package cindy.okino.guessinggame.dao;

import cindy.okino.guessinggame.entity.Game;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Cindy
 */
@Repository
public class GameDatabaseDao implements GameDao {
    
    private final JdbcTemplate jdbc;
    
    @Autowired
    public GameDatabaseDao(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    @Override
    public Game add(Game game) {
        
        final String sql = "INSERT INTO game(answer, status) VALUES (?,?);";
        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();

        jdbc.update((Connection conn) -> {
            
            PreparedStatement statement = conn.prepareStatement(
            sql,
            Statement.RETURN_GENERATED_KEYS);
            
            statement.setString(1, game.getAnswer());
            statement.setString(2, game.getStatus());
            return statement;
        }, keyHolder);
        
        game.setGameId(keyHolder.getKey().intValue());
        
        return game;
    }

    @Override
    public List<Game> getAll() {
        final String SELECT_ALL_GAMES = "SELECT * FROM game";
        return jdbc.query(SELECT_ALL_GAMES, new GameMapper());
    }

    @Override
    public Game findById(int id) {
        try {
            final String SELECT_GAME_BY_ID = "SELECT * FROM game WHERE gameId =?";
            return jdbc.queryForObject(SELECT_GAME_BY_ID, new GameMapper(), id);
        } catch (DataAccessException ex) {
            return null;
        }
    }

    @Override
    public boolean update(Game game) {
        final String UPDATE_GAME = "UPDATE game SET answer = ?, status = ? WHERE id = ?";

        if (findById(game.getGameId()) != null) {
            jdbc.update(UPDATE_GAME, game.getAnswer(), game.getStatus(), game.getGameId());
            return true;
        } else {
            return false;
        }
    }

    @Override
    @Transactional
    public boolean deleteById(int id) {
        final String DELETE_GAME_BY_ID = "DELETE FROM game WHERE id = ?";

        jdbc.update(DELETE_GAME_BY_ID, id);

        if (findById(id) == null) {
            return true;
        } else {
            return false;
        }
    }
    
    
    // We use the RowMapper interface to turn a row of the ResultSet into an object. 
    // Inside the mapRow method, we look at a single row of the ResultSet, so it pull the data out of it and creates our new game object.
    private static final class GameMapper implements RowMapper<Game> {

        @Override
        public Game mapRow(ResultSet rs, int index) throws SQLException {

            Game game = new Game();
            game.setGameId(rs.getInt("gameId"));
            game.setAnswer(rs.getString("answer"));
            game.setStatus(rs.getString("status"));

            return game;
        }

    }
}
