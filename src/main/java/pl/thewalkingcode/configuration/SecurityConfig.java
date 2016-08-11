package pl.thewalkingcode.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import pl.thewalkingcode.controller.ControllerComponentScanner;
import pl.thewalkingcode.service.ServiceComponentScanner;

import javax.sql.DataSource;


@EnableWebSecurity
@Configuration
@ComponentScan(basePackageClasses = {ConfigurationComponentScanner.class, ControllerComponentScanner.class,
        ServiceComponentScanner.class})
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    DataSource dataSource;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication().dataSource(dataSource)
                .usersByUsernameQuery("SELECT username, password, enable FROM users WHERE username=?")
                .authoritiesByUsernameQuery("SELECT users.username, roles.roles FROM users INNER JOIN roles ON users.id_roles=roles.id_roles WHERE users.username=?");
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
                .antMatchers("/manage").access("hasRole('ROLE_ADMIN')")
                .antMatchers("/timesheet").access("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
                .antMatchers("/logout").access("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
                .antMatchers("/register").permitAll()
                .antMatchers("/login").permitAll()
                .antMatchers("/").permitAll();

//                .requiresChannel()
//                    .antMatchers("/").requiresInsecure();
    }

}
