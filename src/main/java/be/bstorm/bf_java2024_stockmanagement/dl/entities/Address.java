package be.bstorm.bf_java2024_stockmanagement.dl.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.*;

/**
 * Represents an address entity, containing details about the street, city, municipality, and zip code.
 * This class is embeddable in other entities, meaning it can be a component of larger entity objects.
 * Utilizes Lombok annotations for common methods like getters, setters, and overrides.
 *
 * <p>Fields:
 * <ul>
 * <li>{@code street} - The street name and number (required, max length 100).</li>
 * <li>{@code city} - The city name (required, max length 100).</li>
 * <li>{@code municipality} - The municipality or subdivision within a city (optional, max length 100).</li>
 * <li>{@code zip} - The postal code (required, max length 10).</li>
 * </ul>
 * </p>
 *
 * @see jakarta.persistence.Embeddable
 * @see lombok.Getter
 * @see lombok.Setter
 * @see lombok.NoArgsConstructor
 * @see lombok.AllArgsConstructor
 * @see lombok.EqualsAndHashCode
 * @see lombok.ToString
 *
 */
@Embeddable
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@EqualsAndHashCode @ToString
public class Address {

    /**
     * The street name and number.
     * Cannot be null; max length of 100 characters.
     */
    @Column(nullable = false, length = 100)
    private String street;

    /**
     * The name of the city.
     * Cannot be null; max length of 100 characters.
     */
    @Column(nullable = false, length = 100)
    private String city;

    /**
     * The municipality or subdivision within a city.
     * Optional; max length of 100 characters.
     */
    @Column(nullable = true, length = 100)
    private String municipality;

    /**
     * The postal code of the address.
     * Cannot be null; max length of 10 characters.
     */
    @Column(nullable = false, length = 10)
    private String zip;
}
