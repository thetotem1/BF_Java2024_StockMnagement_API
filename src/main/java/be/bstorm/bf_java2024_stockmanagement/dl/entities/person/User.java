package be.bstorm.bf_java2024_stockmanagement.dl.entities.person;

import be.bstorm.bf_java2024_stockmanagement.dl.entities.Role;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.*;

@Entity
@Table(name = "user_")
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
/**
 * Represents a user in the stock management system, extending the {@code Person} class and implementing
 * {@code UserDetails} for Spring Security integration. A user can have multiple roles that define their
 * authorities within the system.
 *
 * <p>Fields:
 * <ul>
 * <li>{@code password} - The password for the user account (required).</li>
 * <li>{@code roles} - A set of roles associated with the user, defining their authorities.</li>
 * </ul>
 * </p>
 *
 * <p>Methods:
 * <ul>
 * <li>{@link #getAuthorities()} - Returns the granted authorities based on user roles.</li>
 * <li>{@link #getUsername()} - Returns the user's email as the username.</li>
 * <li>{@link #isAccountNonExpired()}, {@link #isAccountNonLocked()}, {@link #isCredentialsNonExpired()}, {@link #isEnabled()}
 * - Security-related methods indicating the status of the account.</li>
 * </ul>
 * </p>
 *
 * @see Person
 * @see Role
 */
public class User extends Person implements UserDetails {

    /**
     * The password for the user account.
     * Cannot be null.
     */
    @Getter @Setter
    @Column(nullable = false)
    private String password;

    /**
     * A set of roles associated with the user, defining their authorities.
     * This field is eagerly fetched.
     */
    @ManyToMany(fetch = FetchType.EAGER)
    private final Set<Role> roles = new HashSet<>();

    /**
     * Constructs a user with the specified email and password.
     *
     * @param email The email (username) of the user.
     * @param password The password for the user account.
     */
    public User(String email, String password) {
        super(email);
        this.password = password;
    }

    /**
     * Constructs a user with the specified first name, last name, and email.
     *
     * @param firstName The first name of the user.
     * @param lastName The last name of the user.
     * @param email The email (username) of the user.
     */
    public User(String firstName, String lastName, String email) {
        super(firstName, lastName, email);
    }

    /**
     * Constructs a user with the specified ID, first name, last name, email, and password.
     *
     * @param id The unique identifier of the user.
     * @param firstName The first name of the user.
     * @param lastName The last name of the user.
     * @param email The email (username) of the user.
     * @param password The password for the user account.
     */
    public User(UUID id, String firstName, String lastName, String email, String password) {
        super(id, firstName, lastName, email);
        this.password = password;
    }

    /**
     * Returns an immutable set of roles associated with this user.
     *
     * @return An unmodifiable set of roles.
     */
    public Set<Role> getRoles() {
        return Set.copyOf(roles);
    }

    /**
     * Adds a role to the user's role set.
     *
     * @param role The role to be added.
     */
    public void addRole(Role role) {
        roles.add(role);
    }

    /**
     * Removes a role from the user's role set.
     *
     * @param role The role to be removed.
     */
    public void removeRole(Role role) {
        roles.remove(role);
    }

    /**
     * Returns the authorities granted to the user based on their roles.
     *
     * @return A collection of granted authorities.
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles.stream()
                .map(role -> new SimpleGrantedAuthority(role.getName()))
                .toList();
    }

    /**
     * Returns the email as the username for authentication.
     *
     * @return The user's email.
     */
    @Override
    public String getUsername() {
        return this.getEmail();
    }

    /**
     * Indicates whether the user's account has expired. Always returns true (non-expiring).
     *
     * @return true if the account is non-expiring.
     */
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    /**
     * Indicates whether the user's account is locked. Always returns true (non-locked).
     *
     * @return true if the account is not locked.
     */
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    /**
     * Indicates whether the user's credentials (password) have expired. Always returns true (non-expiring).
     *
     * @return true if the credentials are non-expiring.
     */
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    /**
     * Indicates whether the user's account is enabled. Always returns true (enabled).
     *
     * @return true if the account is enabled.
     */
    @Override
    public boolean isEnabled() {
        return true;
    }
}
