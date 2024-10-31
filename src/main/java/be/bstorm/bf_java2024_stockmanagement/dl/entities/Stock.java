package be.bstorm.bf_java2024_stockmanagement.dl.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

/**
 * Represents the stock information for an article, including the current quantity available.
 * Each stock entry is associated with a specific article through a one-to-one relationship.
 *
 * <p>Fields:
 * <ul>
 * <li>{@code currentQuantity} - The current quantity of the article in stock (required).</li>
 * <li>{@code article} - The article associated with this stock entry (one-to-one relationship).</li>
 * </ul>
 * </p>
 *
 * <p>Methods:
 * <ul>
 * <li>{@link #addQuantity(int)} - Adds to the current quantity in stock.</li>
 * <li>{@link #subtractQuantity(int)} - Subtracts from the current quantity in stock.</li>
 * </ul>
 * </p>
 *
 * @see Article
 */
@Entity
@Getter
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class Stock extends BaseEntity {

    /**
     * The current quantity of the article in stock.
     * Must be non-negative.
     */
    @Column(nullable = false)
    @Setter
    private int currentQuantity;

    /**
     * The article associated with this stock entry.
     * This relationship is bidirectional, with {@code Stock} managed by the {@code Article} entity.
     */
    @OneToOne(fetch = FetchType.EAGER)
    private Article article;

    /**
     * Constructs a Stock entry with the specified ID, current quantity, and associated article.
     * Establishes a bidirectional relationship with the specified article.
     *
     * @param id The unique identifier of the stock entry.
     * @param currentQuantity The current quantity of the article in stock.
     * @param article The article associated with this stock entry.
     */
    public Stock(UUID id, int currentQuantity, Article article) {
        super(id);
        this.currentQuantity = currentQuantity;
        this.setArticle(article);
    }

    /**
     * Sets the associated article for this stock entry and updates the article's reference to this stock.
     *
     * @param article The article to associate with this stock.
     */
    private void setArticle(Article article) {
        this.article = article;
        article.setStock(this);
    }

    /**
     * Increases the current quantity in stock by the specified amount.
     *
     * @param quantity The amount to add to the current stock quantity.
     */
    public void addQuantity(int quantity) {
        currentQuantity += quantity;
    }

    /**
     * Decreases the current quantity in stock by the specified amount.
     *
     * @param quantity The amount to subtract from the current stock quantity.
     */
    public void subtractQuantity(int quantity) {
        currentQuantity -= quantity;
    }
}
