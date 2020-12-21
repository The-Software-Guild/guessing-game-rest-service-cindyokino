package DAO;

import Entities.Round;
import java.util.List;

/**
 *
 * @author Cindy
 */
public interface RoundDao {
    
    // Add a new round for a specific game to the database
    Round add(Round round);
    
    // Get a list of all the rounds from the database
    List<Round> getAll();
    
    // Find a round by id from the database
    Round findById(int id);   
    
    // Get a list of all the rounds of a specific game from the database
    List<Round> findByGameId(int gameId);
    
    // Return true if the round exists and is updated at the database
    boolean update(Round round);

    // Return true if round exists and is deleted from database
    boolean deleteById(int id);
}
