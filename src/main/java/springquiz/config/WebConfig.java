package springquiz.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * Configuration class for the API.
 */
@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "springquiz.*")
public class WebConfig {
}

