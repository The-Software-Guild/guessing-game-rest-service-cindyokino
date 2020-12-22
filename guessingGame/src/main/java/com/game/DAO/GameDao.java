package com.game.DAO;

import com.game.Entities.Game;
import java.util.List;

/**
 *
 * @author Cindy
 */
public interface GameDao {
    
    // Add a new game to the database
    Game add(Game game);
    
    // Get a list of all the games from the database
    List<Game> getAll();
    
    // Find a game by id from the database
    Game findById(int id);
    
    // Return true if the game exists and is updated at the database
    boolean update(Game game);
    
    // Return true if the game exists and is deleted from the database
//    boolean deleteById(int id);
}