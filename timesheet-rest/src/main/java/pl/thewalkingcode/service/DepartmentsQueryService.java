package pl.thewalkingcode.service;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
public class DepartmentsQueryService implements IDepartmentsQueryService {

    private final static Logger logger = Logger.getLogger(DepartmentsQueryService.class);

    private static final String GET_DEPARTMENTS = "SELECT departments.DEPARTMENT_NAME FROM ts_dev.departments";

    JdbcTemplate jdbcTemplate;

    @Autowired
    public DepartmentsQueryService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    public List<String> getAllDepartments() {
        logger.debug("Get All Departments");
        return jdbcTemplate.query(GET_DEPARTMENTS, (rs, rowNum) -> {
            return rs.getString("DEPARTMENT_NAME");
        });
    }

}
