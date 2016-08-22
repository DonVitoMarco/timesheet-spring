package pl.thewalkingcode.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;


@Component
public class UserQueryService implements IUserQueryService {

    private static final String CHECK_USERNAME = "SELECT COUNT(*) FROM users WHERE users.USERNAME = ?";

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public UserQueryService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public boolean checkExistUsername(String username) {
        Integer count = jdbcTemplate.queryForObject(CHECK_USERNAME, Integer.class, username);
        return count != null && count > 0;
    }

}
