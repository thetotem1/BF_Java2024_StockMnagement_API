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
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;
    private final JwtUtils jwtUtils;

    @PostMapping("/login")
    public ResponseEntity<UserTokenDTO> login(
            @Valid @RequestBody LoginForm form
            ) {
        User user = authService.login(form.toUser());
        UserDTO dto = UserDTO.fromUser(user);
        UserTokenDTO tokenDTO = new UserTokenDTO(dto, jwtUtils.generateToken(user));
        return ResponseEntity.ok(tokenDTO);
    }

    @PostMapping("/register")
    public ResponseEntity<Void> register(
            @Valid @RequestBody RegisterForm form
    ){
        authService.register(form.toUser());
        return ResponseEntity.noContent().build();
    }
}
