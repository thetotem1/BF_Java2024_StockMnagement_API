package be.bstorm.bf_java2024_stockmanagement.bll.services.security.impls;

import be.bstorm.bf_java2024_stockmanagement.bll.services.security.AuthService;
import be.bstorm.bf_java2024_stockmanagement.dal.repositories.UserRepository;
import be.bstorm.bf_java2024_stockmanagement.dl.entities.person.User;
import be.bstorm.bf_java2024_stockmanagement.il.utils.MailerThread;
import be.bstorm.bf_java2024_stockmanagement.il.utils.MailerUtils;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;

import java.util.Random;
import java.util.UUID;

/**
 * Implementation of the {@link AuthService} interface, providing registration, login, and user authentication services.
 * This service includes functionality for generating secure passwords, encoding passwords, and sending registration emails.
 *
 * <p>Methods:
 * <ul>
 * <li>{@link #register(User)} - Registers a new user with a generated password and sends a welcome email.</li>
 * <li>{@link #login(User)} - Authenticates a user by validating their email and password.</li>
 * <li>{@link #loadUserByUsername(String)} - Loads user details for authentication by username (email).</li>
 * <li>{@link #generatePassword(int)} - Generates a secure random password of specified length.</li>
 * </ul>
 * </p>
 *
 * @see AuthService
 * @see User
 */
@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final MailerUtils mailerUtils;

    /**
     * Registers a new user, generating a unique password and sending it to the user's email.
     * If the email is already associated with an existing user, an exception is thrown.
     *
     * @param user The {@link User} to register.
     * @throws UsernameNotFoundException If a user with the specified email already exists.
     */
    @Override
    @Transactional
    public void register(User user) {
        if (userRepository.existsByEmail(user.getEmail())) {
            throw new UsernameNotFoundException("User with email " + user.getEmail() + " already exists");
        }
        String password = generatePassword(49);
        user.setPassword(passwordEncoder.encode(password));
        user.setId(UUID.randomUUID());
        userRepository.save(user);

        Context context = new Context();
        context.setVariable("email", user.getEmail());
        context.setVariable("password", password);

        MailerThread mailerThread = mailerUtils.createThread(
                "Welcome",
                "sendPassword",
                context,
                user.getEmail()
        );
        new Thread(mailerThread).start();
    }

    /**
     * Authenticates a user by validating their email and password.
     *
     * @param user The {@link User} containing login details.
     * @return The authenticated {@link User}.
     * @throws RuntimeException If the password is incorrect.
     */
    @Override
    public User login(User user) {
        User existingUser = userRepository.findByEmail(user.getEmail()).orElseThrow();
        if (!passwordEncoder.matches(user.getPassword(), existingUser.getPassword())) {
            throw new RuntimeException("Wrong password");
        }
        return existingUser;
    }

    /**
     * Loads user details by email for authentication.
     *
     * @param email The email to load the user details for.
     * @return The {@link UserDetails} for the specified email.
     * @throws UsernameNotFoundException If no user is found with the specified email.
     */
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return userRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException(email));
    }

    /**
     * Generates a secure random password of the specified length, including uppercase, lowercase, digits, and special characters.
     *
     * @param length The length of the password to generate.
     * @return The generated password as a {@link String}.
     */
    private String generatePassword(int length) {
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789!@#$%^&*()-_=+[]{}|\\:;\"'<>,.?/";

        Random random = new Random();
        StringBuilder password = new StringBuilder();
        for (int i = 0; i < length; i++) {
            password.append(characters.charAt(random.nextInt(characters.length())));
        }
        return password.toString();
    }
}
