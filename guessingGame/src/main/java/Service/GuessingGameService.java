package Service;

import Entities.Game;
import Entities.Round;
import java.util.List;

/**
 *
 * @author Cindy
 */
public interface GuessingGameService {
    void begin();
    Round guess(int gameId, String guess);
    List<Game> getGames();
    Game getGameById(int gameId); 
    List<Round> getRoundsByGame(int gameId);    
}
