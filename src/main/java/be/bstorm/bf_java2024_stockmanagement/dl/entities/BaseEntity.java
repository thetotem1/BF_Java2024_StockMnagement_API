package be.bstorm.bf_java2024_stockmanagement.dl.entities;

import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Represents a base entity with common fields for other entities to inherit.
 * Provides an ID, timestamps for creation and update, and utilizes Lombok annotations
 * for convenience.
 *
 * <p>Fields:
 * <ul>
 * <li>{@code id} - Unique identifier for the entity (UUID).</li>
 * <li>{@code createdAt} - Timestamp indicating when the entity was created (auto-generated).</li>
 * <li>{@code updatedAt} - Timestamp indicating the last update to the entity (auto-generated).</li>
 * </ul>
 * </p>
 *
 * @see jakarta.persistence.Id
 * @see MappedSuperclass
 */
@Getter
@NoArgsConstructor
@EqualsAndHashCode(of = {"id"})
@ToString
@MappedSuperclass
public abstract class BaseEntity {

    /**
     * Unique identifier for the entity, represented as a UUID.
     */
    @Id
    @Setter
    private UUID id;

    /**
     * Timestamp for when the entity was created.
     * Automatically generated when the entity is first persisted.
     */
    @CreationTimestamp
    private LocalDateTime createdAt;

    /**
     * Timestamp for the last update to the entity.
     * Automatically updated when the entity is modified.
     */
    @UpdateTimestamp
    private LocalDateTime updatedAt;

    /**
     * Constructs a BaseEntity with the specified UUID.
     *
     * @param id The unique identifier for the entity.
     */
    public BaseEntity(UUID id) {
        this.id = id;
    }
}
