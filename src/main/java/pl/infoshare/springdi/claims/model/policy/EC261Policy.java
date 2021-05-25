package pl.infoshare.springdi.claims.model.policy;

import pl.infoshare.springdi.airports.model.Continent;
import pl.infoshare.springdi.claims.model.Claim;
import pl.infoshare.springdi.shared.specification.CompositeSpecification;
import pl.infoshare.springdi.shared.specification.ConjunctionSpecification;

public class EC261Policy extends CompositeSpecification<Claim> {
    @Override
    public boolean isSatisfiedBy(Claim candidate) {
        var delayedFlightPolicy = new ConjunctionSpecification<>(
                new DelayedFlight(),
                new DelayTimeExceeds(3 * 60),
                new FlightNotOlderThan(2)
        );
        var canceledFlightPolicy = new ConjunctionSpecification<>(
                new CancelledFlight(),
                new FlightNotOlderThan(3)
        );

        return new StartAirportContinentMatches(Continent.EU)
                .or(new DestAirportContinentMatches(Continent.EU))
                .and(delayedFlightPolicy)
                .or(canceledFlightPolicy)
                .isSatisfiedBy(candidate);
    }

}
