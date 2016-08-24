package pl.thewalkingcode.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import pl.thewalkingcode.model.UserRegisterDTO;


@Component
public class UserCommandService implements IUserCommandService {

    private static final String REGISTER_USER = "INSERT INTO users (users.USERNAME, users.PASSWORD) VALUES (?, ?)";
    private static final String CHANGE_ROLE = "UPDATE users SET users.ROLES_ID = (users.ROLES_ID ^ 1) WHERE users.USER_ID = ?";
    private static final String CHANGE_ENABLE = "UPDATE users SET users.ENABLE = (users.ENABLE ^ 1) WHERE users.USER_ID = ?";

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public UserCommandService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public int registerUser(UserRegisterDTO userRegisterDTO) {
        return jdbcTemplate.update(REGISTER_USER, userRegisterDTO.getUsername(), new BCryptPasswordEncoder().encode(userRegisterDTO.getPassword()));
    }

    public boolean changeRoleUser(String userId) {
        return (jdbcTemplate.update(CHANGE_ROLE, userId) > 0);
    }

    public boolean changeEnableUser(String userId) {
        return (jdbcTemplate.update(CHANGE_ENABLE, userId) > 0);
    }

}
