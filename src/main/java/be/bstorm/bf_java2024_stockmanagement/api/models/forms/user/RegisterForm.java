package be.bstorm.bf_java2024_stockmanagement.api.models.forms.user;

import be.bstorm.bf_java2024_stockmanagement.dl.entities.person.User;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

/**
 * Form Data Transfer Object (DTO) for capturing registration data.
 * This record is used to collect and validate user input for creating a new user.
 *
 * <p>Fields:
 * <ul>
 * <li>{@code firstName} - The first name of the user, required and cannot be blank.</li>
 * <li>{@code lastName} - The last name of the user, required and cannot be blank.</li>
 * <li>{@code email} - The email address of the user, required and must be in a valid email format.</li>
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
public record RegisterForm(
        @NotBlank String firstName,
        @NotBlank String lastName,
        @NotBlank @Email String email
) {

    /**
     * Converts this form data into a {@link User} entity.
     *
     * @return A new {@link User} populated with data from this form.
     */
    public User toUser(){
        return new User(firstName, lastName, email);
    }
}
