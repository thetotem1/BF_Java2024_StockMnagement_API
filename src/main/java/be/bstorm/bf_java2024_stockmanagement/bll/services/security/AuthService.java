package be.bstorm.bf_java2024_stockmanagement.bll.services.security;

import be.bstorm.bf_java2024_stockmanagement.dl.entities.person.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface AuthService extends UserDetailsService {

    void register(User user);
    User login(User user);
}
