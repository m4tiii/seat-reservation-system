package pl.rokitowski.core.seatreservation.seatreservationsystem.reservation;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.rokitowski.core.seatreservation.seatreservationsystem.user.User;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "reservation")
@Getter
@Setter
@NoArgsConstructor
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User userId;

    @Column(name = "guest_email", length = 100)
    private String guestEmail;

    @Column(name = "guest_name", length = 20)
    private String guestName;

    @Column(name = "guest_surname", length = 30)
    private String guestSurname;

    @Column(name = "guest_phone", length = 20)
    private String guestPhoneNumber;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 10)
    private ReservationStatus status;

    @Column(nullable = false, updatable = false)
    private OffsetDateTime date;

    @Column(name = "expiration_date", nullable = false, updatable = false)
    private OffsetDateTime expirationDate;

    @OneToMany(mappedBy = "reservation", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Ticket> tickets = new ArrayList<>();

}
