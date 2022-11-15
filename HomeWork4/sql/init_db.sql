CREATE DATABASE myHomeWork;

USE myHomeWork;

CREATE TABLE worker(
id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(1000) CONSTRAINT CK_worker_name CHECK
    (LENGTH(name) >= 2) NOT NULL,
    birthday DATE CONSTRAINT CK_worker_birthday CHECK
    (birthday > '1900-01-01'),
    level VARCHAR(10) CONSTRAINT CHECK
    (level IN ('Trainee', 'Junior', 'Middle', 'Senior')) NOT NULL,
    salary INT CONSTRAINT CK_worker_salary CHECK
    (salary >= 100 AND salary <= 100000)
);

CREATE TABLE client(
    id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(1000) CONSTRAINT CK_client_name CHECK
        (LENGTH(name) >= 2) NOT NULL
);

CREATE TABLE project(
    id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    client_id INT,
    start_date DATE,
    finish_date DATE
);

CREATE TABLE project_worker(
    project_id INT,
    worker_id INT,
    PRIMARY KEY(project_id,worker_id),
    FOREIGN KEY (project_id) REFERENCES project(id)
);
