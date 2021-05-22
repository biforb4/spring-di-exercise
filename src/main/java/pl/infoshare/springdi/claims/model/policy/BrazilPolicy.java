package pl.infoshare.springdi.claims.model.policy;

import pl.infoshare.springdi.airports.model.Continent;
import pl.infoshare.springdi.claims.model.Claim;
import pl.infoshare.springdi.shared.specification.CompositeSpecification;

public class BrazilPolicy extends CompositeSpecification<Claim> {

    @Override
    public boolean isSatisfiedBy(Claim candidate) {
        return new StartAirportContinentMatches(Continent.SA)
                .and(new DestAirportContinentMatches(Continent.SA))
                .and(new DelayedFlight())
                .and(new DelayTimeExceeds(4*60))
                .and(new FlightNotOlderThan(4))
                .isSatisfiedBy(candidate);
    }
}
