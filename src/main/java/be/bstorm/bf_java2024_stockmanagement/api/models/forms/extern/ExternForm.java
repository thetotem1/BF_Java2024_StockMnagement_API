package be.bstorm.bf_java2024_stockmanagement.api.models.forms.extern;

import be.bstorm.bf_java2024_stockmanagement.api.models.forms.extern.enums.ExternType;
import be.bstorm.bf_java2024_stockmanagement.api.models.forms.address.AddressForm;
import be.bstorm.bf_java2024_stockmanagement.dl.entities.person.Client;
import be.bstorm.bf_java2024_stockmanagement.dl.entities.person.Extern;
import be.bstorm.bf_java2024_stockmanagement.dl.entities.person.Supplier;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

/**
 * Form Data Transfer Object (DTO) for capturing external entity (client or supplier) input data.
 * This record is used to collect and validate user input when creating or updating an external entity.
 *
 * <p>Fields:
 * <ul>
 * <li>{@code firstName} - The first name of the external entity, required and cannot exceed 123 characters.</li>
 * <li>{@code lastName} - The last name of the external entity, required and cannot exceed 80 characters.</li>
 * <li>{@code email} - The email address of the external entity, required, must be a valid email format, and cannot exceed 320 characters.</li>
 * <li>{@code phoneNumber} - The optional phone number, with a maximum length of 17 characters.</li>
 * <li>{@code externType} - The type of external entity, indicating whether it is a client or a supplier, required.</li>
 * <li>{@code addressForm} - The {@link AddressForm} object containing address details, required.</li>
 * </ul>
 * </p>
 *
 * <p>Methods:
 * <ul>
 * <li>{@link #toExtern()} - Converts this form into an appropriate {@link Extern} entity, either {@link Client} or {@link Supplier}, based on the {@code externType}.</li>
 * </ul>
 * </p>
 *
 * @see Extern
 * @see ExternType
 * @see Client
 * @see Supplier
 */
public record ExternForm(

        @NotBlank @Size(max = 123) String firstName,
        @NotBlank @Size(max = 80) String lastName,
        @NotBlank @Email @Size(max = 320) String email,
        @Size(max = 17) String phoneNumber,
        @NotNull ExternType externType,
        @NotNull AddressForm addressForm
) {

    /**
     * Converts this form data into an {@link Extern} entity, either {@link Client} or {@link Supplier}, based on the {@code externType}.
     *
     * @return A new {@link Extern} populated with data from this form.
     */
    public Extern toExtern(){
        return switch (externType){
            case CLIENT -> new Client(firstName, lastName, email, phoneNumber, addressForm.toAddress());
            case SUPPLIER -> new Supplier(firstName, lastName, email, phoneNumber, addressForm.toAddress());
        };
    }
}
