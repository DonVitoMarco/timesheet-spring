package pl.thewalkingcode.configuration;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import pl.thewalkingcode.controller.HomeControllerComponentScanner;


@Configuration
@ComponentScan (basePackageClasses = { ConfigurationComponentScanner.class, HomeControllerComponentScanner.class })
public class RootConfig {

}
