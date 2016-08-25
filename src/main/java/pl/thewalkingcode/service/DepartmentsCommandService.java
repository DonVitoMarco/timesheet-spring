package pl.thewalkingcode.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;


@Component
public class DepartmentsCommandService implements IDepartmentsCommandService {

    private static final String DELETE_DEPARTMENTS = "DELETE FROM departments WHERE departments.DEPARTMENT_NAME=?";
    private static final String ADD_DEPARTMENTS = "INSERT INTO departments(departments.DEPARTMENT_NAME) VALUES (?)";

    JdbcTemplate jdbcTemplate;

    @Autowired
    public DepartmentsCommandService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public boolean deleteDepartments(String name) {
        return (jdbcTemplate.update(DELETE_DEPARTMENTS, name) > 0);
    }

    public boolean addDepartments(String name) {
        return (jdbcTemplate.update(ADD_DEPARTMENTS, name) > 0);
    }

}
