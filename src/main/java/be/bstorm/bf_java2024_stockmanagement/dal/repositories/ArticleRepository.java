package be.bstorm.bf_java2024_stockmanagement.dal.repositories;

import be.bstorm.bf_java2024_stockmanagement.dl.entities.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

/**
 * Repository interface for managing {@link Article} entities in the stock management system.
 * Extends {@link JpaRepository} to provide CRUD operations and custom queries for Article entities.
 *
 * <p>Methods:
 * <ul>
 * <li>{@link #existsByDesignation(String)} - Checks if an article with a specified designation exists.</li>
 * <li>{@link #existsInOtherArticleByDesignation(UUID, String)} - Checks if an article with the specified designation exists in other articles.</li>
 * <li>{@link #findAllActive()} - Finds all active (non-deleted) articles.</li>
 * </ul>
 * </p>
 *
 * @see Article
 * @see JpaRepository
 */
@Repository
public interface ArticleRepository extends JpaRepository<Article, UUID> {

    /**
     * Checks if an article with the specified designation exists, ignoring case.
     *
     * @param designation The designation to check.
     * @return {@code true} if an article with the specified designation exists; {@code false} otherwise.
     */
    @Query("select count(a) > 0 from Article a where a.designation ilike :designation")
    boolean existsByDesignation(String designation);

    /**
     * Checks if an article with the specified designation exists in other articles (i.e., excluding the given article ID).
     *
     * @param id The ID of the article to exclude from the check.
     * @param designation The designation to check.
     * @return {@code true} if another article with the specified designation exists; {@code false} otherwise.
     */
    @Query("select count(a) > 0 from Article a where a.id != :id and a.designation = :designation")
    boolean existsInOtherArticleByDesignation(UUID id, String designation);

    /**
     * Finds all active (non-deleted) articles.
     *
     * @return A list of all active articles.
     */
    @Query("select a from Article a where a.isDeleted = false")
    List<Article> findAllActive();

//    /**
//     * Finds an active article by its ID, if it exists and is not marked as deleted.
//     *
//     * @param id The ID of the article to find.
//     * @return An {@link Optional} containing the active article, if found.
//     */
//    @Query("select a from Article a where a.id = :id and a.isDeleted = false")
//    Optional<Article> findActiveById(UUID id);
}
