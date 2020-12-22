package com.game.Service;

import com.game.DAO.GameDao;
import com.game.DAO.RoundDao;
import com.game.Entities.Game;
import com.game.Entities.Round;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

/**
 *
 * @author Cindy
 */
@Service
public class GuessingGameServiceImplementation implements GuessingGameService {
    
    private final GameDao gameDao;
    private final RoundDao roundDao;
    
    public GuessingGameServiceImplementation(GameDao gameDao, RoundDao roundDao) {
        this.gameDao = gameDao;
        this.roundDao = roundDao;
    }
    
    // This method starts a game, generates an answer, and sets the correct status. 
    // Should return a 201 CREATED message as well as the created gameId.
    @Override
    public int begin() {
        //Prepare game object to be created:
        Game game = new Game();
        String answer = generateAnswer(); // call method to obtain a string with 4 different random numbers     
        game.setAnswer(answer); // set game answer to string generated with generateAnswer()
        game.setStatus("IN PROGRESS"); // All the new games starts with "IN PROGRESS" status 
        gameDao.add(game);
        System.out.println(game.getGameId());
        return game.getGameId();
    }
    
    // This method makes a guess by passing the guess and gameId in as JSON.
    // The program must calculate the results of the guess and mark the game finished if the guess is correct.
    // It returns the Round object with the results filled in.
    @Override
    public Round guess(int gameId, String guess) {
        // Prepare round object to be created:
        Round round = new Round();
        round.setGuess(guess);
        round.setTimeOfTheGuess(LocalTime.now()); 
        String verifiedGuess = verifyGuess(guess); // call method to verify if is a valid guess (contains only 4 numbers)
        String result = calculateResult(gameId, verifiedGuess); // call method to calculate the result
        round.setResultOfTheGuess(result);
        round.setGameId(gameId);
        System.out.println(result);
        roundDao.add(round);        
        return round;
    }

    // Returns a list of all games. Be sure in-progress games do not display their answer.
    @Override
    public List<Game> getGames() {
        List<Game> gamesList = gameDao.getAll();
        for (Game game : gamesList ) {
            if (game.getStatus().equals("IN PROGRESS")) {
                game.setAnswer("****"); // hides the answer fo a game with status "in progress"
            }
        }
        return gamesList;
    }

    // Returns a specific game based on ID. Be sure in-progress games do not display their answer.
    @Override
    public Game getGameById(int gameId) {
        Game game = gameDao.findById(gameId);
        if (game.getStatus().equals("IN PROGRESS")) {
                game.setAnswer("****"); // hides the answer if the game status is "in progress"
            }
        return game;
    }

    // Returns a list of rounds for the specified game sorted by time.
    @Override
    public List<Round> getRoundsByGame(int gameId) {
        List<Round> roundsList = roundDao.findByGameId(gameId);
        return roundsList;
    }
    
    
    // Method to obtain a string with 4 different random numbers
    public String generateAnswer() {
        List<Integer> numbers = new ArrayList<>(Arrays.asList(0,1,2,3,4,5,6,7,8,9)); // create a list of Integer from 0-9
        Collections.shuffle(numbers); // randomize the numbers array
        List answer = numbers.subList(0, 4); // generate sublist with the first 4 items from numbers list
        StringBuilder answerStr  = new StringBuilder();  // put each item of list into a StringBuilder
        for (int i = 0; i < 4; i++) {            
           answerStr.append(answer.get(i)); 
        }   
        System.out.println("SHUFFLED ARRAY: " + numbers);
        System.out.println("GENERATED ANSWER: " + answerStr.toString());
        return answerStr.toString(); // transform the list into string
    }
    
    // Method to calculate the result comparing the generated answer and the guess
    public String calculateResult(int gameId, String guess) { 
        int e = 0; // number of exact matches 
        int p = 0; // number of partial matches
        String answer = gameDao.findById(gameId).getAnswer(); // get game generated answer          
        for (int i = 0; i < 4; i++) { // compare generated answer string with round string         
            char a = answer.charAt(i);
            char g = guess.charAt(i);
            if (answer.contains(String.valueOf(g))) { //verify if the answer contains the guess number at position "i"
                if (a == g) { // verify if exact match "e"
                    ++e;
                } else { // verify if partial match "p"
                    ++p;
                }
            }
            if (e == 4) {// verify if game is finished "e:4:p:0", set status to "FINISHED"
                Game gameToUpdate = gameDao.findById(gameId);
                gameToUpdate.setStatus("FINISHED");
                gameDao.update(gameToUpdate);
            }
        }
        String result = "e:" + e + ":p:" + p;            
        return result;
    }
    
    // Verify if JSON guess received has 4 elements and contains only numbers
    public String verifyGuess(String guess) {
        // verify if String is not null
        if (guess == null) { 
            throw new IllegalArgumentException();
        }
        // verify if String lenght is not 4
        if (guess.length() != 4) {
            throw new IllegalArgumentException();
        }
        // call method to verify if string is not numeric
        if (!isNumeric(guess)){
            throw new IllegalArgumentException();
        }
        return guess;
    }
    
    // Method to verify if string is numeric, should only have numbers from 0-9
    public static boolean isNumeric(String strNum) {
        try {
            int in = Integer.parseInt(strNum);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }
}
