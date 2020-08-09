## Тестовое задание для smartsoft

# Скрипт создания базы данных:

CREATE DATABASE smartsoft;

CREATE TABLE users (
    id bigserial primary key,
    username varchar(20) NOT NULL,
    password varchar(20) NOT NULL
);

INSERT INTO users (id, username, password) VALUES (0, 'user1', 'user1');

CREATE TABLE valute (
    id varchar(10) primary key,
    num_code smallint NOT NULL,
    char_code varchar(5) NOT NULL,
    nominal smallint NOT NULL,
    name varchar(50) NOT NULL,
    value real NOT NULL
);

CREATE TABLE valute_conversions_history (
    id bigint GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    src_valute varchar(5) NOT NULL,
    src_summ real NOT NULL,
    dst_valute varchar(5) NOT NULL,
    dst_summ real NOT NULL,
    date date NOT NULL
);

CREATE TABLE valute_last_update (
    id bigint GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    last_update date NOT NULL
);

# Запуск front:

yarn install
yarn start

# Запуск backend

mvn spring-boot:run

