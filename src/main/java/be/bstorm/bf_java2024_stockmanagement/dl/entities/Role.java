package be.bstorm.bf_java2024_stockmanagement.dl.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.*;

import java.util.UUID;

/**
 * Represents a user role within the system, defining specific permissions or access levels.
 * Each role has a unique name and inherits common fields from the {@code BaseEntity} class.
 *
 * <p>Fields:
 * <ul>
 * <li>{@code name} - The unique name of the role (required, max length 20).</li>
 * </ul>
 * </p>
 *
 * @see BaseEntity
 */
@Entity
@Getter @Setter
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class Role extends BaseEntity {

    /**
     * The unique name of the role, such as "ADMIN" or "USER".
     * Cannot be null and has a maximum length of 20 characters.
     */
    @Column(unique = true, nullable = false, length = 20)
    private String name;

    /**
     * Constructs a Role with the specified ID and name.
     *
     * @param id The unique identifier of the role.
     * @param name The name of the role.
     */
    public Role(UUID id, String name) {
        super(id);
        this.name = name;
    }
}
