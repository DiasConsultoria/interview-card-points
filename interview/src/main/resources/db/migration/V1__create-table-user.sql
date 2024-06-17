CREATE TABLE IF NOT EXISTS users(
    id serial PRIMARY KEY,
    username TEXT NOT NULL,
    password TEXT NOT NULL,
    cpf TEXT NOT NULL,
    email TEXT NOT NULL,
    tier INT NOT NULL ,
    points INT NOT NULL
);