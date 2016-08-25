package pl.thewalkingcode.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;


@Component
public class DepartmentsQueryService implements IDepartmentsQueryService {

    private static final String GET_DEPARTMENTS = "SELECT departments.DEPARTMENT_NAME FROM ts_dev.departments";

    JdbcTemplate jdbcTemplate;

    @Autowired
    public DepartmentsQueryService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    public List<String> getAllDepartments() {
        return jdbcTemplate.query(GET_DEPARTMENTS, new RowMapper<String>() {
            public String mapRow(ResultSet result, int rowNum) throws SQLException {
                return result.getString("DEPARTMENT_NAME");
            }
        });
    }

}
