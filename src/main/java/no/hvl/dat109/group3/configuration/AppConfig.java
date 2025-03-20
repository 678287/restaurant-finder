package no.hvl.dat109.group3.configuration;
/**
 * A configuration class to define a bean for RestTemplate, so that we may use the API
 */

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class AppConfig {
    @Bean
    RestTemplate restTemplate() {
        return new RestTemplate();
    }
}