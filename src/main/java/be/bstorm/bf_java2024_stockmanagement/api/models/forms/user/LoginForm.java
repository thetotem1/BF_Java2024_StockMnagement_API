package be.bstorm.bf_java2024_stockmanagement.api.models.forms.user;

import be.bstorm.bf_java2024_stockmanagement.dl.entities.person.User;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

/**
 * Form Data Transfer Object (DTO) for capturing login credentials.
 * This record is used to collect and validate user input for login.
 *
 * <p>Fields:
 * <ul>
 * <li>{@code email} - The email address of the user, required and must be in valid email format.</li>
 * <li>{@code password} - The password of the user, required.</li>
 * </ul>
 * </p>
 *
 * <p>Methods:
 * <ul>
 * <li>{@link #toUser()} - Converts this form into a {@link User} entity.</li>
 * </ul>
 * </p>
 *
 * @see User
 */
public record LoginForm(
        @NotBlank @Email String email,
        @NotBlank String password
) {

    /**
     * Converts this form data into a {@link User} entity.
     *
     * @return A new {@link User} populated with data from this form.
     */
    public User toUser(){
        return new User(email, password);
    }
}
