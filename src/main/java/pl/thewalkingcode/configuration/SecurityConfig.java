package pl.thewalkingcode.configuration;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import pl.thewalkingcode.controller.HomeControllerComponentScanner;


@EnableWebSecurity
@Configuration
@ComponentScan(basePackageClasses = {ConfigurationComponentScanner.class, HomeControllerComponentScanner.class})
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication().withUser("marek").password("marek").roles("USER");
        auth.inMemoryAuthentication().withUser("norman").password("jayden").roles("USER");
        auth.inMemoryAuthentication().withUser("admin").password("admin").roles("ADMIN");
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
