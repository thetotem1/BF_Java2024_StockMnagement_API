package be.bstorm.bf_java2024_stockmanagement.dl.entities;

import be.bstorm.bf_java2024_stockmanagement.dl.enums.StockMovementType;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.validator.constraints.Range;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Represents a stock movement for an article, recording the type, quantity, and date of the movement.
 * Each movement is associated with an article and can either increase or decrease the stock.
 *
 * <p>Fields:
 * <ul>
 * <li>{@code movementType} - The type of stock movement (e.g., INCREASE, DECREASE).</li>
 * <li>{@code quantity} - The quantity of items moved (must be non-negative).</li>
 * <li>{@code movementDate} - The date and time when the movement occurred.</li>
 * <li>{@code article} - The article associated with this stock movement.</li>
 * </ul>
 * </p>
 *
 * @see StockMovementType
 * @see Article
 */
@Entity
@Getter @Setter
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class StockMovement extends BaseEntity {

    /**
     * The type of stock movement, represented as an enum (e.g., INCREASE, DECREASE).
     */
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private StockMovementType movementType;

    /**
     * The quantity of items involved in the movement.
     * Must be non-negative.
     */
    @Column(nullable = false)
    @Range(min = 0L)
    private int quantity;

    /**
     * The date and time when the stock movement occurred.
     */
    @Column(nullable = false)
    private LocalDateTime movementDate;

    /**
     * The article associated with this stock movement.
     */
    @ManyToOne(fetch = FetchType.EAGER)
    private Article article;

    /**
     * Constructs a StockMovement with the specified ID, movement type, quantity, movement date, and associated article.
     *
     * @param id The unique identifier of the stock movement.
     * @param movementType The type of stock movement (e.g., INCREASE, DECREASE).
     * @param quantity The quantity of items moved.
     * @param movementDate The date and time of the movement.
     * @param article The article associated with this stock movement.
     */
    public StockMovement(UUID id, StockMovementType movementType, int quantity, LocalDateTime movementDate, Article article) {
        super(id);
        this.movementType = movementType;
        this.quantity = quantity;
        this.movementDate = movementDate;
        this.article = article;
    }
}
