package pl.thewalkingcode.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import pl.thewalkingcode.controller.HomeControllerComponentScanner;

import javax.sql.DataSource;


@EnableWebSecurity
@Configuration
@ComponentScan(basePackageClasses = {ConfigurationComponentScanner.class, HomeControllerComponentScanner.class})
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    DataSource dataSource;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication().dataSource(dataSource)
                .usersByUsernameQuery("SELECT username, password, enable FROM users WHERE username=?")
                .authoritiesByUsernameQuery("SELECT username, 'ROLE_USER' FROM users WHERE username=?");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .formLogin()
                .loginPage("/login")
                .defaultSuccessUrl("/timesheet").and()
                .logout()
                .logoutUrl("/logout")
                .logoutSuccessUrl("/").and()

                .exceptionHandling()
                .accessDeniedPage("/403").and()

                .authorizeRequests()
                .antMatchers("/timesheet").access("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
                .antMatchers("/logout").access("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
                .antMatchers("/login").permitAll()
                .antMatchers("/").permitAll();

//                .requiresChannel()
//                    .antMatchers("/").requiresInsecure();
    }

}
