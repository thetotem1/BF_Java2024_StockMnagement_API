package be.bstorm.bf_java2024_stockmanagement.dal.repositories;

import be.bstorm.bf_java2024_stockmanagement.dl.entities.order.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

/**
 * Repository interface for managing {@link Order} entities in the stock management system.
 * Extends {@link JpaRepository} to provide CRUD operations for Order entities.
 *
 * <p>Provides basic CRUD functionality, including saving, finding, and deleting orders by UUID.</p>
 *
 * @see Order
 * @see JpaRepository
 */
@Repository
public interface OrderRepository extends JpaRepository<Order, UUID> {
}
