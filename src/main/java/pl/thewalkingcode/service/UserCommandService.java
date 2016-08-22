package pl.thewalkingcode.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import pl.thewalkingcode.model.UserRegisterDTO;


@Component
public class UserCommandService implements IUserCommandService {

    private static final String REGISTER_USER = "INSERT INTO users (users.USERNAME, users.PASSWORD) VALUES (?, ?)";

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public UserCommandService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public int registerUser(UserRegisterDTO userRegisterDTO) {
        return jdbcTemplate.update(REGISTER_USER, userRegisterDTO.getUsername(), new BCryptPasswordEncoder().encode(userRegisterDTO.getPassword()));
    }

}
