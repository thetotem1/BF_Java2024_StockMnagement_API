package be.bstorm.bf_java2024_stockmanagement.api.models.forms.article;

import be.bstorm.bf_java2024_stockmanagement.dl.entities.Article;
import be.bstorm.bf_java2024_stockmanagement.dl.enums.VAT;
import be.bstorm.bf_java2024_stockmanagement.api.validators.annotations.ImageFormat;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

/**
 * Form Data Transfer Object (DTO) for capturing article input data.
 * This record is used to collect and validate user input for creating or updating an article.
 *
 * <p>Fields:
 * <ul>
 * <li>{@code designation} - The name or designation of the article, required and cannot be blank.</li>
 * <li>{@code unitPriceExcludingTax} - The unit price of the article, excluding tax, must be zero or positive.</li>
 * <li>{@code vat} - The VAT rate applied to the article, represented as an enumeration.</li>
 * <li>{@code image} - The image file associated with the article, validated for format using {@link ImageFormat}.</li>
 * <li>{@code categoryId} - The UUID of the category to which the article belongs.</li>
 * </ul>
 * </p>
 *
 * <p>Methods:
 * <ul>
 * <li>{@link #toArticle()} - Converts this form into an {@link Article} entity.</li>
 * </ul>
 * </p>
 *
 * @see Article
 * @see ImageFormat
 */
public record ArticleForm(

        @NotBlank(message = "Required field") String designation,
        @Min(0) double unitPriceExcludingTax,
        VAT vat,
        @ImageFormat MultipartFile image,
        UUID categoryId
) {

    /**
     * Converts this form data into an {@link Article} entity.
     *
     * @return A new {@link Article} populated with data from this form.
     */
    public Article toArticle() {
        return new Article(
                this.designation,
                (long) (unitPriceExcludingTax * 100),
                vat
        );
    }
}
