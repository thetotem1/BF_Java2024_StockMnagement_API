package be.bstorm.bf_java2024_stockmanagement.api.models.forms.user;

import be.bstorm.bf_java2024_stockmanagement.dl.entities.person.User;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record LoginForm(
        @NotBlank @Email
        String email,
        @NotBlank
        String password
) {

    public User toUser(){
        return new User(email,password);
    }
}
