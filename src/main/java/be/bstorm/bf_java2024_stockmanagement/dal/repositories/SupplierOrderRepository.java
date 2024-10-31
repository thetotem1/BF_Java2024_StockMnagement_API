package be.bstorm.bf_java2024_stockmanagement.dal.repositories;

import be.bstorm.bf_java2024_stockmanagement.dl.entities.order.SupplierOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

/**
 * Repository interface for managing {@link SupplierOrder} entities in the stock management system.
 * Extends {@link JpaRepository} to provide CRUD operations for SupplierOrder entities.
 *
 * <p>Provides basic CRUD functionality, including saving, finding, and deleting supplier orders by UUID.</p>
 *
 * @see SupplierOrder
 * @see JpaRepository
 */
@Repository
public interface SupplierOrderRepository extends JpaRepository<SupplierOrder, UUID> {
}
