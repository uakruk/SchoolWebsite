package ua.edu.kordelschool.config;

import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.RestController;
import ua.edu.kordelschool.KodreslchoolApplication;

import java.io.IOException;

/**
 * @author Yaroslav Kruk on 12/8/16.
 *         e-mail: yakruck@gmail.com
 *         GitHub: https://github.com/uakruk
 * @version 1.0
 * @since 1.8
 */
@Configuration
@ComponentScan(value = {"ua.edu.kordelschool"},
                basePackageClasses = {KodreslchoolApplication.class},
                excludeFilters = @ComponentScan.Filter({
                        Controller.class, Configuration.class, ControllerAdvice.class, RestController.class
                }))
public class ApplicationConfig {

    public static PropertyPlaceholderConfigurer propertyPlaceholderConfigurer() throws IOException {
        PropertyPlaceholderConfigurer ppc = new PropertyPlaceholderConfigurer();
        ppc.setIgnoreUnresolvablePlaceholders(true);
        ppc.setLocations(new PathMatchingResourcePatternResolver().getResources("classpath:/**.properties"));

        return ppc;
    }
}
