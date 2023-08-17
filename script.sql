CREATE DATABASE customer_manager;

USE customer_manager;

CREATE TABLE customer (
                          id BIGINT PRIMARY KEY AUTO_INCREMENT,
                          name VARCHAR(255),
                          is_active BOOLEAN DEFAULT TRUE
);

CREATE TABLE phone_number (
                              id BIGINT PRIMARY KEY AUTO_INCREMENT,
                              number VARCHAR(20),
                              customer_id BIGINT,
                              FOREIGN KEY (customer_id) REFERENCES customer(id)
);

CREATE TABLE customer_pf (
                             id BIGINT PRIMARY KEY,
                             cpf VARCHAR(20) unique,
                             rg VARCHAR(20) unique,
                             type VARCHAR(10),
                             registration_date DATE,
                             FOREIGN KEY (id) REFERENCES customer(id)
);

CREATE TABLE customer_pj (
                             id BIGINT PRIMARY KEY,
                             cnpj VARCHAR(20) unique,
                             ie VARCHAR(20) unique,
                             type VARCHAR(10),
                             registration_date DATE,
                             FOREIGN KEY (id) REFERENCES customer(id)
);