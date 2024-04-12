package Blacklist.Manager.configuration;


import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@Configuration
public class CorsConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        // Define CORS policy details
        registry.addMapping("/**")  // Apply CORS to all routes
                .allowedOrigins("*")  // Allow this origin, use "*" for all if needed
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")  // Specify allowed methods
                .allowedHeaders("*")  // Allow all headers
                .allowCredentials(true)  // Allow credentials such as cookies, authorization headers, etc.
                .maxAge(3600);  // How long the response from the pre-flight request can be cached
    }
    
}
