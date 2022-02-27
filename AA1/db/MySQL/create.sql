DROP DATABASE IF EXISTS scheduling_system;
CREATE DATABASE scheduling_system;
USE scheduling_system;

CREATE TABLE IF NOT EXISTS users (
	name VARCHAR(100) NOT NULL,
	email VARCHAR(50) NOT NULL UNIQUE ,
	cpf VARCHAR(11) NOT NULL,
	password VARCHAR(50) NOT NULL,
	role VARCHAR(50),
	PRIMARY KEY(cpf)
);

CREATE TABLE IF NOT EXISTS administrators (
	cpf VARCHAR(11) NOT NULL,
	PRIMARY KEY(cpf),
	CONSTRAINT fk_admin_user
		FOREIGN KEY(cpf)
		REFERENCES users(cpf)
		ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS clients (
	cpf VARCHAR(11) NOT NULL,
    telephone VARCHAR(14) NOT NULL,
	gender VARCHAR(20) NOT NULL,
	birth_date DATE NOT NULL,
	PRIMARY KEY(cpf),
    CONSTRAINT fk_client_user
        FOREIGN KEY(cpf)
        REFERENCES users(cpf)
        ON DELETE CASCADE	
);

CREATE TABLE IF  NOT EXISTS professionals (
	cpf VARCHAR(11) NOT NULL,
	qualifications VARCHAR(100) NOT NULL,
	knowledge_area VARCHAR(50) NOT NULL,
	expertise VARCHAR(50) NOT NULL,
	PRIMARY KEY(cpf),
    CONSTRAINT fk_professional_user
        FOREIGN KEY(cpf)
        REFERENCES users(cpf)
        ON DELETE CASCADE
);

INSERT INTO users(cpf, name, email, password, role) 
VALUES ("19", "vini", "vini@gmail.com", "oi", "ADMIN ");

INSERT INTO clients(cpf, telephone, gender, birth_date) 
VALUES ("19", "98846-9932", "Masculino", "2020-10-10");

