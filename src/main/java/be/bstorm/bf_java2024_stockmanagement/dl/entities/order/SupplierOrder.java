package be.bstorm.bf_java2024_stockmanagement.dl.entities.order;

import be.bstorm.bf_java2024_stockmanagement.dl.entities.person.Supplier;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Represents an order placed with a supplier in the stock management system, extending the {@code Order} class.
 * Each supplier order is associated with a specific supplier.
 *
 * <p>Fields:
 * <ul>
 * <li>{@code supplier} - The supplier associated with this order.</li>
 * </ul>
 * </p>
 *
 * <p>Constructors:
 * <ul>
 * <li>{@link #SupplierOrder(UUID, LocalDateTime, String, Supplier)} - Constructs a SupplierOrder with specified ID, order date, comment, and supplier.</li>
 * </ul>
 * </p>
 *
 * @see Order
 * @see Supplier
 */
@Entity
@Getter @Setter
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class SupplierOrder extends Order {

    /**
     * The supplier associated with this order.
     */
    @ManyToOne(fetch = FetchType.EAGER)
    private Supplier supplier;

    /**
     * Constructs a SupplierOrder with the specified ID, order date, comment, and supplier.
     *
     * @param id The unique identifier of the order.
     * @param orderDate The date and time of the order.
     * @param comment An optional comment associated with the order.
     * @param supplier The supplier associated with this order.
     */
    public SupplierOrder(UUID id, LocalDateTime orderDate, String comment, Supplier supplier) {
        super(id, orderDate, comment);
        this.supplier = supplier;
    }
}
