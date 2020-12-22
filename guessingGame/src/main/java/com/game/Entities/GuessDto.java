package com.game.Entities;

/**
 *
 * @author Cindy
 */
public class GuessDto {
    private String guess;
    private int gameId;

    public String getGuess() {
        return guess;
    }

    public void setGuess(String guess) {
        this.guess = guess;
    }

    public int getGameId() {
        return gameId;
    }

    public void setGameId(int gameId) {
        this.gameId = gameId;
    }    
    
}
