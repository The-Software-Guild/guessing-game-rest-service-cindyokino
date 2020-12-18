package cindy.okino.guessinggame.entity;

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
    
    public Round(){        
    }

    public Round(int roundId, String guess, LocalTime timeOfTheGuess) {
        this.roundId = roundId;
        this.guess = guess;
        this.timeOfTheGuess = timeOfTheGuess;
    }

    public int getRoundId() {
        return roundId;
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
}