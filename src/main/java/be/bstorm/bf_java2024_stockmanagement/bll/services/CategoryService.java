package be.bstorm.bf_java2024_stockmanagement.bll.services;

import be.bstorm.bf_java2024_stockmanagement.dl.entities.Category;

import java.util.List;
import java.util.UUID;

/**
 * Interface for managing {@link Category} entities, providing methods for retrieving category data.
 *
 * <p>Methods:
 * <ul>
 * <li>{@link #findAll()} - Retrieves a list of all categories.</li>
 * <li>{@link #findById(UUID)} - Finds a category by its unique identifier (UUID).</li>
 * </ul>
 * </p>
 *
 * @see Category
 */
public interface CategoryService {

    /**
     * Retrieves a list of all categories from the repository.
     *
     * @return A {@link List} of {@link Category} objects.
     */
    List<Category> findAll();

    /**
     * Finds a category by its unique identifier (UUID).
     *
     * @param id The UUID of the category to retrieve.
     * @return The {@link Category} if found.
     * @throws RuntimeException If the category is not found.
     */
    Category findById(UUID id);
}
