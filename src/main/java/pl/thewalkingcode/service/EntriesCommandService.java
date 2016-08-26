package pl.thewalkingcode.service;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import pl.thewalkingcode.controller.HomeController;
import pl.thewalkingcode.model.EntryCommandDTO;
import pl.thewalkingcode.model.EntryDeleteFormDTO;
import pl.thewalkingcode.model.EntryFormDTO;

import java.sql.Date;


@Component
public class EntriesCommandService implements IEntriesCommandService {

    private final static Logger logger = Logger.getLogger(EntriesCommandService.class);

    private static final String INSERT_ENTRY = "INSERT INTO entries (date, time, start, end, user_id) " +
            "VALUES (?, ?, ?, ?, (SELECT users.USER_ID FROM users WHERE users.USERNAME = ?))";
    private static final String EDIT_ENTRY = "  UPDATE entries SET entries.START = ?, entries.END = ?, " +
            "entries.TIME = ?, entries.APPROVE = 0 WHERE entries.DATE = ? AND entries.USER_ID = (SELECT users.USER_ID FROM users WHERE users.USERNAME = ?)";
    private static final String CHECK_ENTRY = "SELECT COUNT(*) FROM entries WHERE entries.DATE = ? AND " +
            "entries.USER_ID = (SELECT users.USER_ID FROM users WHERE users.USERNAME = ?)";
    private static final String DELETE_ENTRY = "DELETE FROM entries WHERE entries.USER_ID = " +
            "(SELECT users.USER_ID FROM users WHERE users.USERNAME = ?) AND entries.DATE = ? AND entries.ENTRIES_ID = ?";
    private static final String APPROVE_ENTRY = "UPDATE entries SET entries.APPROVE = 1 WHERE entries.ENTRIES_ID = ?";
    private static final String NOT_APPROVE_ENTRY = "UPDATE entries SET entries.APPROVE = 0 WHERE entries.ENTRIES_ID = ?";

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public EntriesCommandService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    @Override
    public EntryCommandDTO addNewEntry(EntryFormDTO entryFormDTO, String username) {
        EntryCommandDTO entry = convertEntryFormDTOToCommandDTO(entryFormDTO, username);
        logger.debug("Add New Entry Before Convert: " + entryFormDTO.toString() + " " + username);
        logger.debug("Add New Entry: " + entry.toString());
        if (!checkExistEntry(entry.getDate(), entry.getUsername())) {
            jdbcTemplate.update(INSERT_ENTRY, entry.getDate(), entry.getTime(),
                    entry.getStartTime(), entry.getEndTime(), entry.getUsername());
        }
        return entry;
    }

    public EntryCommandDTO editEntry(EntryFormDTO entryFormDTO, String username) {
        EntryCommandDTO entry = convertEntryFormDTOToCommandDTO(entryFormDTO, username);
        logger.debug("Edit Entry Before Convert: " + entryFormDTO.toString() + " " + username);
        logger.debug("Edit Entry: " + entry.toString());
        if (checkExistEntry(entry.getDate(), entry.getUsername())) {
            jdbcTemplate.update(EDIT_ENTRY, entry.getStartTime(), entry.getEndTime(),
                    entry.getTime(), entry.getDate(), entry.getUsername());
        }
        return entry;
    }

    public int deleteEntry(EntryDeleteFormDTO entryDeleteFormDTO, String username) {
        logger.debug("Delete Entry: " + entryDeleteFormDTO + " " + username);
        return jdbcTemplate.update(DELETE_ENTRY, username, entryDeleteFormDTO.getDate(), entryDeleteFormDTO.getIndex());
    }

    public boolean approveEntry(String index) {
        logger.debug("Approve Entry: " + index);
        return (jdbcTemplate.update(APPROVE_ENTRY, index) > 0);
    }

    public boolean notApproveEntry(String index) {
        logger.debug("Not Approve Entry: " + index);
        return (jdbcTemplate.update(NOT_APPROVE_ENTRY, index) > 0);
    }

    private EntryCommandDTO convertEntryFormDTOToCommandDTO(EntryFormDTO entryFormDTO, String username) {
        EntryCommandDTO entry = new EntryCommandDTO.EntryCommandDTOBuilder()
                .addDate(entryFormDTO.getDate())
                .addStartTime(entryFormDTO.getTimeStart())
                .addEndTime(entryFormDTO.getTimeEnd())
                .addUsername(username)
                .build();
        return entry;
    }

    private boolean checkExistEntry(Date date, String username) {
        Integer count = jdbcTemplate.queryForObject(CHECK_ENTRY, Integer.class, date, username);
        return count != null && count > 0;
    }

}
