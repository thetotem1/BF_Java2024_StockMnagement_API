package be.bstorm.bf_java2024_stockmanagement.api.models.dtos.article;

import be.bstorm.bf_java2024_stockmanagement.dl.entities.Article;

import java.util.UUID;

/**
 * Data Transfer Object (DTO) for summarizing information about an {@link Article}.
 * This record provides a lightweight representation of an article, including essential fields like pricing, category, and stock quantity.
 *
 * <p>Fields:
 * <ul>
 * <li>{@code id} - The unique identifier of the article.</li>
 * <li>{@code designation} - The name or designation of the article.</li>
 * <li>{@code unitPriceExcludingTax} - The unit price of the article, excluding tax, represented as a decimal.</li>
 * <li>{@code unitPriceIncludingTax} - The unit price of the article, including tax, represented as a decimal.</li>
 * <li>{@code category} - The designation of the category to which the article belongs.</li>
 * <li>{@code quantity} - The current quantity of the article in stock.</li>
 * </ul>
 * </p>
 *
 * <p>Methods:
 * <ul>
 * <li>{@link #fromArticle(Article)} - Converts an {@link Article} entity into an {@link ArticleDTO}.</li>
 * </ul>
 * </p>
 *
 * @see Article
 */
public record ArticleDTO(
        UUID id,
        String designation,
        double unitPriceExcludingTax,
        double unitPriceIncludingTax,
        String category,
        int quantity
) {

    /**
     * Converts an {@link Article} entity into an {@link ArticleDTO} instance.
     *
     * @param a The {@link Article} entity to convert.
     * @return An {@link ArticleDTO} with data populated from the given article.
     */
    public static ArticleDTO fromArticle(Article a) {
        return new ArticleDTO(
                a.getId(),
                a.getDesignation(),
                a.getUnitPriceExcludingTax() / 100D,
                a.getUnitPriceIncludingTax() / 100D,
                a.getCategory().getDesignation(),
                a.getStock() == null ? 0 : a.getStock().getCurrentQuantity()
        );
    }
}
