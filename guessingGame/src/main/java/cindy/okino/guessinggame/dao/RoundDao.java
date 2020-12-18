package cindy.okino.guessinggame.dao;

import cindy.okino.guessinggame.entity.Round;
import java.util.List;

/**
 *
 * @author Cindy
 */
public interface RoundDao {
    
    Round add(Round round);
    
    List<Round> getAll();
    
    Round findById(int id);
    
    // Return true if the round exists and is updated at the database
    boolean update(Round round);

    // Return true if round exists and is deleted from database
    boolean deleteById(int id);
}
