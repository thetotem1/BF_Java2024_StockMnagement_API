package be.bstorm.bf_java2024_stockmanagement.dl.entities.person;

import be.bstorm.bf_java2024_stockmanagement.dl.entities.Address;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.UUID;

/**
 * Represents a supplier in the stock management system, extending the {@code Extern} class.
 * A supplier provides goods or services and is identified by a unique set of contact details.
 *
 * <p>Constructors:
 * <ul>
 * <li>{@link #Supplier(String, String, String, String, Address)} - Constructs a supplier with specified contact details.</li>
 * <li>{@link #Supplier(UUID, String, String, String, String, Address)} - Constructs a supplier with a specified ID and contact details.</li>
 * </ul>
 * </p>
 *
 * @see Extern
 * @see Address
 */
@Entity
@DiscriminatorValue("SUPPLIER")
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class Supplier extends Extern {

    /**
     * Constructs a Supplier with the specified name, email, phone number, and address.
     *
     * @param firstName The first name of the supplier contact.
     * @param lastName The last name of the supplier contact.
     * @param email The email address of the supplier.
     * @param phoneNumber The phone number of the supplier.
     * @param address The physical address of the supplier.
     */
    public Supplier(String firstName, String lastName, String email, String phoneNumber, Address address) {
        super(firstName, lastName, email, phoneNumber, address);
    }

    /**
     * Constructs a Supplier with the specified ID, name, email, phone number, and address.
     *
     * @param id The unique identifier of the supplier.
     * @param firstName The first name of the supplier contact.
     * @param lastName The last name of the supplier contact.
     * @param email The email address of the supplier.
     * @param phoneNumber The phone number of the supplier.
     * @param address The physical address of the supplier.
     */
    public Supplier(UUID id, String firstName, String lastName, String email, String phoneNumber, Address address) {
        super(id, firstName, lastName, email, phoneNumber, address);
    }
}
