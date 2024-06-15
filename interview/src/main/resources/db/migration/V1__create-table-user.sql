CREATE TABLE IF NOT EXISTS USERS(
    id INT PRIMARY KEY ,
    username TEXT NOT NULL,
    password TEXT NOT NULL,
    cpf TEXT NOT NULL,
    email TEXT NOT NULL,
    tier INT NOT NULL ,
    points TEXT NOT NULL
);