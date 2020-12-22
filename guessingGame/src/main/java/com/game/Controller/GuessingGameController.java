package com.game.Controller;

import com.game.DAO.GameDao;
import com.game.DAO.RoundDao;
import com.game.Entities.Game;
import com.game.Entities.GuessDto;
import com.game.Entities.Round;
import com.game.Service.GuessingGameServiceImplementation;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Cindy
 */
@RestController
@RequestMapping("/gameapi")
public class GuessingGameController {
    
    private final GameDao game;
    private final RoundDao round;
    private final GuessingGameServiceImplementation service;
    
    public GuessingGameController(GameDao gameDao, RoundDao roundDao, GuessingGameServiceImplementation service) {
        this.game = gameDao;
        this.round = roundDao;
        this.service = service;    
    }

    @PostMapping("/begin")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<String> begin() {
        int gameId = service.begin();
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body("{\"gameId\": " + gameId + "}");
    }
    
    @PostMapping("/guess")
    public ResponseEntity<Round> guess(@RequestBody GuessDto guessDto) {
        System.out.println("gameId" + guessDto.getGameId());
        System.out.println("guess" + guessDto.getGuess());
        Round round = service.guess(guessDto.getGameId(),guessDto.getGuess());
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(round);
    }
    
    @GetMapping("/game")
    public List<Game> all() {
        List<Game> gamesList = service.getGames();
        return gamesList;
    }
    
    @GetMapping("/game/{gameId}")
    public Game findById(@PathVariable int gameId) {
        Game game = service.getGameById(gameId);
        return game;
    }
    
    @GetMapping("/rounds/{gameId}")
    public List<Round> getRoundsByGame(@PathVariable int gameId) {
        List<Round> roundsList = service.getRoundsByGame(gameId);
        return roundsList;
    }
}
