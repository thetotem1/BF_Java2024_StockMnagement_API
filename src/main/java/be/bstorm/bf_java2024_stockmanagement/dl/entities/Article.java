package be.bstorm.bf_java2024_stockmanagement.dl.entities;

import be.bstorm.bf_java2024_stockmanagement.dl.enums.VAT;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.validator.constraints.Range;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.UUID;

/**
 * Represents an Article entity, with details such as designation, unit price excluding tax, VAT rate,
 * category, and stock information. Includes methods to calculate the price including tax.
 * Utilizes JPA and Lombok annotations for entity management and common methods.
 *
 * <p>Fields:
 * <ul>
 * <li>{@code designation} - Unique designation for the article (required, max length 80).</li>
 * <li>{@code unitPriceExcludingTax} - Price before tax (required, non-negative).</li>
 * <li>{@code vat} - VAT rate for the article, represented as an enum (required).</li>
 * <li>{@code picture} - URL or path to the picture of the article (optional).</li>
 * <li>{@code isDeleted} - Indicates if the article is logically deleted (default false).</li>
 * <li>{@code category} - Category to which the article belongs (optional).</li>
 * <li>{@code stock} - Stock information for the article (optional, one-to-one relationship).</li>
 * </ul>
 * </p>
 *
 * <p>Methods:
 * <ul>
 * <li>{@link #getUnitPriceIncludingTax()} - Calculates the total price including tax.</li>
 * <li>{@link #getAddedValue()} - Computes the added value based on the VAT.</li>
 * </ul>
 * </p>
 *
 * @see BaseEntity
 * @see VAT
 * @see Category
 * @see Stock
 */
@Entity
@SQLDelete(sql = "update Article set is_deleted = true where id = ?")
@NoArgsConstructor
@Getter
@EqualsAndHashCode(callSuper = true, of = {"designation", "unitPriceExcludingTax", "vat", "category"})
@ToString(callSuper = true, of = {"designation", "unitPriceExcludingTax", "vat", "category"})
public class Article extends BaseEntity {

    /**
     * The unique designation of the article.
     * Cannot be null; max length of 80 characters.
     */
    @Setter
    @Column(nullable = false, unique = true, length = 80)
    private String designation;

    /**
     * The unit price of the article before tax.
     * Must be non-negative.
     */
    @Setter
    @Column(nullable = false)
    @Range(min = 0L)
    private long unitPriceExcludingTax;

    /**
     * The VAT rate applicable to the article.
     */
    @Setter
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private VAT vat;

    /**
     * The picture URL or path for the article.
     * This field is optional.
     */
    @Setter
    @Column(nullable = true)
    private String picture;

    /**
     * Flag indicating if the article has been logically deleted.
     * This value is managed automatically and is always false by default.
     */
    @Column(nullable = false)
    private final boolean isDeleted = false;

    /**
     * The category of the article.
     * This field is optional and follows a many-to-one relationship.
     */
    @Setter
    @ManyToOne(fetch = FetchType.EAGER)
    private Category category;

    /**
     * The stock associated with the article.
     * This is a one-to-one relationship managed by the {@code Stock} entity.
     */
    @OneToOne(mappedBy = "article", fetch = FetchType.EAGER)
    private Stock stock;

    /**
     * Constructs an Article with the specified designation, unit price excluding tax, and VAT rate.
     *
     * @param designation The designation of the article.
     * @param unitPriceExcludingTax The unit price excluding tax.
     * @param vat The VAT rate for the article.
     */
    public Article(String designation, long unitPriceExcludingTax, VAT vat) {
        this.designation = designation;
        this.unitPriceExcludingTax = unitPriceExcludingTax;
        this.vat = vat;
    }

    /**
     * Constructs an Article with the specified ID, designation, unit price excluding tax, and VAT rate.
     *
     * @param id The unique identifier of the article.
     * @param designation The designation of the article.
     * @param unitPriceExcludingTax The unit price excluding tax.
     * @param vat The VAT rate for the article.
     */
    public Article(UUID id, String designation, long unitPriceExcludingTax, VAT vat) {
        super(id);
        this.designation = designation;
        this.unitPriceExcludingTax = unitPriceExcludingTax;
        this.vat = vat;
    }

    /**
     * Constructs an Article with the specified ID, designation, unit price excluding tax, VAT rate,
     * picture URL, and category.
     *
     * @param id The unique identifier of the article.
     * @param designation The designation of the article.
     * @param unitPriceExcludingTax The unit price excluding tax.
     * @param vat The VAT rate for the article.
     * @param picture The picture URL or path for the article.
     * @param category The category to which the article belongs.
     */
    public Article(UUID id, String designation, long unitPriceExcludingTax, VAT vat, String picture, Category category) {
        super(id);
        this.designation = designation;
        this.unitPriceExcludingTax = unitPriceExcludingTax;
        this.vat = vat;
        this.picture = picture;
        this.category = category;
    }

    /**
     * Sets the stock for this article.
     * This is an internal method used to establish the relationship from the {@code Stock} entity.
     *
     * @param stock The stock associated with this article.
     */
    protected void setStock(Stock stock) {
        this.stock = stock;
    }

    /**
     * Calculates the unit price including tax based on the VAT rate.
     *
     * @return The unit price including tax.
     */
    public long getUnitPriceIncludingTax() {
        return getUnitPriceExcludingTax() + getAddedValue();
    }

    /**
     * Computes the added value, or tax amount, based on the unit price and VAT rate.
     *
     * @return The added value.
     */
    public long getAddedValue() {
        BigDecimal vat = BigDecimal.valueOf(this.vat.value, 2);
        BigDecimal priceTTE = BigDecimal.valueOf(this.unitPriceExcludingTax);
        BigDecimal addedValue = priceTTE.multiply(vat);
        return addedValue.setScale(0, RoundingMode.HALF_UP).longValue();
    }
}
