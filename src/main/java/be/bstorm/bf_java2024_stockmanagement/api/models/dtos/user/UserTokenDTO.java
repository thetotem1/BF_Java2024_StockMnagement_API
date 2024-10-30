package be.bstorm.bf_java2024_stockmanagement.api.models.dtos.user;

public record UserTokenDTO(
        UserDTO user,
        String token
) {
}
