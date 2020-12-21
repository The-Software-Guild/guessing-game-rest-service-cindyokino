package cindy.okino.guessinggame.dao;

import cindy.okino.guessinggame.entity.Round;
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
public class RoundDatabaseDao implements RoundDao {
    
    private final JdbcTemplate jdbc;
    
    @Autowired
    public RoundDatabaseDao(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    @Override
    public Round add(Round round) {   
        final String sql = "INSERT INTO round(guess, timeOfTheGuess, resultOfTheGuess) VALUES (?,?,?);";
        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();

        jdbc.update((Connection conn) -> {
            
            PreparedStatement statement = conn.prepareStatement(
            sql,
            Statement.RETURN_GENERATED_KEYS);
            
            statement.setString(1, round.getGuess());
            statement.setTime(2, round.getTimeOfTheGuess());
            statement.setString(3, round.getResultOfTheGuess());
            return statement;
        }, keyHolder);
        
        round.setRoundId(keyHolder.getKey().intValue());
        
        return round;
    }

    @Override
    public List<Round> getAll() {
        final String SELECT_ALL_ROUND = "SELECT * FROM round";
        return jdbc.query(SELECT_ALL_ROUND, new RoundDatabaseDao.RoundMapper());
    }

    @Override
    public Round findById(int id) {
        try {
            final String SELECT_ROUND_BY_ID = "SELECT * FROM round WHERE roundId =?";
            return jdbc.queryForObject(SELECT_ROUND_BY_ID, new RoundDatabaseDao.RoundMapper(), id);
        } catch (DataAccessException ex) {
            return null;
        }
    }

    @Override
    public List<Round> findByGameId(int gameId) {
        try {
            final String SELECT_ROUND_BY_GAMEID = "SELECT * FROM round "
                    + "INNER JOIN game ON round.gameId = game.gameId "
                    + "WHERE round.gameId =?";
            return jdbc.query(SELECT_ROUND_BY_GAMEID, new RoundDatabaseDao.RoundMapper(), gameId);

        } catch (DataAccessException ex) {
            return null;
        }
    }

    @Override
    public boolean update(Round round) {
        final String UPDATE_ROUND = "UPDATE round SET guess = ?, timeOfTheGuess = ?, resultOfTheGuess =?, gameId = ? WHERE roundId = ?";

        if (findById(round.getRoundId()) != null) {
            jdbc.update(UPDATE_ROUND, round.getGuess(), round.getTimeOfTheGuess(), round.getResultOfTheGuess());
            return true;
        } else {
            return false;
        }
    }

    @Override
    @Transactional
    public boolean deleteById(int id) {
        final String DELETE_ROUND_BY_ID = "DELETE FROM game WHERE roundId = ?";

        jdbc.update(DELETE_ROUND_BY_ID, id);

        if (findById(id) == null) {
            return true;
        } else {
            return false;
        }
    }


    // We use the RowMapper interface to turn a row of the ResultSet into an object. 
    // Inside the mapRow method, we look at a single row of the ResultSet, so it pull the data out of it and creates our new round object.
    private static final class RoundMapper implements RowMapper<Round> {

        @Override
        public Round mapRow(ResultSet rs, int index) throws SQLException {

            Round round = new Round();
            round.setRoundId(rs.getInt("roundId"));
            round.setGuess(rs.getString("setGuess"));
            round.setTimeOfTheGuess(rs.getTime("setTimeOfTheGuess"));
            round.setResultOfTheGuess(rs.getString("setResultOfTheGuess"));

            return round;
        }

    }
}
