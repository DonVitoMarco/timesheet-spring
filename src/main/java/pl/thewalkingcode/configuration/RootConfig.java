package pl.thewalkingcode.configuration;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import pl.thewalkingcode.controller.ControllerComponentScanner;
import pl.thewalkingcode.service.ServiceComponentScanner;
import pl.thewalkingcode.validation.ValidationComponentScanner;


@Configuration
@ComponentScan(basePackageClasses = {ConfigurationComponentScanner.class, ControllerComponentScanner.class,
        ServiceComponentScanner.class, ValidationComponentScanner.class})
public class RootConfig {

}
