DROP DATABASE IF EXISTS guessingGameDB;

CREATE DATABASE guessingGameDB;

USE guessingGameDB;

CREATE TABLE game (
	gameId INT PRIMARY KEY AUTO_INCREMENT,
    answer VARCHAR(4) NOT NULL,
    `status` VARCHAR(15) NOT NULL
);

CREATE TABLE round (
	roundId INT PRIMARY KEY AUTO_INCREMENT,
    guess VARCHAR(4) NOT NULL,
    timeOfTheGuess TIME NOT NULL,
    resultOfTheGuess VARCHAR(15),
    gameId INT NOT NULL,
    foreign key (gameId) references game(gameId)
);