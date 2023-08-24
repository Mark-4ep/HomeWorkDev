INSERT INTO Client (name) VALUES
    ('Mark'),
    ('Ron'),
    ('Sem'),
    ('Olivia'),
    ('Leo'),
    ('Luca'),
    ('Nova'),
    ('Luna'),
    ('Sirius'),
    ('Bob');


INSERT INTO Planet (id, name) VALUES
    ('MERC', 'Mercury'),
    ('MARS', 'Mars'),
    ('EARTH', 'Earth'),
    ('VEN', 'Venus'),
    ('JUP', 'Jupiter');



INSERT INTO Ticket (Client_id, from_planet_id, to_planet_id) VALUES
    (1, 'JUP' , 'VEN'),
    (2, 'JUP' , 'MERC'),
    (3, 'JUP' , 'EARTH'),
    (4, 'JUP' , 'JUP'),
    (5, 'JUP' , 'MARS'),
    (6, 'MERC' , 'MARS'),
    (7, 'MERC' , 'EARTH'),
    (8, 'MERC' , 'VEN'),
    (9, 'MERC' , 'JUP'),
    (10, 'EARTH' , 'JUP')
    ;

