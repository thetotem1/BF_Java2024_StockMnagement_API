package be.bstorm.bf_java2024_stockmanagement.dal.repositories;

import be.bstorm.bf_java2024_stockmanagement.dl.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

/**
 * Repository interface for managing {@link Category} entities in the stock management system.
 * Extends {@link JpaRepository} to provide CRUD operations for Category entities.
 *
 * <p>Provides basic CRUD functionality, including saving, finding, and deleting categories by UUID.</p>
 *
 * @see Category
 * @see JpaRepository
 */
@Repository
public interface CategoryRepository extends JpaRepository<Category, UUID> {
}
