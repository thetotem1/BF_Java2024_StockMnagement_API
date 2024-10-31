package be.bstorm.bf_java2024_stockmanagement.dal.repositories;

import be.bstorm.bf_java2024_stockmanagement.dl.entities.person.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

/**
 * Repository interface for managing {@link Client} entities in the stock management system.
 * Extends {@link JpaRepository} to provide CRUD operations for Client entities.
 *
 * <p>Provides basic CRUD functionality, including saving, finding, and deleting clients by UUID.</p>
 *
 * @see Client
 * @see JpaRepository
 */
@Repository
public interface ClientRepository extends JpaRepository<Client, UUID> {
}
