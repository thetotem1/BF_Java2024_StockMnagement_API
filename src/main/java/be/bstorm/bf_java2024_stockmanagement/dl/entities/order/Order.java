package be.bstorm.bf_java2024_stockmanagement.dl.entities.order;

import be.bstorm.bf_java2024_stockmanagement.dl.entities.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Represents an order within the stock management system. This is a base class for different types of orders,
 * containing common fields such as order date and an optional comment. Uses a table-per-class inheritance strategy.
 *
 * <p>Fields:
 * <ul>
 * <li>{@code orderDate} - The date and time when the order was placed (required).</li>
 * <li>{@code comment} - An optional comment associated with the order.</li>
 * </ul>
 * </p>
 *
 * <p>Constructors:
 * <ul>
 * <li>{@link #Order(UUID, LocalDateTime, String)} - Constructs an Order with a specified ID, order date, and optional comment.</li>
 * </ul>
 * </p>
 *
 * @see BaseEntity
 */
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Table(name = "order_")
@Getter @Setter
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class Order extends BaseEntity {

    /**
     * The date and time when the order was placed.
     * Cannot be null.
     */
    @Column(nullable = false)
    private LocalDateTime orderDate;

    /**
     * An optional comment associated with the order.
     */
    @Column(nullable = true)
    private String comment;

    /**
     * Constructs an Order with the specified ID, order date, and optional comment.
     *
     * @param id The unique identifier of the order.
     * @param orderDate The date and time of the order.
     * @param comment An optional comment associated with the order.
     */
    public Order(UUID id, LocalDateTime orderDate, String comment) {
        super(id);
        this.orderDate = orderDate;
        this.comment = comment;
    }
}
