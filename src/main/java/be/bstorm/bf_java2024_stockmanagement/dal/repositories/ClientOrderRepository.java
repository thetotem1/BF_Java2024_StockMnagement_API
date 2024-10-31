package be.bstorm.bf_java2024_stockmanagement.dal.repositories;

import be.bstorm.bf_java2024_stockmanagement.dl.entities.order.ClientOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

/**
 * Repository interface for managing {@link ClientOrder} entities in the stock management system.
 * Extends {@link JpaRepository} to provide CRUD operations for ClientOrder entities.
 *
 * <p>Provides basic CRUD functionality, including saving, finding, and deleting client orders by UUID.</p>
 *
 * @see ClientOrder
 * @see JpaRepository
 */
@Repository
public interface ClientOrderRepository extends JpaRepository<ClientOrder, UUID> {
}
