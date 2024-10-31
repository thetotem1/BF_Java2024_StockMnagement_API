package be.bstorm.bf_java2024_stockmanagement.dl.entities.person;

import be.bstorm.bf_java2024_stockmanagement.dl.entities.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

/**
 * Represents a person in the stock management system with common attributes such as first name, last name, and email.
 * This class is a base class for entities that involve personal information.
 *
 * <p>Fields:
 * <ul>
 * <li>{@code firstName} - The first name of the person (required, max length 123).</li>
 * <li>{@code lastName} - The last name of the person (required, max length 80).</li>
 * <li>{@code email} - The unique email of the person (required, max length 320).</li>
 * </ul>
 * </p>
 *
 * <p>Constructors:
 * <ul>
 * <li>{@link #Person(String)} - Constructs a person with an email.</li>
 * <li>{@link #Person(UUID, String, String, String)} - Constructs a person with an ID, first name, last name, and email.</li>
 * <li>{@link #Person(String, String, String)} - Constructs a person with a first name, last name, and email.</li>
 * </ul>
 * </p>
 *
 * @see BaseEntity
 */
@MappedSuperclass
@Getter @Setter
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public abstract class Person extends BaseEntity {

    /**
     * The first name of the person.
     * Cannot be null; max length of 123 characters.
     */
    @Column(nullable = false, length = 123)
    private String firstName;

    /**
     * The last name of the person.
     * Cannot be null; max length of 80 characters.
     */
    @Column(nullable = false, length = 80)
    private String lastName;

    /**
     * The unique email of the person.
     * Cannot be null; max length of 320 characters.
     */
    @Column(nullable = false, unique = true, length = 320)
    private String email;

    /**
     * Constructs a person with the specified email.
     *
     * @param email The email address of the person.
     */
    public Person(String email) {
        this.email = email;
    }

    /**
     * Constructs a person with the specified ID, first name, last name, and email.
     *
     * @param id The unique identifier of the person.
     * @param firstName The first name of the person.
     * @param lastName The last name of the person.
     * @param email The email address of the person.
     */
    public Person(UUID id, String firstName, String lastName, String email) {
        super(id);
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    /**
     * Constructs a person with the specified first name, last name, and email.
     *
     * @param firstName The first name of the person.
     * @param lastName The last name of the person.
     * @param email The email address of the person.
     */
    public Person(String firstName, String lastName, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }
}
