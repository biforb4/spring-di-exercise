package pl.infoshare.springdi.claims.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Value;
import pl.infoshare.springdi.airports.model.Airport;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.Comparator;
import java.util.stream.Stream;

/**
 * Uwaga: Potrzebne dopiero do zadania piątego.
 */
@Value
public class Claim {
    private final static BigDecimal MINUTES_IN_HOUR = BigDecimal.valueOf(60);

    ClaimType type;
    Integer totalDelay;
    LocalDate flightDate;
    Airport startAirport;
    Airport destAirport;

    public BigDecimal getTotalDelayInHours() {
        return BigDecimal.valueOf(totalDelay).divide(MINUTES_IN_HOUR, RoundingMode.HALF_UP);
    }

    public boolean isCancelled() {
        return type == ClaimType.CANCELLED;
    }

    public boolean isDelayed() {
        return type == ClaimType.DELAYED;
    }

    public ClaimEligibility getEligibility() {
        var regulation = Stream.of(ClaimRegulation.values())
                .filter(c -> c.eligible(this))
                .max(Comparator.comparing(o -> o.PRIORITY));

        if (regulation.isEmpty()) {
            return new ClaimEligibility(false, ClaimRegulation.NONE);
        }

        return new ClaimEligibility(true, regulation.get());
    }
}

