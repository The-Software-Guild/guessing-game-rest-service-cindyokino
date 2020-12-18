package cindy.okino.guessinggame.entity;

import java.sql.Time;

/**
 *
 * @author Cindy
 */
public class Round {
    
    private int roundId;
    private String guess;
    private Time timeOfTheGuess;
    private String resultOfTheGuess;
    
    public Round(){        
    }

    public Round(int roundId, String guess, Time timeOfTheGuess, String resultOfTheGuess) {
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

    public Time getTimeOfTheGuess() {
        return timeOfTheGuess;
    }

    public void setTimeOfTheGuess(Time timeOfTheGuess) {
        this.timeOfTheGuess = timeOfTheGuess;
    }

    public String getResultOfTheGuess() {
        return resultOfTheGuess;
    }

    public void setResultOfTheGuess(String resultOfTheGuess) {
        this.resultOfTheGuess = resultOfTheGuess;
    }
}