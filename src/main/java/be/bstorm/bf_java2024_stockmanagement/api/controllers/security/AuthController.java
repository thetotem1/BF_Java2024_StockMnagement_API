package be.bstorm.bf_java2024_stockmanagement.api.controllers.security;

import be.bstorm.bf_java2024_stockmanagement.api.models.dtos.user.UserDTO;
import be.bstorm.bf_java2024_stockmanagement.api.models.dtos.user.UserTokenDTO;
import be.bstorm.bf_java2024_stockmanagement.api.models.forms.user.LoginForm;
import be.bstorm.bf_java2024_stockmanagement.api.models.forms.user.RegisterForm;
import be.bstorm.bf_java2024_stockmanagement.bll.services.security.AuthService;
import be.bstorm.bf_java2024_stockmanagement.dl.entities.person.User;
import be.bstorm.bf_java2024_stockmanagement.il.utils.JwtUtils;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller for handling authentication and registration endpoints.
 * Provides endpoints for user login and registration with JWT-based authentication.
 *
 * <p>Endpoints:
 * <ul>
 * <li>{@link #login(LoginForm)} - Authenticates a user and returns a JWT token.</li>
 * <li>{@link #register(RegisterForm)} - Registers a new user.</li>
 * </ul>
 * </p>
 *
 * @see AuthService
 * @see JwtUtils
 */
@RestController
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;
    private final JwtUtils jwtUtils;

    /**
     * Authenticates a user with the provided credentials and returns a token.
     *
     * @param form The {@link LoginForm} containing login credentials.
     * @return A {@link ResponseEntity} containing a {@link UserTokenDTO} with the user details and JWT token.
     */
    @PostMapping("/login")
    public ResponseEntity<UserTokenDTO> login(
            @Valid @RequestBody LoginForm form
    ) {
        User user = authService.login(form.toUser());
        UserDTO dto = UserDTO.fromUser(user);
        UserTokenDTO tokenDTO = new UserTokenDTO(dto, jwtUtils.generateToken(user));
        return ResponseEntity.ok(tokenDTO);
    }

    /**
     * Registers a new user with the provided registration information.
     *
     * @param form The {@link RegisterForm} containing registration data.
     * @return A {@link ResponseEntity} with no content if the registration is successful.
     */
    @PostMapping("/register")
    public ResponseEntity<Void> register(
            @Valid @RequestBody RegisterForm form
    ) {
        authService.register(form.toUser());
        return ResponseEntity.noContent().build();
    }
}
