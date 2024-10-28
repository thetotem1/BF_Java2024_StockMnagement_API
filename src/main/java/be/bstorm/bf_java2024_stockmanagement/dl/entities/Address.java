package be.bstorm.bf_java2024_stockmanagement.dl.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.UniqueConstraint;
import lombok.*;

@Embeddable
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@EqualsAndHashCode @ToString
public class Address {

    @Column(nullable = false,length = 100)
    private String street;
    @Column(nullable = false,length = 100)
    private String city;
    @Column(nullable = true,length = 100)
    private String municipality;
    @Column(nullable = false,length = 10)
    private String zip;
}
