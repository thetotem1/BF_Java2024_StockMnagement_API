package be.bstorm.bf_java2024_stockmanagement.dl.entities.order;

import be.bstorm.bf_java2024_stockmanagement.dl.entities.person.Client;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Represents an order placed by a client in the stock management system, extending the {@code Order} class.
 * Each client order is associated with a specific client.
 *
 * <p>Fields:
 * <ul>
 * <li>{@code client} - The client who placed the order.</li>
 * </ul>
 * </p>
 *
 * <p>Constructors:
 * <ul>
 * <li>{@link #ClientOrder(UUID, LocalDateTime, String, Client)} - Constructs a ClientOrder with specified ID, order date, comment, and client.</li>
 * </ul>
 * </p>
 *
 * @see Order
 * @see Client
 */
@Entity
@Getter @Setter
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class ClientOrder extends Order {

    /**
     * The client who placed the order.
     */
    @ManyToOne(fetch = FetchType.EAGER)
    private Client client;

    /**
     * Constructs a ClientOrder with the specified ID, order date, comment, and client.
     *
     * @param id The unique identifier of the order.
     * @param orderDate The date and time of the order.
     * @param comment An optional comment associated with the order.
     * @param client The client who placed the order.
     */
    public ClientOrder(UUID id, LocalDateTime orderDate, String comment, Client client) {
        super(id, orderDate, comment);
        this.client = client;
    }
}
