package be.bstorm.bf_java2024_stockmanagement.api.models.forms.user;

import be.bstorm.bf_java2024_stockmanagement.dl.entities.person.User;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record RegisterForm(
        @NotBlank
        String firstName,
        @NotBlank
        String lastName,
        @NotBlank @Email
        String email
) {

    public User toUser(){
        return new User(firstName,lastName,email);
    }
}
