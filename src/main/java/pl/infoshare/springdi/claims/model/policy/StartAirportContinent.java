package pl.infoshare.springdi.claims.model.policy;

import pl.infoshare.springdi.airports.model.Continent;
import pl.infoshare.springdi.claims.model.Claim;
import pl.infoshare.springdi.shared.specification.CompositeSpecification;

public class StartAirportContinent extends CompositeSpecification<Claim> {

    private final Continent continent;

    public StartAirportContinent(Continent continent) {
        this.continent = continent;
    }

    @Override
    public boolean isSatisfiedBy(Claim candidate) {
        return candidate.getStartAirport().getContinent() == this.continent;
    }
}
