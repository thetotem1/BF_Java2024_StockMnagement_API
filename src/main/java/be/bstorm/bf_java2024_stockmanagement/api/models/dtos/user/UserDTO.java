package be.bstorm.bf_java2024_stockmanagement.api.models.dtos.user;

import be.bstorm.bf_java2024_stockmanagement.dl.entities.Role;
import be.bstorm.bf_java2024_stockmanagement.dl.entities.person.User;

import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

public record UserDTO(
        UUID id,
        String email,
        Set<String> roles
) {
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
