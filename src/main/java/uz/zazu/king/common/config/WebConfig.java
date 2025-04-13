package uz.zazu.king.common.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Value("${flag.dev}")
    private boolean dev;

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        if (dev) {
            registry
                    .addMapping("/api/**")
                    .allowedOrigins("*")
                    .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                    .allowedHeaders("Content-Type", "Authorization", "Accept", "X-Requested-With", "Origin")
                    .exposedHeaders("Authorization")
                    .allowCredentials(true);
        } else {
            registry
                    .addMapping("/api/**")
                    .allowedOrigins("http://localhost:3001")
                    .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                    .allowedHeaders("*")
                    .allowCredentials(true);
        }

    }
}
