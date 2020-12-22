package com.game.Entities;

import java.time.LocalTime;


/**
 *
 * @author Cindy
 */
public class Round {
    
    private int roundId;
    private String guess;
    private LocalTime timeOfTheGuess;
    private String resultOfTheGuess;
    private int gameId;
    
    public Round(){        
    }

    public Round(int roundId, String guess, LocalTime timeOfTheGuess, String resultOfTheGuess) {
        this.roundId = roundId;
        this.guess = guess;
        this.timeOfTheGuess = timeOfTheGuess;
        this.resultOfTheGuess = resultOfTheGuess;
    }

    public int getRoundId() {
        return roundId;
    }
    
    public void setRoundId(int id){
        this.roundId = id;
    }

    public String getGuess() {
        return guess;
    }

    public void setGuess(String guess) {
        this.guess = guess;
    }

    public LocalTime getTimeOfTheGuess() {
        return timeOfTheGuess;
    }

    public void setTimeOfTheGuess(LocalTime timeOfTheGuess) {
        this.timeOfTheGuess = timeOfTheGuess;
    }

    public String getResultOfTheGuess() {
        return resultOfTheGuess;
    }

    public void setResultOfTheGuess(String resultOfTheGuess) {
        this.resultOfTheGuess = resultOfTheGuess;
    }

    public int getGameId() {
        return gameId;
    }

    public void setGameId(int gameId) {
        this.gameId = gameId;
    }
    
    
}