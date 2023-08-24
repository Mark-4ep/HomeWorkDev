
CREATE TABLE Client (
    id BIGINT auto_increment PRIMARY KEY ,
    name VARCHAR(200) UNIQUE
        CONSTRAINT CK_client_name
        CHECK (LENGTH(name) >= 3) NOT NULL
);

CREATE TABLE Planet (
    id VARCHAR(200) UNIQUE
        CONSTRAINT CK_digit_uppercase
        CHECK (id REGEXP '^[A-Z0-9]*$') NOT NULL,
    name VARCHAR(500)
        CONSTRAINT CK_planet_name_length
        CHECK (LENGTH(name) >= 1) NOT NULL
);


CREATE TABLE Ticket (
    id BIGINT PRIMARY KEY auto_increment,
    created_at  timestamp DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    client_id BIGINT,
    from_planet_id VARCHAR(50),
    to_planet_id VARCHAR(50),
    FOREIGN KEY (from_planet_id) REFERENCES Planet(id) ON DELETE CASCADE,
    FOREIGN KEY (to_planet_id) REFERENCES Planet(id) ON DELETE CASCADE,
    FOREIGN KEY (client_id) REFERENCES Client(id) ON DELETE CASCADE
);