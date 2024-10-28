package be.bstorm.bf_java2024_stockmanagement.api.controllers.security;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AuthController {

    @PreAuthorize("isAnonymous()")
    @PostMapping("/login")
    public String login() {
        return "auth/login";
    }
}
