package be.bstorm.bf_java2024_stockmanagement.dal.repositories;

import be.bstorm.bf_java2024_stockmanagement.dl.entities.person.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

/**
 * Repository interface for managing {@link User} entities in the stock management system.
 * Extends {@link JpaRepository} to provide CRUD operations for User entities and includes custom queries for finding and checking users by email.
 *
 * <p>Methods:
 * <ul>
 * <li>{@link #findByEmail(String)} - Finds a user by email, ignoring case sensitivity.</li>
 * <li>{@link #existsByEmail(String)} - Checks if a user exists with a specified email, ignoring case sensitivity.</li>
 * </ul>
 * </p>
 *
 * @see User
 * @see JpaRepository
 */
@Repository
public interface UserRepository extends JpaRepository<User, UUID> {

    /**
     * Finds a user by their email address, ignoring case.
     *
     * @param email The email address to search for.
     * @return An {@link Optional} containing the user if found; {@code Optional.empty()} otherwise.
     */
    @Query("select u from User u where u.email ilike :email")
    Optional<User> findByEmail(String email);

    /**
     * Checks if a user exists with the specified email address, ignoring case.
     *
     * @param email The email address to check.
     * @return {@code true} if a user with the specified email exists; {@code false} otherwise.
     */
    @Query("select count(u) > 0 from User u where u.email ilike :email")
    boolean existsByEmail(String email);
}
