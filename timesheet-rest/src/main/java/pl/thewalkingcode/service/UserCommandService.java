package pl.thewalkingcode.service;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import pl.thewalkingcode.model.UserRegisterDTO;


@Component
public class UserCommandService implements IUserCommandService {

    private final static Logger logger = Logger.getLogger(UserCommandService.class);

    private static final String REGISTER_USER = "INSERT INTO users (users.USERNAME, users.PASSWORD) VALUES (?, ?)";
    private static final String CHANGE_ROLE = "UPDATE users SET users.ROLES_ID = (users.ROLES_ID ^ 1) WHERE users.USER_ID = ?";
    private static final String CHANGE_ENABLE = "UPDATE users SET users.ENABLE = (users.ENABLE ^ 1) WHERE users.USER_ID = ?";

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public UserCommandService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    public int registerUser(UserRegisterDTO userRegisterDTO) {
        logger.debug("Register User: " + userRegisterDTO.toString());
        return jdbcTemplate.update(REGISTER_USER, userRegisterDTO.getUsername(), new BCryptPasswordEncoder().encode(userRegisterDTO.getPassword()));
    }

    public int changeRoleUser(String userId) {
        logger.debug("Change Role User: " + userId);
        return jdbcTemplate.update(CHANGE_ROLE, userId);
    }

    public int changeEnableUser(String userId) {
        logger.debug("Check Enable User: " + userId);
        return jdbcTemplate.update(CHANGE_ENABLE, userId);
    }

}
