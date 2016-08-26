package pl.thewalkingcode.service;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import pl.thewalkingcode.model.UserQueryDTO;

import java.util.List;


@Component
public class UserQueryService implements IUserQueryService {

    private final static Logger logger = Logger.getLogger(UserQueryService.class);

    private static final String CHECK_USERNAME = "SELECT COUNT(*) FROM users WHERE users.USERNAME = ?";
    private static final String GET_USERS = "SELECT users.USER_ID, users.USERNAME, users.ENABLE, roles.ROLES_NAME, departments.DEPARTMENT_NAME FROM users " +
            "INNER JOIN roles ON users.ROLES_ID = roles.ROLES_ID INNER JOIN departments ON users.DEPARTMENTS_ID = departments.DEPARTMENT_ID";

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public UserQueryService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    public List<UserQueryDTO> getAllUsers() {
        logger.debug("Get All Users");
        return jdbcTemplate.query(GET_USERS, (result, rowNum) -> {
            UserQueryDTO user = new UserQueryDTO();
            user.setIndex(result.getInt("USER_ID"));
            user.setUsername(result.getString("USERNAME"));
            user.setEnable(result.getBoolean("ENABLE"));
            user.setRoles(result.getString("ROLES_NAME"));
            user.setDepartment(result.getString("DEPARTMENT_NAME"));
            return user;
        });
    }

    public boolean checkExistUsername(String username) {
        logger.debug("Check Exist Username " + username);
        Integer count = jdbcTemplate.queryForObject(CHECK_USERNAME, Integer.class, username);
        return count != null && count > 0;
    }

}
