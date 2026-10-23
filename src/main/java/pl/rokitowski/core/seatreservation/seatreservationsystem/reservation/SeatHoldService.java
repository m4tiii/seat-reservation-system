package pl.rokitowski.core.seatreservation.seatreservationsystem.reservation;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.time.Duration;

@Service
@RequiredArgsConstructor
public class SeatHoldService {
    private final StringRedisTemplate redisTemplate;

    private static final Duration HOLD_TIMEOUT = Duration.ofMinutes(10);

    private String buildKey(Long eventId, Long seatId){
        return "hold:event:"+eventId+":seat:"+seatId;
    }

    public boolean holdSeat(Long eventId, Long seatId, String identifier){
        String key = buildKey(eventId, seatId);

        Boolean success = redisTemplate.opsForValue().setIfAbsent(key, identifier, HOLD_TIMEOUT);
        return Boolean.TRUE.equals(success);
    }

    public void releaseSeat(Long eventId, Long seatId){
        String key = buildKey(eventId, seatId);
        redisTemplate.delete(key);
    }

    public boolean isSeatHeld(Long eventId, Long seatId){
        String key = buildKey(eventId, seatId);
        return Boolean.TRUE.equals(redisTemplate.hasKey(key));
    }
}
