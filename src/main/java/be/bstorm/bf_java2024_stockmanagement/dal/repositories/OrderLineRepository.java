package be.bstorm.bf_java2024_stockmanagement.dal.repositories;

import be.bstorm.bf_java2024_stockmanagement.dl.entities.OrderLine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository interface for managing {@link OrderLine} entities in the stock management system.
 * Extends {@link JpaRepository} to provide CRUD operations for OrderLine entities, using {@link OrderLine.OrderLineId} as the primary key type.
 *
 * <p>Provides basic CRUD functionality, including saving, finding, and deleting order lines by their composite primary key.</p>
 *
 * @see OrderLine
 * @see JpaRepository
 */
@Repository
public interface OrderLineRepository extends JpaRepository<OrderLine, OrderLine.OrderLineId> {
}
