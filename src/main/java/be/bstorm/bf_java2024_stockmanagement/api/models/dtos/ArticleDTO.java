package be.bstorm.bf_java2024_stockmanagement.api.models.dtos;

import be.bstorm.bf_java2024_stockmanagement.dl.entities.Article;

import java.util.UUID;

public record ArticleDTO(
        UUID id,
        String designation,
        double unitPriceExcludingTax,
        double unitPriceIncludingTax,
        String category,
        int quantity
) {

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
