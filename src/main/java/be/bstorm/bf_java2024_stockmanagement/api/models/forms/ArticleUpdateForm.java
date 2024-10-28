package be.bstorm.bf_java2024_stockmanagement.api.models.forms;

import be.bstorm.bf_java2024_stockmanagement.dl.entities.Article;
import be.bstorm.bf_java2024_stockmanagement.dl.enums.VAT;
import be.bstorm.bf_java2024_stockmanagement.api.validators.annotations.ImageFormat;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

public record ArticleUpdateForm(

        @NotBlank(message = "Required field")
        String designation,
        @Min(0)
        double unitPriceExcludingTax,
        VAT vat,
        @ImageFormat
        MultipartFile image,
        UUID categoryId,
        UUID id,
        String imageUrl
) {

    public Article toArticle() {
        return new Article(
                this.id,
                this.designation,
                (long) (this.unitPriceExcludingTax * 100),
                this.vat
        );
    }
}
