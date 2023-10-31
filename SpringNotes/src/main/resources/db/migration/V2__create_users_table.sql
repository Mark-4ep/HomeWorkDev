 CREATE TABLE users(
    users_id INTEGER NOT NULL auto_increment PRIMARY KEY,
    username VARCHAR(50) NOT NULL,
    password VARCHAR(64) NOT NULL,
    role VARCHAR(50) NOT NULL,
    enabled INTEGER DEFAULT NULL
  );