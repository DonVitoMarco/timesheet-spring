package pl.thewalkingcode.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import pl.thewalkingcode.model.EntryQueryDTO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;


@Component
public class EntriesQueryService implements IEntriesQueryService {

    private static final String getEntry =  "SELECT entries.ENTRIES_ID, entries.DATE, entries.TIME, entries.START, " +
                                            "entries.END, entries.APPROVE, users.USERNAME, departments.DEPARTMENT_NAME FROM entries " +
                                            "INNER JOIN users ON entries.USER_ID = users.USER_ID " +
                                            "INNER JOIN departments ON entries.DEPARTMENT_ID = departments.DEPARTMENT_ID " +
                                            "WHERE users.USERNAME = ?";

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public EntriesQueryService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public EntryQueryDTO getEntryByIndex(int index) {
        return null;
    }

    @Override
    public List<EntryQueryDTO> getAllEntries(String username) {

        //TODO lambda
        List<EntryQueryDTO> listEntries = jdbcTemplate.query(getEntry, new RowMapper<EntryQueryDTO>() {
            public EntryQueryDTO mapRow(ResultSet result, int rowNum) throws SQLException {
                EntryQueryDTO entry = new EntryQueryDTO();
                entry.setIndex(result.getInt("ENTRIES_ID"));
                entry.setUsername(result.getString("USERNAME"));
                entry.setDate(result.getDate("DATE"));
                entry.setTime(result.getTime("TIME"));
                entry.setStartTime(result.getTime("START"));
                entry.setEndTime(result.getTime("END"));
                entry.setDepartment(result.getString("DEPARTMENT_NAME"));
                entry.setApprove(result.getBoolean("APPROVE"));
                return entry;
            }
        }, username);

        //TODO logger
        for(EntryQueryDTO entry : listEntries) {
            System.out.println(entry.toString());
        }

        return listEntries;
    }

}
