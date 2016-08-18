package pl.thewalkingcode.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import pl.thewalkingcode.model.EntryQueryDTO;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;


@Component
public class EntriesQueryService implements IEntriesQueryService {

    private static final String GET_ENTRIES = "SELECT entries.*, users.USERNAME FROM entries INNER JOIN users ON entries.USER_ID = users.USER_ID WHERE users.USERNAME = ? AND entries.DATE BETWEEN ? AND ?";


    private JdbcTemplate jdbcTemplate;

    @Autowired
    public EntriesQueryService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    @Override
    public EntryQueryDTO getEntryByIndex(String username, int index) {
        return null;
    }

    @Override
    public List<EntryQueryDTO> getAllEntries(String username, Date startDate, Date endDate) {

        //TODO lambda
        List<EntryQueryDTO> listEntries = jdbcTemplate.query(GET_ENTRIES, new RowMapper<EntryQueryDTO>() {
            public EntryQueryDTO mapRow(ResultSet result, int rowNum) throws SQLException {
                EntryQueryDTO entry = new EntryQueryDTO();
                entry.setIndex(result.getInt("ENTRIES_ID"));
                entry.setUsername(result.getString("USERNAME"));
                entry.setDate(result.getDate("DATE"));
                entry.setTime(result.getTime("TIME"));
                entry.setStartTime(result.getTime("START"));
                entry.setEndTime(result.getTime("END"));
                entry.setApprove(result.getBoolean("APPROVE"));
                return entry;
            }
        }, username, startDate, endDate);

        //TODO logger
        for(EntryQueryDTO entry : listEntries) {
            System.out.println(entry.toString());
        }

        return listEntries;
    }

}
