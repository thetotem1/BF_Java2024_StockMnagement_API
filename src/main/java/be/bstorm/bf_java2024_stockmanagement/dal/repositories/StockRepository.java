package be.bstorm.bf_java2024_stockmanagement.dal.repositories;

import be.bstorm.bf_java2024_stockmanagement.dl.entities.Stock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

/**
 * Repository interface for managing {@link Stock} entities in the stock management system.
 * Extends {@link JpaRepository} to provide CRUD operations for Stock entities.
 *
 * <p>Provides basic CRUD functionality, including saving, finding, and deleting stock records by UUID.</p>
 *
 * @see Stock
 * @see JpaRepository
 */
@Repository
public interface StockRepository extends JpaRepository<Stock, UUID> {
}
