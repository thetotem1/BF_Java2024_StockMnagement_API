package be.bstorm.bf_java2024_stockmanagement.dal.repositories;

import be.bstorm.bf_java2024_stockmanagement.dl.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

/**
 * Repository interface for managing {@link Role} entities in the stock management system.
 * Extends {@link JpaRepository} to provide CRUD operations for Role entities and includes a custom query to find a role by its name.
 *
 * <p>Methods:
 * <ul>
 * <li>{@link #findByName(String)} - Finds a role entity by its name, ignoring case.</li>
 * </ul>
 * </p>
 *
 * @see Role
 * @see JpaRepository
 */
@Repository
public interface RoleRepository extends JpaRepository<Role, UUID> {

    /**
     * Finds a role by its name, ignoring case.
     *
     * @param name The name of the role to find.
     * @return An {@link Optional} containing the role if found; {@code Optional.empty()} otherwise.
     */
    @Query("select r from Role r where r.name ilike :name")
    Optional<Role> findByName(String name);
}
