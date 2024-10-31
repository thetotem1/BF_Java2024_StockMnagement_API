package be.bstorm.bf_java2024_stockmanagement.il.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

/**
 * Configures security settings for the stock management system using Spring Security.
 * It enables method-level security and provides basic HTTP security configurations such as disabling CSRF and CORS.
 *
 * <p>Configurations:
 * <ul>
 * <li>{@link #passwordEncoder()} - Configures a BCrypt password encoder bean.</li>
 * <li>{@link #securityFilterChain(HttpSecurity)} - Defines the security filter chain, allowing all requests by default.</li>
 * </ul>
 * </p>
 *
 * @see BCryptPasswordEncoder
 * @see SecurityFilterChain
 */
@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

    /**
     * Configures a {@link PasswordEncoder} bean using the BCrypt hashing algorithm.
     * This bean is used for encoding passwords within the security configuration.
     *
     * @return A {@link BCryptPasswordEncoder} instance.
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * Defines the security filter chain for HTTP requests.
     * Configures HTTP security to disable CSRF and CORS and permits all incoming requests.
     *
     * @param http The {@link HttpSecurity} to customize.
     * @return A {@link SecurityFilterChain} with configured settings.
     * @throws Exception if an error occurs while configuring the security settings.
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .cors(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(r ->
                        r.anyRequest().permitAll());

        return http.build();
    }
}
