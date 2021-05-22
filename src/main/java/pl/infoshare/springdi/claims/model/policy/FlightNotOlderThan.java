package pl.infoshare.springdi.claims.model.policy;

import pl.infoshare.springdi.claims.model.Claim;
import pl.infoshare.springdi.shared.specification.CompositeSpecification;

import java.time.LocalDate;
import java.time.Period;

public class FlightNotOlderThan extends CompositeSpecification<Claim> {
    private final Integer years;

    public FlightNotOlderThan(Integer years) {
        this.years = years;
    }

    @Override
    public boolean isSatisfiedBy(Claim candidate) {
        var now = LocalDate.now();
        var diff = Period.between(candidate.getFlightDate(), now);

        return diff.getYears() <= years;
    }
}
