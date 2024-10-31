package be.bstorm.bf_java2024_stockmanagement.api.controllers;

import be.bstorm.bf_java2024_stockmanagement.bll.services.ArticleService;
import be.bstorm.bf_java2024_stockmanagement.bll.services.CategoryService;
import be.bstorm.bf_java2024_stockmanagement.dl.entities.Article;
import be.bstorm.bf_java2024_stockmanagement.dl.entities.Category;
import be.bstorm.bf_java2024_stockmanagement.api.models.dtos.article.ArticleDTO;
import be.bstorm.bf_java2024_stockmanagement.api.models.dtos.article.ArticleDetailsDTO;
import be.bstorm.bf_java2024_stockmanagement.api.models.forms.article.ArticleForm;
import be.bstorm.bf_java2024_stockmanagement.api.models.forms.article.ArticleUpdateForm;
import jakarta.validation.Valid;
import jakarta.validation.ValidationException;
import lombok.RequiredArgsConstructor;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.UUID;

/**
 * REST controller for managing articles.
 * Provides endpoints for creating, updating, deleting, and retrieving articles in the stock management system.
 *
 * <p>Endpoints:
 * <ul>
 * <li>{@link #getArticles()} - Retrieves all active articles as a list.</li>
 * <li>{@link #getArticle(UUID)} - Retrieves details of a specific article by its ID.</li>
 * <li>{@link #createArticle(ArticleForm, BindingResult)} - Creates a new article with optional image upload and validates input data.</li>
 * <li>{@link #updateArticle(ArticleUpdateForm, BindingResult)} - Updates an article’s details and optionally replaces the image, with validation checks.</li>
 * <li>{@link #deleteArticle(UUID)} - Marks an article as deleted by its ID.</li>
 * </ul>
 * </p>
 *
 * @see ArticleService
 * @see CategoryService
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/article")
public class ArticleController {

    private final ArticleService articleService;
    private final CategoryService categoryService;

    /**
     * Retrieves a list of all active articles, transforming each into an {@link ArticleDTO}.
     *
     * @return A {@link ResponseEntity} containing a list of {@link ArticleDTO} objects representing active articles.
     */
    @GetMapping
    public ResponseEntity<List<ArticleDTO>> getArticles() {
        List<ArticleDTO> articles = articleService.findAll().stream()
                .map(ArticleDTO::fromArticle)
                .toList();
        return ResponseEntity.ok(articles);
    }

    /**
     * Retrieves detailed information about a specific article, identified by its unique ID.
     *
     * @param id The UUID of the article to retrieve. Must be a valid UUID format.
     * @return A {@link ResponseEntity} containing an {@link ArticleDetailsDTO} if found.
     * @throws RuntimeException if the article does not exist or is marked as deleted.
     */
    @GetMapping("/{id}")
    public ResponseEntity<ArticleDetailsDTO> getArticle(@PathVariable UUID id) {
        ArticleDetailsDTO dto = ArticleDetailsDTO.fromArticle(articleService.findById(id));
        return ResponseEntity.ok(dto);
    }

    /**
     * Creates a new article, storing it in the system with an optional image.
     * Validates the {@link ArticleForm} input data, including mandatory fields and image format constraints.
     *
     * @param articleForm The {@link ArticleForm} containing the article data, such as designation, VAT rate, and category ID.
     *                    The {@code image} field is optional but validated if provided.
     * @param bindingResult The result of form validation, holding any validation errors.
     * @return A {@link ResponseEntity} with a location header pointing to the created article resource if successful.
     * @throws ValidationException If there are validation errors in the provided form data. Collects errors as a list and includes them in the exception message.
     */
    @PostMapping
    public ResponseEntity<Void> createArticle(
            @Valid @RequestBody ArticleForm articleForm,
            BindingResult bindingResult
    ) {
        if (bindingResult.hasErrors()) {
            List<String> errors = bindingResult.getAllErrors().stream()
                    .map(DefaultMessageSourceResolvable::getDefaultMessage)
                    .toList();
            throw new ValidationException("Validation Error: " + errors);
        }

        Category category = categoryService.findById(articleForm.categoryId());
        Article article = articleForm.toArticle();
        article.setCategory(category);
        Article createdArticle = articleService.save(article, articleForm.image());

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(createdArticle.getId())
                .toUri();
        return ResponseEntity.created(location).build();
    }

    /**
     * Updates an existing article by ID with new data provided in the {@link ArticleUpdateForm}.
     * This includes changes to the designation, unit price, VAT, category, and optionally, a new image.
     *
     * @param articleForm The {@link ArticleUpdateForm} containing updated article details, including an optional image field.
     * @param bindingResult The result of validation for the form data, capturing validation errors if any.
     * @return A {@link ResponseEntity} with no content if the update is successful.
     * @throws ValidationException If validation errors occur, throws a {@link ValidationException} with a list of error messages.
     */
    @PutMapping
    public ResponseEntity<Void> updateArticle(
            @Valid @RequestBody ArticleUpdateForm articleForm,
            BindingResult bindingResult
    ) {
        if (bindingResult.hasErrors()) {
            List<String> errors = bindingResult.getAllErrors().stream()
                    .map(DefaultMessageSourceResolvable::getDefaultMessage)
                    .toList();
            throw new ValidationException("Validation Error: " + errors);
        }

        Article article = articleForm.toArticle();
        article.setCategory(categoryService.findById(articleForm.categoryId()));
        articleService.update(article, articleForm.image());
        return ResponseEntity.noContent().build();
    }

    /**
     * Deletes an article by marking it as deleted in the system. If the article does not exist or is already deleted,
     * the method throws an {@link IllegalArgumentException}.
     *
     * @param id The UUID of the article to delete.
     * @return A {@link ResponseEntity} with no content if the deletion is successful.
     * @throws IllegalArgumentException if the article does not exist.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteArticle(@PathVariable UUID id) {
        articleService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
