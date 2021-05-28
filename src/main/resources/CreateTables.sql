CREATE TABLE CLIENTS (
                         clientId INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
                         name VARCHAR NOT NULL,
                         phoneNumber VARCHAR NOT NULL UNIQUE,
                         email VARCHAR UNIQUE,
                         passportNumber VARCHAR NOT NULL UNIQUE
);

CREATE TABLE ACCOUNTS (
                          accountId INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
                          accountNumber BIGINT NOT NULL,
                          balance DOUBLE NOT NULL,
                          clientId INT NOT NULL,
                          FOREIGN KEY (clientId) REFERENCES CLIENTS(clientId) ON DELETE CASCADE
);

CREATE TABLE CARDS (
                           cardId INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
                           cardNumber BIGINT NOT NULL,
                           accountId INT NOT NULL,
                           FOREIGN KEY (accountId) REFERENCES ACCOUNTS(accountId) ON DELETE CASCADE
);

