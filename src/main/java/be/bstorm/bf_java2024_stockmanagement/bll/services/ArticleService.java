package be.bstorm.bf_java2024_stockmanagement.bll.services;

import be.bstorm.bf_java2024_stockmanagement.dl.entities.Article;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

/**
 * Interface for managing {@link Article} entities, providing CRUD operations and additional business logic.
 * This service includes methods for saving images, validating uniqueness, and retrieving only active articles.
 *
 * <p>Methods:
 * <ul>
 * <li>{@link #findAll()} - Retrieves a list of all active articles.</li>
 * <li>{@link #findById(UUID)} - Finds an article by its unique identifier (UUID), ensuring it is active.</li>
 * <li>{@link #save(Article, MultipartFile)} - Saves a new article, with optional image storage, ensuring unique designation.</li>
 * <li>{@link #update(Article, MultipartFile)} - Updates an article's values, with optional image replacement, ensuring unique designation.</li>
 * <li>{@link #delete(UUID)} - Marks an article as deleted by its ID.</li>
 * </ul>
 * </p>
 *
 * @see Article
 */
public interface ArticleService {

    /**
     * Retrieves a list of all active articles from the repository.
     *
     * @return A {@link List} of active {@link Article} objects.
     */
    List<Article> findAll();

    /**
     * Retrieves an article by its unique identifier (UUID).
     * If the article is marked as deleted, a {@link RuntimeException} is thrown.
     *
     * @param id The UUID of the article to retrieve.
     * @return The {@link Article} if found and active.
     * @throws RuntimeException If the article is marked as deleted.
     */
    Article findById(UUID id);

    /**
     * Saves a new article entity with a unique identifier. If an image is provided, it is stored on the server.
     * Validates that the article designation is unique before saving.
     *
     * @param article The {@link Article} entity to save.
     * @param image A {@link MultipartFile} representing the article's image (optional).
     * @return The saved {@link Article}.
     * @throws IllegalArgumentException If an article with the same designation already exists.
     */
    Article save(Article article, MultipartFile image);

    /**
     * Updates an existing article entity with new values. If an image is provided, it replaces the existing one.
     * Validates that no other article with the same designation exists before updating.
     *
     * @param article The {@link Article} entity with updated values.
     * @param image A {@link MultipartFile} representing the new article's image (optional).
     * @throws IllegalArgumentException If another article with the same designation already exists.
     */
    void update(Article article, MultipartFile image);

    /**
     * Marks an article as deleted by its ID.
     *
     * @param id The UUID of the article to delete.
     * @throws IllegalArgumentException If the article does not exist.
     */
    void delete(UUID id);
}
