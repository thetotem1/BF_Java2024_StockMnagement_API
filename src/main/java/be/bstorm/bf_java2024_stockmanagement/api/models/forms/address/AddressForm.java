package be.bstorm.bf_java2024_stockmanagement.api.models.forms.address;

import be.bstorm.bf_java2024_stockmanagement.dl.entities.Address;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

/**
 * Form Data Transfer Object (DTO) for capturing address input data.
 * This record is used to collect and validate user input for address fields.
 *
 * <p>Fields:
 * <ul>
 * <li>{@code street} - The street name and number, must be provided, with a maximum length of 100 characters.</li>
 * <li>{@code city} - The city name, must be provided, with a maximum length of 100 characters.</li>
 * <li>{@code municipality} - The optional municipality name, with a maximum length of 100 characters.</li>
 * <li>{@code zip} - The postal or zip code, must be provided, with a maximum length of 10 characters.</li>
 * </ul>
 * </p>
 *
 * <p>Methods:
 * <ul>
 * <li>{@link #toAddress()} - Converts this form into an {@link Address} entity.</li>
 * </ul>
 * </p>
 *
 * @see Address
 */
public record AddressForm(
        @NotBlank @Size(max = 100) String street,
        @NotBlank @Size(max = 100) String city,
        @Size(max = 100) String municipality,
        @NotBlank @Size(max = 10) String zip
) {

    /**
     * Converts this form data into an {@link Address} entity.
     *
     * @return A new {@link Address} populated with data from this form.
     */
    public Address toAddress(){
        return new Address(street, city, municipality, zip);
    }
}
