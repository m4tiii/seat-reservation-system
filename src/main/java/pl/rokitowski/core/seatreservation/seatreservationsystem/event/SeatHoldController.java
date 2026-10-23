package pl.rokitowski.core.seatreservation.seatreservationsystem.event;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.rokitowski.core.seatreservation.seatreservationsystem.reservation.SeatHoldService;

@RestController
@RequestMapping("/api/holds")
@RequiredArgsConstructor
public class SeatHoldController {
    private final SeatHoldService seatHoldService;

    @PostMapping("/event/{eventId}/seat/{seatId}")
    public ResponseEntity<String> holdSeat(
      @PathVariable Long eventId,
      @PathVariable Long seatId,
      @RequestParam(defaultValue = "user-1") String userToken
    ){
        boolean locked = seatHoldService.holdSeat(eventId, seatId, userToken);
        if(!locked){
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body("Miejsce jest już zablokowane przez innego użytkownika");
        }
        return ResponseEntity.ok("Miejsce zablokowane na 10 minut");
    }

    @GetMapping("/event/{eventId}/seat/{seatId}")
    public ResponseEntity<Boolean> isHeld(
            @PathVariable Long eventId,
            @PathVariable Long seatId
    ){
        return ResponseEntity.ok(seatHoldService.isSeatHeld(eventId, seatId));
    }
}
