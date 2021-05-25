package pl.infoshare.springdi.claims;

import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
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

    @SneakyThrows
    public ClaimEligibility createClaim(ClaimRequest request) {
        var startAirport = finder.find(request.getStartingAirport()).orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Start Airport not found"));
        Thread.sleep(1000);
        var destAirport = finder.find(request.getDestinationAirport()).orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Dest Airport not found"));

        var claim = new Claim(request.getType(), request.getTotalDelay(), request.getFlightDate(), startAirport, destAirport);
        return claim.getEligibility();
    }
}
