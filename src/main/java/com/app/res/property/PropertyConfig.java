package com.app.res.property;

import com.app.res.agents.agentRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@CrossOrigin(origins = "http://localhost:4200")
@EnableWebMvc
public class PropertyConfig {
    @Bean(name = "property")
    CommandLineRunner commandLineRunner(PropertyRepository repository) {
        return args -> {


        };
    }
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("http://localhost:4200");
        registry.addMapping("/*").
                allowedOrigins("*").
                allowedMethods("*").
                allowedHeaders("*").
                allowCredentials(true);
    }
}
