package be.bstorm.bf_java2024_stockmanagement.dal.repositories;

import be.bstorm.bf_java2024_stockmanagement.dl.entities.StockMovement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.stereotype.Repository;

import java.util.UUID;

/**
 * Repository interface for managing {@link StockMovement} entities in the stock management system.
 * Extends {@link JpaRepository} to provide CRUD operations for StockMovement entities and includes a stored procedure to insert a stock movement.
 *
 * <p>Methods:
 * <ul>
 * <li>{@link #insertStockMovement(UUID, String, int)} - Executes the stored procedure {@code InsertStockMovement} to insert a new stock movement entry.</li>
 * </ul>
 * </p>
 *
 * @see StockMovement
 * @see JpaRepository
 */
@Repository
public interface StockMovementRepository extends JpaRepository<StockMovement, UUID> {

    /**
     * Executes the stored procedure {@code InsertStockMovement} to insert a new stock movement entry.
     *
     * @param articleId The ID of the article associated with the stock movement.
     * @param movementType The type of stock movement (e.g., STOCK_IN, STOCK_OUT).
     * @param quantity The quantity involved in the stock movement.
     */
    @Procedure(procedureName = "InsertStockMovement")
    void insertStockMovement(
            UUID articleId,
            String movementType,
            int quantity
    );
}
