package pl.rokitowski.core.seatreservation.seatreservationsystem.event;

import java.time.OffsetDateTime;

public record EventResponse(
        Long id,
        String title,
        String description,
        OffsetDateTime startDate,
        OffsetDateTime endDate,
        Long venueId
) {}
