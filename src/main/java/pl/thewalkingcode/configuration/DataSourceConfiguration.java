package pl.thewalkingcode.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import pl.thewalkingcode.controller.HomeControllerComponentScanner;

import javax.sql.DataSource;


@Configuration
@ComponentScan(basePackageClasses = {ConfigurationComponentScanner.class, HomeControllerComponentScanner.class})
@PropertySource("classpath:db.properties")
public class DataSourceConfiguration {

    @Autowired
    Environment env;

    private static final String PROPERTY_DRIVER = "db.driver";
    private static final String PROPERTY_URL = "db.url";
    private static final String PROPERTY_USER = "db.user";
    private static final String PROPERTY_PASSWORD = "db.password";

    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(env.getProperty(PROPERTY_DRIVER));
        dataSource.setUrl(env.getProperty(PROPERTY_URL));
        dataSource.setUsername(env.getProperty(PROPERTY_USER));
        dataSource.setPassword(env.getProperty(PROPERTY_PASSWORD));
        return dataSource;
    }

    @Bean
    public JdbcTemplate jdbcTemplate(DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }

}
