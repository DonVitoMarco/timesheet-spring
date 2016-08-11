package pl.thewalkingcode.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import pl.thewalkingcode.model.Entry;

import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


@Repository
public class GetEntries {

    JdbcTemplate jdbcTemplate;

    @Autowired
    public GetEntries(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    
    private static final String GET_ALL = "SELECT entries.data, entries.start, entries.end, entries.time, entries.user, department.department_name" +
            "FROM entries INNER JOIN department ON entries.department = department.department_id WHERE entries.user = 'marek'";

    public List<Entry> getAllEntries() {
        List<Entry> entries = new ArrayList<>();
        List<Map<String, Object>> rows = jdbcTemplate.queryForList(GET_ALL);
        for(Map row : rows) {
            Entry entry = new Entry();
            entry.setDate((Date) row.get("DATA"));
            entry.setTime((Time) row.get("TIME"));
            entry.setTimeStart((Time) row.get("START"));
            entry.setTimeEnd((Time) row.get("END"));
            entry.setDepartment((String) row.get("DEPARTMENT"));
            entry.setUser((String) row.get("USERNAME"));
        }
        return entries;
    }

}