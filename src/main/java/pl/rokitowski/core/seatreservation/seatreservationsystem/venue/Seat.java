package pl.rokitowski.core.seatreservation.seatreservationsystem.venue;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "seat")
@Getter
@Setter
@NoArgsConstructor
public class Seat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "venue_id", nullable = false)
    private Venue venue;

    @Column(name = "seat_sector", nullable = false, length = 20)
    private String seatSector;

    @Column(name = "seat_row", nullable = false, length = 20)
    private String seatRow;

    @Column(name = "seat_number", nullable = false, length = 20)
    private String seatNumber;
}
