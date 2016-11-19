package pl.thewalkingcode.model;

import org.springframework.jdbc.core.RowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;


public class EntryQueryDTOMapper implements RowMapper<EntryQueryDTO> {

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

}
