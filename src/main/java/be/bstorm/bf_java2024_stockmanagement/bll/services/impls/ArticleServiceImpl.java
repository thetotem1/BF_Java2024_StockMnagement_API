package be.bstorm.bf_java2024_stockmanagement.bll.services.impls;

import be.bstorm.bf_java2024_stockmanagement.bll.services.ArticleService;
import be.bstorm.bf_java2024_stockmanagement.dal.repositories.ArticleRepository;
import be.bstorm.bf_java2024_stockmanagement.dl.entities.Article;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.UUID;

/**
 * Implementation of the {@link ArticleService} interface that provides CRUD operations for {@link Article} entities.
 * This service includes business logic related to articles, such as image storage, validation checks, and retrieval of active articles only.
 *
 * <p>Methods:
 * <ul>
 * <li>{@link #findAll()} - Retrieves a list of all active articles.</li>
 * <li>{@link #findById(UUID)} - Finds an article by its unique identifier, ensuring it is not marked as deleted.</li>
 * <li>{@link #save(Article, MultipartFile)} - Saves a new article with optional image storage, ensuring a unique designation.</li>
 * <li>{@link #update(Article, MultipartFile)} - Updates an article's details and optionally replaces its image.</li>
 * <li>{@link #delete(UUID)} - Marks an article as deleted by its ID.</li>
 * <li>{@link #saveImage(MultipartFile)} - Stores an image on the server and returns its file name.</li>
 * </ul>
 * </p>
 *
 * @see ArticleService
 * @see Article
 */
@Service
@RequiredArgsConstructor
public class ArticleServiceImpl implements ArticleService {

    private final ArticleRepository articleRepository;

    /**
     * Retrieves a list of all active articles from the repository.
     *
     * @return A {@link List} of active {@link Article} objects.
     */
    @Override
    public List<Article> findAll() {
        return articleRepository.findAllActive();
    }

    /**
     * Retrieves an article by its unique identifier (UUID).
     * If the article is marked as deleted, a {@link RuntimeException} is thrown.
     *
     * @param id The UUID of the article to retrieve.
     * @return The {@link Article} if found and active.
     * @throws RuntimeException If the article is marked as deleted.
     */
    @Override
    public Article findById(UUID id) {
        Article article = articleRepository.findById(id).orElseThrow();
        if (article.isDeleted()) {
            throw new RuntimeException("Article deleted");
        }
        return article;
    }

    /**
     * Saves a new article entity with a unique identifier. If an image is provided, it is stored on the server.
     * Validates that the article designation is unique before saving.
     *
     * @param article The {@link Article} entity to save.
     * @param image A {@link MultipartFile} representing the article's image (optional).
     * @return The saved {@link Article}.
     * @throws IllegalArgumentException If an article with the same designation already exists.
     */
    @Override
    public Article save(Article article, MultipartFile image) {
        if (articleRepository.existsByDesignation(article.getDesignation())) {
            throw new IllegalArgumentException("Designation already exists");
        }
        article.setId(UUID.randomUUID());

        if (image != null && !image.isEmpty()) {
            article.setPicture(saveImage(image));
        }
        return articleRepository.save(article);
    }

    /**
     * Updates an existing article entity with new values. If an image is provided, it replaces the existing one.
     * Validates that no other article with the same designation exists before updating.
     *
     * @param article The {@link Article} entity with updated values.
     * @param image A {@link MultipartFile} representing the new article's image (optional).
     * @throws IllegalArgumentException If another article with the same designation already exists.
     */
    @Override
    public void update(Article article, MultipartFile image) {
        Article existingArticle = articleRepository.findById(article.getId()).orElseThrow();

        if (articleRepository.existsInOtherArticleByDesignation(article.getId(), article.getDesignation())) {
            throw new IllegalArgumentException("Designation already exists");
        }

        existingArticle.setDesignation(article.getDesignation());
        existingArticle.setUnitPriceExcludingTax(article.getUnitPriceExcludingTax());
        existingArticle.setVat(article.getVat());
        existingArticle.setCategory(article.getCategory());

        if (image != null && !image.isEmpty()) {
            existingArticle.setPicture(saveImage(image));
        }

        articleRepository.save(existingArticle);
    }

    /**
     * Marks an article as deleted by its ID.
     *
     * @param id The UUID of the article to delete.
     * @throws IllegalArgumentException If the article does not exist.
     */
    @Transactional
    @Override
    public void delete(UUID id) {
        if (!articleRepository.existsById(id)) {
            throw new IllegalArgumentException("Article does not exist");
        }
        articleRepository.deleteById(id);
    }

    /**
     * Stores the provided image on the server and returns the image name for future retrieval.
     *
     * @param image A {@link MultipartFile} representing the image to be saved.
     * @return The saved image name as a {@link String}.
     * @throws RuntimeException If an error occurs during the file save operation.
     */
    private String saveImage(MultipartFile image) {
        String imageName = UUID.randomUUID() + "_" + image.getOriginalFilename();
        Path imagePath = Path.of(System.getProperty("user.dir"), "images", imageName);
        try {
            Files.write(imagePath, image.getBytes());
            return imageName;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
