package pl.infoshare.springdi.claims.model.policy;

import pl.infoshare.springdi.airports.model.Continent;
import pl.infoshare.springdi.claims.model.Claim;
import pl.infoshare.springdi.shared.specification.CompositeSpecification;

public class StartAirportContinentMatches extends CompositeSpecification<Claim> {

    private final Continent continent;

    public StartAirportContinentMatches(Continent continent) {
        this.continent = continent;
    }

    @Override
    public boolean isSatisfiedBy(Claim candidate) {
        return candidate.getStartAirport().getContinent() == this.continent;
    }
}
