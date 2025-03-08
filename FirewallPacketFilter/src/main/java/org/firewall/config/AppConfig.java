package org.firewall.config;

import org.firewall.utils.CommandExecutor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.web.reactive.config.EnableWebFlux;
import org.springframework.web.reactive.config.CorsRegistry;
import org.springframework.web.reactive.config.WebFluxConfigurer;

@Configuration
@EnableMongoRepositories(basePackages = "org.firewall.repository")  // Enable MongoDB repositories
@EnableWebFlux  // Enable WebFlux for reactive endpoints
public class AppConfig implements WebFluxConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("*")   // Allow all origins
                .allowedMethods("GET", "POST", "PUT", "DELETE")   // Allow specific HTTP methods
                .allowedHeaders("*");  // Allow all headers
    }

    @Bean
    public CommandExecutor firewallCommandExecutor() {
        return new CommandExecutor();
    }
}
