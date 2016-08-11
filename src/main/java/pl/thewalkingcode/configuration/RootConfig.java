package pl.thewalkingcode.configuration;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import pl.thewalkingcode.controller.ControllerComponentScanner;
import pl.thewalkingcode.repository.RepositoryComponentScanner;
import pl.thewalkingcode.service.ServiceComponentScanner;


@Configuration
@ComponentScan(basePackageClasses = {ConfigurationComponentScanner.class, ControllerComponentScanner.class,
        ServiceComponentScanner.class, RepositoryComponentScanner.class})
public class RootConfig {

}
