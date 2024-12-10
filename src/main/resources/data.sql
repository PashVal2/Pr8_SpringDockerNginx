CREATE DATABASE IF NOT EXISTS mydb;

-- Создание пользователя с необходимыми привилегиями
CREATE USER IF NOT EXISTS 'root'@'%' IDENTIFIED BY 'password';
GRANT SELECT, UPDATE, INSERT ON mydb.* TO 'root'@'%';
FLUSH PRIVILEGES;

USE mydb;

-- Создание таблицы users, если она не существует
CREATE TABLE IF NOT EXISTS author (
  author_id INT PRIMARY KEY AUTO_INCREMENT,
  name VARCHAR(255) NOT NULL,
  surname VARCHAR(255) NOT NULL
);

-- Вставка данных с проверкой на существование записи
INSERT INTO author (name, surname)
SELECT * FROM (SELECT 'Alex', 'Rover') AS tmp
WHERE NOT EXISTS (
    SELECT name FROM author WHERE name = 'Alex' AND surname = 'Rover'
) LIMIT 1;

INSERT INTO author (name, surname)
SELECT * FROM (SELECT 'Bob', 'Marley') AS tmp
WHERE NOT EXISTS (
    SELECT name FROM author WHERE name = 'Bob' AND surname = 'Marley'
) LIMIT 1;

INSERT INTO author (name, surname)
SELECT * FROM (SELECT 'Kate', 'Yandson') AS tmp
WHERE NOT EXISTS (
    SELECT name FROM author WHERE name = 'Kate' AND surname = 'Yandson'
) LIMIT 1;

INSERT INTO author (name, surname)
SELECT * FROM (SELECT 'Lilo', 'Black') AS tmp
WHERE NOT EXISTS (
    SELECT name FROM author WHERE name = 'Lilo' AND surname = 'Black'
) LIMIT 1;

CREATE TABLE IF NOT EXISTS lombard_item (
  item_id INT PRIMARY KEY AUTO_INCREMENT, -- Поле id с автоинкрементом
  name VARCHAR(255) NOT NULL,                -- Поле name с ограничением длины
  description TEXT NOT NULL,                 -- Поле description с типом TEXT
  cost DOUBLE NOT NULL                       -- Поле cost для стоимости
);

INSERT INTO lombard_item (name, description, cost)
SELECT * FROM (SELECT 'Golden Necklace', 'A beautiful golden necklace with intricate designs.', 1500.75) AS tmp
WHERE NOT EXISTS (
    SELECT name FROM lombard_item WHERE name = 'Golden Necklace'
) LIMIT 1;

INSERT INTO lombard_item (name, description, cost)
SELECT * FROM (SELECT 'Vintage Watch', 'A classic vintage watch from the 1950s.', 300.00) AS tmp
WHERE NOT EXISTS (
    SELECT name FROM lombard_item WHERE name = 'Vintage Watch'
) LIMIT 1;

INSERT INTO lombard_item (name, description, cost)
SELECT * FROM (SELECT 'Diamond Ring', 'An exquisite diamond ring with a flawless cut.', 2500.50) AS tmp
WHERE NOT EXISTS (
    SELECT name FROM lombard_item WHERE name = 'Diamond Ring'
) LIMIT 1;
