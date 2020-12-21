package com.game.Entities;

/**
 *
 * @author Cindy
 */
public class Game {
    
    private int gameId;
    private String answer;
    private String status;

    public Game() {        
    }
    
    public Game(int gameId, String answer, String status) {
        this.gameId = gameId;
        this.answer = answer;
        this.status = status;
    }

    public int getGameId() {
        return gameId;
    }
    
    public void setGameId(int id){
        this.gameId = id;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    } 
}