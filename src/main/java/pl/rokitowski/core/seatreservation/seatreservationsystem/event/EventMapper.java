package pl.rokitowski.core.seatreservation.seatreservationsystem.event;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface EventMapper {
    @Mapping(target = "venueId", source = "venue.id")
    EventResponse toResponse(Event event);

    List<EventResponse> toResponseList(List<Event> events);
}
