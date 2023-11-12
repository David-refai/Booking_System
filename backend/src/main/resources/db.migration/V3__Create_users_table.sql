-- postgresql
CREATE TABLE users(
    id SERIAL PRIMARY KEY,
    username VARCHAR(50) UNIQUE NOT NULL,
    password VARCHAR(50) NOT NULL,
    email VARCHAR(355) UNIQUE NOT NULL,
    role VARCHAR(50)  NOT NULL,
    created_on TIMESTAMP NOT NULL,

    CONSTRAINT users_unique UNIQUE(username, email)
);

