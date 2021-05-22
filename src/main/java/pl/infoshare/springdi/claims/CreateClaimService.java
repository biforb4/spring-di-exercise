package pl.infoshare.springdi.claims;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;
import pl.infoshare.springdi.airports.AirportFinder;
import pl.infoshare.springdi.claims.model.Claim;
import pl.infoshare.springdi.claims.model.ClaimEligibility;

@Component
@AllArgsConstructor
public class CreateClaimService {
    private final AirportFinder finder;

    public ClaimEligibility createClaim(ClaimDto dto) {
        var startAirport = finder.find(dto.getStartingAirport()).orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Start Airport not found"));
        var destAirport = finder.find(dto.getDestinationAirport()).orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Dest Airport not found"));

        var claim = new Claim(dto.getType(), dto.getTotalDelay(), dto.getFlightDate(), startAirport, destAirport);
        return claim.getEligibility();
    }
}
