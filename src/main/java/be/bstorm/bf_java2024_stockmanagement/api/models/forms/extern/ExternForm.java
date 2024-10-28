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

public record ExternForm(

        @NotBlank @Size(max = 123)
        String firstName,
        @NotBlank @Size(max = 80)
        String lastName,
        @NotBlank @Email @Size(max = 320)
        String email,
        @Size(max = 17)
        String phoneNumber,
        @NotNull
        ExternType externType,
        @NotNull
        AddressForm addressForm
) {

    public Extern toExtern(){
        return switch (externType){
            case CLIENT -> new Client(firstName,lastName,email,phoneNumber,addressForm.toAddress());
            case SUPPLIER -> new Supplier(firstName,lastName,email,phoneNumber,addressForm.toAddress());
        };
    }
}
