package pl.thewalkingcode.configuration;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import pl.thewalkingcode.controller.ControllerComponentScanner;


@Configuration
@ComponentScan (basePackageClasses = { ConfigurationComponentScanner.class, ControllerComponentScanner.class })
public class RootConfig {

}
