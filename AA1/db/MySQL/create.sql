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

CREATE TABLE IF NOT EXISTS appointments (
	cpf_cliente VARCHAR(11) NOT NULL,
	cpf_professional VARCHAR(11) NOT NULL,
	data_consulta DATE NOT NULL,
	hora_consulta INTEGER NOT NULL,
	PRIMARY KEY(cpf_cliente, cpf_professional, data_consulta, hora_consulta),
	CONSTRAINT fk_CPF_cliente FOREIGN KEY (cpf_cliente) REFERENCES clients (cpf) ON DELETE CASCADE,
	CONSTRAINT fk_CPF_professional FOREIGN KEY (cpf_professional) REFERENCES professionals (cpf) ON DELETE CASCADE,
	CONSTRAINT hora_limite_inferior CHECK (hora_consulta > 0),
	CONSTRAINT hora_limite_superior CHECK (hora_consulta < 24)
);


INSERT INTO users (cpf, name, email, password, role)
VALUES ("1", "Client", "client@client.com", "client", "CLIENT");

INSERT INTO clients (cpf, telephone, gender, birth_date) 
VALUES ("1", "9 9999-9999", "M", "2020-10-10");

INSERT INTO users (cpf, name, email, password, role)
VALUES ("2", "Prof", "prof@prof.com", "prof", "PROF");

INSERT INTO professionals (cpf, qualifications, knowledge_area, expertise)
VALUES ("2", "/curriculum", "Engenharias", "Industrial");

INSERT INTO users (cpf, name, email, password, role)
VALUES ("3", "Client2", "client2@client.com", "client2", "ADMIN");

INSERT INTO clients (cpf, telephone, gender, birth_date) 
VALUES ("3", "9 9999-9999", "M", "2020-10-10");

INSERT INTO appointments(cpf_cliente, cpf_professional, data_consulta, hora_consulta) 
VALUES('1', '2','2022-11-10', 11);
