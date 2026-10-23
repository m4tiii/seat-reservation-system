package pl.rokitowski.core.seatreservation.seatreservationsystem.reservation;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Long> {
    @Query("""
        SELECT t.seat.id FROM Ticket t
        WHERE t.event.id = :eventId
        AND t.seat.id = :seatIds
        AND t.reservation.status IN ('PENDING', 'CONFIRMED')
    """)
    List<Long> findTakenSeatIds(@Param("eventId") Long eventId,
                                @Param("seatId") List<Long> seatId);

    List<Ticket> findAllByEventId(Long eventId);
    List<Ticket> findAllByReservationId(Long reservationId);
}
