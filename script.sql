CREATE DATABASE customer_manager;

USE customer_manager;

CREATE TABLE customer (
                          id BIGINT PRIMARY KEY AUTO_INCREMENT,
                          name VARCHAR(255) NOT NULL,
                          is_active BOOLEAN DEFAULT TRUE
);

CREATE TABLE phone_number (
                              id BIGINT PRIMARY KEY AUTO_INCREMENT,
                              number VARCHAR(20) NOT NULL,
                              customer_id BIGINT,
                              FOREIGN KEY (customer_id) REFERENCES customer(id)
);

CREATE TABLE customer_pf (
                             id BIGINT PRIMARY KEY,
                             cpf VARCHAR(20) NOT NULL,
                             rg VARCHAR(20) NOT NULL,
                             type VARCHAR(10) NOT NULL,
                             registration_date DATE,
                             FOREIGN KEY (id) REFERENCES customer(id)
);

CREATE TABLE customer_pj (
                             id BIGINT PRIMARY KEY,
                             cnpj VARCHAR(20) NOT NULL,
                             ie VARCHAR(20) NOT NULL,
                             type VARCHAR(10) NOT NULL,
                             registration_date DATE,
                             FOREIGN KEY (id) REFERENCES customer(id)
);
