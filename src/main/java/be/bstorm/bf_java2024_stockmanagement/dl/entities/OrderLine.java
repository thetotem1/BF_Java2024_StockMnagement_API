package be.bstorm.bf_java2024_stockmanagement.dl.entities;

import be.bstorm.bf_java2024_stockmanagement.dl.entities.order.Order;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.validator.constraints.Range;

import java.util.UUID;

/**
 * Represents a line item within an order, linking a specific article to an order with a specified quantity.
 * The {@code OrderLine} class uses a composite key defined by the {@link OrderLineId} class.
 *
 * <p>Fields:
 * <ul>
 * <li>{@code id} - Composite identifier for the order line, embedding {@code orderId} and {@code articleId}.</li>
 * <li>{@code quantity} - The quantity of the article in this order line (must be non-negative).</li>
 * <li>{@code order} - The order associated with this line item.</li>
 * <li>{@code article} - The article associated with this line item.</li>
 * </ul>
 * </p>
 *
 * @see Order
 * @see Article
 */
@Entity
@Getter @Setter
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class OrderLine {

    /**
     * Composite identifier for the OrderLine, consisting of {@code orderId} and {@code articleId}.
     */
    @EmbeddedId
    private OrderLineId id;

    /**
     * The quantity of the article in this order line.
     * Must be non-negative.
     */
    @Range(min = 0L)
    @Column(nullable = false)
    private int quantity;

    /**
     * The order associated with this line item.
     * The {@code orderId} is mapped from the composite key {@code id}.
     */
    @ManyToOne(fetch = FetchType.EAGER)
    @MapsId("orderId")
    private Order order;

    /**
     * The article associated with this line item.
     * The {@code articleId} is mapped from the composite key {@code id}.
     */
    @ManyToOne(fetch = FetchType.EAGER)
    @MapsId("articleId")
    private Article article;

    /**
     * Constructs an OrderLine with the specified quantity, order, and article.
     * Initializes the composite identifier with the IDs of the order and article.
     *
     * @param quantity The quantity of the article.
     * @param order The order associated with this line item.
     * @param article The article associated with this line item.
     */
    public OrderLine(int quantity, Order order, Article article) {
        this.id = new OrderLineId(order.getId(), article.getId());
        this.quantity = quantity;
        this.order = order;
        this.article = article;
    }

    /**
     * Composite key class for the {@code OrderLine} entity, combining {@code orderId} and {@code articleId}.
     * Used to uniquely identify each order line item within an order.
     */
    @Embeddable
    @Getter @Setter
    @NoArgsConstructor @AllArgsConstructor
    @EqualsAndHashCode
    @ToString
    public static class OrderLineId {

        /**
         * The unique identifier of the order.
         */
        private UUID orderId;

        /**
         * The unique identifier of the article.
         */
        private UUID articleId;
    }
}
