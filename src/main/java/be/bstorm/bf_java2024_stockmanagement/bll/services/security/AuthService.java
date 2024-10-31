package be.bstorm.bf_java2024_stockmanagement.bll.services.security;

import be.bstorm.bf_java2024_stockmanagement.dl.entities.person.User;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * Interface for authentication and user management services in the stock management system.
 * Extends {@link UserDetailsService} to integrate with Spring Security's authentication process.
 *
 * <p>Methods:
 * <ul>
 * <li>{@link #register(User)} - Registers a new user in the system.</li>
 * <li>{@link #login(User)} - Authenticates a user by validating their credentials.</li>
 * </ul>
 * </p>
 *
 * @see UserDetailsService
 * @see User
 */
public interface AuthService extends UserDetailsService {

    /**
     * Registers a new user in the system.
     *
     * @param user The {@link User} to register.
     */
    void register(User user);

    /**
     * Authenticates a user by validating their email and password.
     *
     * @param user The {@link User} containing login credentials.
     * @return The authenticated {@link User}.
     */
    User login(User user);
}
