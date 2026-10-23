package pl.rokitowski.core.seatreservation.seatreservationsystem.venue;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "address")
@Getter
@Setter
@NoArgsConstructor
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 50)
    private String city;

    @Column(name = "postal_code",nullable = false, length = 10)
    private String postalCode;

    @Column(nullable = false, length = 50)
    private String street;

    @Column(name = "building_number",nullable = false, length = 10)
    private String buildingNumber;

    @Column(name = "flat_number",nullable = false, length = 10)
    private String flatNumber;
}
