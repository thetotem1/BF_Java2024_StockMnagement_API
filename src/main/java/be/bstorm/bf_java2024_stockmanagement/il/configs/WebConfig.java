package be.bstorm.bf_java2024_stockmanagement.il.configs;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Configures web settings for the stock management system, implementing {@link WebMvcConfigurer}.
 * This class defines resource handlers for serving static content such as images from a specified location.
 *
 * <p>Methods:
 * <ul>
 * <li>{@link #addResourceHandlers(ResourceHandlerRegistry)} - Adds a resource handler for serving images from a local directory.</li>
 * </ul>
 * </p>
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {

    /**
     * Adds a resource handler for serving images from the local file system.
     * Maps requests to "/images/**" to the "images" directory within the application's root directory.
     *
     * @param registry The {@link ResourceHandlerRegistry} to configure.
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/images/**")
                .addResourceLocations("file:" + System.getProperty("user.dir") + "/images/");
    }
}
