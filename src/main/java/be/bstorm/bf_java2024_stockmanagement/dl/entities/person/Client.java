package be.bstorm.bf_java2024_stockmanagement.dl.entities.person;

import be.bstorm.bf_java2024_stockmanagement.dl.entities.Address;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.UUID;

/**
 * Represents a client in the stock management system, extending the {@code Extern} class.
 * A client is an external entity that interacts with the system, identified by personal and contact details.
 *
 * <p>Constructors:
 * <ul>
 * <li>{@link #Client(String, String, String, String, Address)} - Constructs a client with specified personal details, phone number, and address.</li>
 * <li>{@link #Client(UUID, String, String, String, String, Address)} - Constructs a client with a specified ID, personal details, phone number, and address.</li>
 * </ul>
 * </p>
 *
 * @see Extern
 * @see Address
 */
@Entity
@DiscriminatorValue("CLIENT")
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class Client extends Extern {

    /**
     * Constructs a Client with the specified personal details, phone number, and address.
     *
     * @param firstName The first name of the client.
     * @param lastName The last name of the client.
     * @param email The email address of the client.
     * @param phoneNumber The phone number of the client.
     * @param address The physical address of the client.
     */
    public Client(String firstName, String lastName, String email, String phoneNumber, Address address) {
        super(firstName, lastName, email, phoneNumber, address);
    }

    /**
     * Constructs a Client with the specified ID, personal details, phone number, and address.
     *
     * @param id The unique identifier of the client.
     * @param firstName The first name of the client.
     * @param lastName The last name of the client.
     * @param email The email address of the client.
     * @param phoneNumber The phone number of the client.
     * @param address The physical address of the client.
     */
    public Client(UUID id, String firstName, String lastName, String email, String phoneNumber, Address address) {
        super(id, firstName, lastName, email, phoneNumber, address);
    }
}
