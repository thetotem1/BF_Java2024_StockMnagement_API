package be.bstorm.bf_java2024_stockmanagement.bll.services.impls;

import be.bstorm.bf_java2024_stockmanagement.bll.services.CategoryService;
import be.bstorm.bf_java2024_stockmanagement.dal.repositories.CategoryRepository;
import be.bstorm.bf_java2024_stockmanagement.dl.entities.Category;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

/**
 * Implementation of the {@link CategoryService} interface that provides operations for {@link Category} entities.
 * This service provides methods to retrieve categories from the repository.
 *
 * <p>Methods:
 * <ul>
 * <li>{@link #findAll()} - Retrieves a list of all categories.</li>
 * <li>{@link #findById(UUID)} - Finds a category by its unique identifier (UUID).</li>
 * </ul>
 * </p>
 *
 * @see CategoryService
 * @see Category
 */
@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    /**
     * Retrieves a list of all categories from the repository.
     *
     * @return A {@link List} of {@link Category} objects.
     */
    @Override
    public List<Category> findAll() {
        return categoryRepository.findAll();
    }

    /**
     * Finds a category by its unique identifier (UUID).
     *
     * @param id The UUID of the category to retrieve.
     * @return The {@link Category} if found.
     * @throws RuntimeException If the category is not found.
     */
    @Override
    public Category findById(UUID id) {
        return categoryRepository.findById(id).orElseThrow();
    }
}
