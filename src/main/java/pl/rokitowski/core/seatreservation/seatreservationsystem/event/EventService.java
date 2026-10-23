package pl.rokitowski.core.seatreservation.seatreservationsystem.event;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class EventService {
    private final EventRepository eventRepository;
    private final EventMapper eventMapper;

    public List<EventResponse> getAllEvents(){
        List<Event> events = eventRepository.findAll();
        return eventMapper.toResponseList(events);
    }
}
