package com.game.Service;

import com.game.Entities.Game;
import com.game.Entities.Round;
import java.util.List;

/**
 *
 * @author Cindy
 */
public interface GuessingGameService {
    Game begin();
    Round guess(int gameId, String guess);
    List<Game> getGames();
    Game getGameById(int gameId); 
    List<Round> getRoundsByGame(int gameId);    
}
