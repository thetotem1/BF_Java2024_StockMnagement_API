package be.bstorm.bf_java2024_stockmanagement.dl.entities.person;

import be.bstorm.bf_java2024_stockmanagement.dl.entities.Address;
import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

/**
 * Represents an external person entity within the system, extending {@code Person} and containing additional details
 * such as phone number and address. This class is designed to be extended by other entities, with a single table
 * inheritance strategy for subclasses.
 *
 * <p>Fields:
 * <ul>
 * <li>{@code phoneNumber} - The phone number of the external person (optional, max length 17).</li>
 * <li>{@code address} - The physical address of the external person (embedded).</li>
 * </ul>
 * </p>
 *
 * <p>Constructors:
 * <ul>
 * <li>{@link #Extern(UUID, String, String, String, String, Address)} - Constructs an Extern with a specified ID, personal details, phone number, and address.</li>
 * <li>{@link #Extern(String, String, String, String, Address)} - Constructs an Extern with specified personal details, phone number, and address.</li>
 * </ul>
 * </p>
 *
 * @see Person
 * @see Address
 */
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "extern_type")
@Getter @Setter
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public abstract class Extern extends Person {

    /**
     * The phone number of the external person.
     * Optional; max length of 17 characters.
     */
    @Column(length = 17)
    private String phoneNumber;

    /**
     * The physical address of the external person.
     * This field is embedded within the entity.
     */
    @Embedded
    private Address address;

    /**
     * Constructs an Extern with the specified ID, personal details, phone number, and address.
     *
     * @param id The unique identifier of the external person.
     * @param firstName The first name of the external person.
     * @param lastName The last name of the external person.
     * @param email The email address of the external person.
     * @param phoneNumber The phone number of the external person.
     * @param address The physical address of the external person.
     */
    public Extern(UUID id, String firstName, String lastName, String email, String phoneNumber, Address address) {
        super(id, firstName, lastName, email);
        this.phoneNumber = phoneNumber;
        this.address = address;
    }

    /**
     * Constructs an Extern with the specified personal details, phone number, and address.
     *
     * @param firstName The first name of the external person.
     * @param lastName The last name of the external person.
     * @param email The email address of the external person.
     * @param phoneNumber The phone number of the external person.
     * @param address The physical address of the external person.
     */
    public Extern(String firstName, String lastName, String email, String phoneNumber, Address address) {
        super(firstName, lastName, email);
        this.phoneNumber = phoneNumber;
        this.address = address;
    }
}
