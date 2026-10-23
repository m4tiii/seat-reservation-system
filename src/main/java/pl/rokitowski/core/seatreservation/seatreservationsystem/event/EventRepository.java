package pl.rokitowski.core.seatreservation.seatreservationsystem.event;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.OffsetDateTime;
import java.util.List;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {
    List<Event> findAllByVenueId(Long venueId);

    List<Event> findAllByStartDateAfterOrderByStartDateAsc(OffsetDateTime dateTime);

    @Query("SELECT e FROM Event e WHERE LOWER(e.title) LIKE LOWER(CONCAT('%', :title, '%')) ")
    List<Event> searchByTitle(@Param("title") String title);
}
