package com.app.res.agents;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
@Configuration
@CrossOrigin(origins = "http://localhost:4200")
@EnableWebMvc
public class agentsConfig implements WebMvcConfigurer {
    @Bean(name="bg")
    CommandLineRunner commandLineRunner(agentRepository repository){
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
