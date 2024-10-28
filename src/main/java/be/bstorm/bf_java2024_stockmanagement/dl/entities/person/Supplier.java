package be.bstorm.bf_java2024_stockmanagement.dl.entities.person;

import be.bstorm.bf_java2024_stockmanagement.dl.entities.Address;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.UUID;

@Entity
@DiscriminatorValue("SUPPLIER")
@NoArgsConstructor
@EqualsAndHashCode(callSuper=true) @ToString(callSuper=true)
public class Supplier extends Extern{

    public Supplier(String firstName, String lastName, String email, String phoneNumber, Address address) {
        super(firstName, lastName, email, phoneNumber, address);
    }

    public Supplier(UUID id, String firstName, String lastName, String email, String phoneNumber, Address address) {
        super(id, firstName, lastName, email, phoneNumber, address);
    }
}
