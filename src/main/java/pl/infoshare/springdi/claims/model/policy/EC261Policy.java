package pl.infoshare.springdi.claims.model.policy;

import pl.infoshare.springdi.airports.model.Continent;
import pl.infoshare.springdi.claims.model.Claim;
import pl.infoshare.springdi.shared.specification.CompositeSpecification;

public class EC261Policy extends CompositeSpecification<Claim> {
    @Override
    public boolean isSatisfiedBy(Claim candidate){
        return new StartAirportContinent(Continent.EU)
                .and(new DestAirportContinentPolicy(Continent.EU))
                .and(new DelayTimeExceeds(3*60))
                .and(new FlightNotOlderThan(2))
                .isSatisfiedBy(candidate);
    }
}
