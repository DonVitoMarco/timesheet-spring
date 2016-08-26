package pl.thewalkingcode.service;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import pl.thewalkingcode.model.EntryQueryDTO;
import pl.thewalkingcode.model.EntryQueryDTOMapper;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;


@Component
public class EntriesQueryService implements IEntriesQueryService {

    private final static Logger logger = Logger.getLogger(EntriesQueryService.class);

    private static final String GET_ENTRIES = "SELECT entries.*, users.USERNAME FROM entries " +
            "INNER JOIN users ON entries.USER_ID = users.USER_ID WHERE users.USERNAME = ? AND entries.DATE BETWEEN ? AND ?";
    private static final String GET_ALL_ENTRIES = "SELECT entries.*, users.USERNAME FROM entries " +
            "INNER JOIN users ON entries.USER_ID = users.USER_ID WHERE users.USERNAME LIKE '%' AND entries.DATE BETWEEN ? AND ?";

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public EntriesQueryService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    @Override
    public List<EntryQueryDTO> getAllEntries(String username, Date startDate, Date endDate) {
        logger.debug("Get All Entries");
        return jdbcTemplate.query(GET_ENTRIES, new EntryQueryDTOMapper(), username, startDate, endDate);
    }

    public List<EntryQueryDTO> getAllEntriesAllUsers(Date startDate, Date endDate) {
        logger.debug("Get All Entries From All Users");
        return jdbcTemplate.query(GET_ALL_ENTRIES, new EntryQueryDTOMapper(), startDate, endDate);
    }

}
