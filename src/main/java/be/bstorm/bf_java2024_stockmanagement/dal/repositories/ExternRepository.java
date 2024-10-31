package be.bstorm.bf_java2024_stockmanagement.dal.repositories;

import be.bstorm.bf_java2024_stockmanagement.dl.entities.person.Extern;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.UUID;

/**
 * Repository interface for managing {@link Extern} entities in the stock management system.
 * Extends {@link JpaRepository} to provide CRUD operations for Extern entities and includes a custom query to check if an Extern exists by email.
 *
 * <p>Methods:
 * <ul>
 * <li>{@link #existsByEmail(String)} - Checks if an Extern entity exists with a specified email address.</li>
 * </ul>
 * </p>
 *
 * @see Extern
 * @see JpaRepository
 */
@Repository
public interface ExternRepository extends JpaRepository<Extern, UUID> {

    /**
     * Checks if an Extern entity exists with the specified email address, ignoring case.
     *
     * @param email The email to check.
     * @return {@code true} if an Extern with the specified email exists; {@code false} otherwise.
     */
    @Query("select count(e) > 0 from Extern e where e.email ilike :email")
    boolean existsByEmail(String email);
}
