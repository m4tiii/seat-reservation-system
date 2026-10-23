package pl.rokitowski.core.seatreservation.seatreservationsystem.user;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 20)
    private String name;

    @Column(nullable = false, length = 30)
    private String surname;

    @Column(nullable = false, length = 20)
    private String password;

    @Column(nullable = false, length = 20, unique = true)
    private String email;

    @Column(name = "phone_number",nullable = false, length = 20, unique = true)
    private String phoneNumber;
}
