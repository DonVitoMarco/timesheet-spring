package pl.thewalkingcode.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import pl.thewalkingcode.model.EntryCommandDTO;
import pl.thewalkingcode.model.EntryFormDTO;


@Component
public class EntriesCommandService implements IEntriesCommandService {

    private static final String INSERT_ENTRY = "INSERT INTO entries (date, time, start, end, user_id) " +
            "VALUES (?, ?, ?, ?, (SELECT users.USER_ID FROM users WHERE users.USERNAME = ?))";

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public EntriesCommandService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void addNewEntry(EntryFormDTO entryFormDTO, String username) {
        EntryCommandDTO entry = new EntryCommandDTO.EntryCommandDTOBuilder()
                .addDate(entryFormDTO.getDate())
                .addStartTime(entryFormDTO.getTimeStart())
                .addEndTime(entryFormDTO.getTimeEnd())
                .addUsername(username)
                .build();
        //TODO logger
        System.out.println(entry.toString());

        jdbcTemplate.update(INSERT_ENTRY, entry.getDate(), entry.getTime(),
                entry.getStartTime(), entry.getEndTime(), entry.getUsername());
    }

}
