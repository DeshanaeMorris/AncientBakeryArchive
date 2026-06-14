-- Enable foreign key support in SQLite
PRAGMA foreign_keys = ON;
--Drop tables if exist
DROP TABLE IF EXISTS Recipe_Ingredients;
DROP TABLE IF EXISTS Recipes;
DROP TABLE IF EXISTS Eras;
DROP TABLE IF EXISTS Ingredients;
DROP TABLE IF EXISTS Glossary;

--Eras Table
CREATE TABLE Eras (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    name VARCHAR NOT NULL,
    time_period VARCHAR,
    History_text VARCHAR
);
--Ingredients Table
CREATE TABLE Ingredients (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    name VARCHAR NOT NULL,
    category VARCHAR NOT NULL
);

--Recipes Table
CREATE TABLE Recipes (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    name VARCHAR NOT NULL,
    era_id INTEGER NOT NULL,
    original_text VARCHAR NOT NULL,
    modernized_text VARCHAR,
    temperature_description VARCHAR,
    base_portion INTEGER,
    Sources VARCHAR,
    image_url VARCHAR,
    FOREIGN KEY (era_id) REFERENCES Eras (id) ON DELETE CASCADE
);
--Recipe Ingredients Table
CREATE TABLE Recipe_Ingredients (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    Recipes_ID INTEGER NOT NULL,
    Ingredients_ID INTEGER NOT NULL,
    Quantity REAL NOT NULL,
    Unit VARCHAR NOT NULL,
    FOREIGN KEY (Recipes_ID) REFERENCES Recipes (id) ON DELETE CASCADE,
    FOREIGN KEY (Ingredients_ID) REFERENCES Ingredients (id) ON DELETE CASCADE
);
--Glossary Table
CREATE TABLE Glossary (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    Term VARCHAR NOT NULL,
    Definition VARCHAR NOT NULL,
    Modern_Substitute VARCHAR
);

