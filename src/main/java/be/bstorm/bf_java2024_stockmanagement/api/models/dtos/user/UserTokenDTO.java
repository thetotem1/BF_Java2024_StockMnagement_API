package be.bstorm.bf_java2024_stockmanagement.api.models.dtos.user;

/**
 * Data Transfer Object (DTO) for encapsulating a {@link UserDTO} with an authentication token.
 * This record is used to send user information along with a JWT or similar authentication token.
 *
 * <p>Fields:
 * <ul>
 * <li>{@code user} - The {@link UserDTO} object containing user details.</li>
 * <li>{@code token} - The authentication token associated with the user.</li>
 * </ul>
 * </p>
 *
 * @see UserDTO
 */
public record UserTokenDTO(
        UserDTO user,
        String token
) {
}
