package pl.thewalkingcode.service;

import org.springframework.stereotype.Component;


@Component
public class EntriesCommandService implements IEntriesCommandService {

    private static final String INSERT_ENTRY = "INSERT INTO entries (date, time, start, end, user_id) " +
            "VALUES ('2016-08-10', ?, ?, ?, (SELECT users.USER_ID FROM users WHERE users.USERNAME = ?))";

    @Override
    public void addNewEntry() {

    }

}
