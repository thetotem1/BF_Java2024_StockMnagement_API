package be.bstorm.bf_java2024_stockmanagement.api.models.dtos.article;

import be.bstorm.bf_java2024_stockmanagement.dl.entities.Article;
import be.bstorm.bf_java2024_stockmanagement.dl.enums.VAT;

import java.util.UUID;

/**
 * Data Transfer Object (DTO) for detailed information about an {@link Article}.
 * This record provides essential fields to represent an article, including pricing, VAT, and stock information.
 *
 * <p>Fields:
 * <ul>
 * <li>{@code id} - The unique identifier of the article.</li>
 * <li>{@code designation} - The name or designation of the article.</li>
 * <li>{@code unitPriceExcludingTax} - The unit price of the article, excluding tax, represented as a decimal.</li>
 * <li>{@code unitPriceIncludingTax} - The unit price of the article, including tax, represented as a decimal.</li>
 * <li>{@code addedValue} - The added tax value as a decimal.</li>
 * <li>{@code vat} - The VAT rate applied to the article.</li>
 * <li>{@code picture} - The URL or file path to the article's picture.</li>
 * <li>{@code category} - The designation of the category to which the article belongs.</li>
 * <li>{@code quantity} - The current quantity of the article in stock.</li>
 * </ul>
 * </p>
 *
 * <p>Methods:
 * <ul>
 * <li>{@link #fromArticle(Article)} - Converts an {@link Article} entity into an {@link ArticleDetailsDTO}.</li>
 * </ul>
 * </p>
 *
 * @see Article
 */
public record ArticleDetailsDTO(
        UUID id,
        String designation,
        double unitPriceExcludingTax,
        double unitPriceIncludingTax,
        double addedValue,
        VAT vat,
        String picture,
        String category,
        int quantity
) {

    /**
     * Converts an {@link Article} entity into an {@link ArticleDetailsDTO} instance.
     *
     * @param a The {@link Article} entity to convert.
     * @return An {@link ArticleDetailsDTO} with data populated from the given article.
     */
    public static ArticleDetailsDTO fromArticle(Article a) {
        return new ArticleDetailsDTO(
                a.getId(),
                a.getDesignation(),
                a.getUnitPriceExcludingTax() / 100D,
                a.getUnitPriceIncludingTax() / 100D,
                a.getAddedValue() / 100D,
                a.getVat(),
                a.getPicture(),
                a.getCategory().getDesignation(),
                a.getStock() == null ? 0 : a.getStock().getCurrentQuantity()
        );
    }
}
