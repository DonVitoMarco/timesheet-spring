package pl.thewalkingcode.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import pl.thewalkingcode.controller.ControllerComponentScanner;
import pl.thewalkingcode.service.ServiceComponentScanner;
import pl.thewalkingcode.validation.ValidationComponentScanner;

import javax.sql.DataSource;


@EnableWebSecurity
@Configuration
@ComponentScan(basePackageClasses = {ConfigurationComponentScanner.class, ControllerComponentScanner.class,
        ServiceComponentScanner.class, ValidationComponentScanner.class})
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    DataSource dataSource;


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication().dataSource(dataSource)
                .usersByUsernameQuery("SELECT users.USERNAME, users.PASSWORD, users.ENABLE FROM users WHERE users.USERNAME=?")
                .authoritiesByUsernameQuery("SELECT users.USERNAME, roles.ROLES_NAME FROM users INNER JOIN roles ON users.ROLES_ID = roles.ROLES_ID WHERE users.USERNAME=?")
                .passwordEncoder(passwordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .formLogin()
                .loginPage("/login")
                .defaultSuccessUrl("/").and()
                .logout()
                .logoutUrl("/logout")
                .logoutSuccessUrl("/").and()

                .exceptionHandling()
                .accessDeniedPage("/denied").and()

                .authorizeRequests()
                .antMatchers("/ajax/query/entries", "/ajax/query/departments", "/ajax/query/users").access("hasRole('ROLE_ADMIN')")
                .antMatchers("/ajax/query/show").access("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
                .antMatchers("/ajax/command/add", "/ajax/command/edit", "/ajax/command/del").access("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
                .antMatchers("/ajax/command/changeRole", "/ajax/command/changeEnable",
                        "/ajax/command/approve", "/ajax/command/notapprove", "/ajax/command/changeDepartment").access("hasRole('ROLE_ADMIN')")
                .antMatchers("/manage").access("hasRole('ROLE_ADMIN')")
                .antMatchers("/timesheet").access("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
                .antMatchers("/logout").access("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
                .antMatchers("/register").permitAll()
                .antMatchers("/login").permitAll()
                .antMatchers("/about").permitAll()
                .antMatchers("/").permitAll().and()

                .csrf().disable();

//                .requiresChannel()
//                    .antMatchers("/").requiresInsecure();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
