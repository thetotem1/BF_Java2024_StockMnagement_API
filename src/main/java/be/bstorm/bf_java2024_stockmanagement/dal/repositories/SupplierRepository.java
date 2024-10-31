package be.bstorm.bf_java2024_stockmanagement.dal.repositories;

import be.bstorm.bf_java2024_stockmanagement.dl.entities.person.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

/**
 * Repository interface for managing {@link Supplier} entities in the stock management system.
 * Extends {@link JpaRepository} to provide CRUD operations for Supplier entities.
 *
 * <p>Provides basic CRUD functionality, including saving, finding, and deleting suppliers by UUID.</p>
 *
 * @see Supplier
 * @see JpaRepository
 */
@Repository
public interface SupplierRepository extends JpaRepository<Supplier, UUID> {
}
