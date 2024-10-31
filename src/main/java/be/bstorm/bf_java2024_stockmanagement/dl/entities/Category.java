package be.bstorm.bf_java2024_stockmanagement.dl.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.*;

import java.util.UUID;

/**
 * Represents a Category entity that can be assigned to articles.
 * Each category has a unique designation and inherits common fields
 * from the {@code BaseEntity} class.
 *
 * <p>Fields:
 * <ul>
 * <li>{@code designation} - A unique designation for the category (required, max length 80).</li>
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
public class Category extends BaseEntity {

    /**
     * Unique designation for the category.
     * Cannot be null; max length of 80 characters.
     */
    @Column(nullable = false, unique = true, length = 80)
    private String designation;

    /**
     * Constructs a Category with the specified ID and designation.
     *
     * @param id The unique identifier of the category.
     * @param designation The designation of the category.
     */
    public Category(UUID id, String designation) {
        super(id);
        this.designation = designation;
    }
}
