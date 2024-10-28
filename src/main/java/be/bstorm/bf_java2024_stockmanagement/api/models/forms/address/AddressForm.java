package be.bstorm.bf_java2024_stockmanagement.api.models.forms.address;

import be.bstorm.bf_java2024_stockmanagement.dl.entities.Address;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record AddressForm(
        @NotBlank @Size(max = 100)
        String street,
        @NotBlank @Size(max = 100)
        String city,
        @Size(max = 100)
        String municipality,
        @NotBlank @Size(max = 10)
        String zip
) {

    public Address toAddress(){
        return new Address(street, city, municipality, zip);
    }
}
