package be.bstorm.bf_java2024_stockmanagement.api.models.dtos.user;

import be.bstorm.bf_java2024_stockmanagement.dl.entities.Role;
import be.bstorm.bf_java2024_stockmanagement.dl.entities.person.User;

import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * Data Transfer Object (DTO) for summarizing information about a {@link User}.
 * This record provides essential details of a user, including their unique ID, email, and roles.
 *
 * <p>Fields:
 * <ul>
 * <li>{@code id} - The unique identifier of the user.</li>
 * <li>{@code email} - The email address of the user.</li>
 * <li>{@code roles} - The set of roles assigned to the user, represented by role names.</li>
 * </ul>
 * </p>
 *
 * <p>Methods:
 * <ul>
 * <li>{@link #fromUser(User)} - Converts a {@link User} entity into a {@link UserDTO}.</li>
 * </ul>
 * </p>
 *
 * @see User
 */
public record UserDTO(
        UUID id,
        String email,
        Set<String> roles
) {
    /**
     * Converts a {@link User} entity into a {@link UserDTO} instance.
     *
     * @param user The {@link User} entity to convert.
     * @return A {@link UserDTO} with data populated from the given user entity.
     */
    public static UserDTO fromUser(User user) {
        return new UserDTO(
                user.getId(),
                user.getEmail(),
                user.getRoles().stream()
                        .map(Role::getName)
                        .collect(Collectors.toSet())
        );
    }
}
