package pl.infoshare.springdi.claims;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Value;
import pl.infoshare.springdi.claims.model.ClaimType;

import java.time.LocalDate;

/**
 * Uwaga: Potrzebne dopiero do zadania piątego.
 */
@Value
public class ClaimRequest {

    ClaimType type;
    Integer totalDelay;
    String startingAirport;
    String destinationAirport;
    LocalDate flightDate;

    @JsonCreator
    public ClaimRequest(@JsonProperty("type") ClaimType type,
                        @JsonProperty("totalDelay") Integer totalDelay,
                        @JsonProperty("startingAirport") String startingAirport,
                        @JsonProperty("destinationAirport") String destinationAirport,
                        @JsonProperty("flightDate") LocalDate flightDate) {
        this.type = type;
        this.totalDelay = totalDelay;
        this.startingAirport = startingAirport;
        this.destinationAirport = destinationAirport;
        this.flightDate = flightDate;
    }
}

