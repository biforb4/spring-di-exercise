package pl.infoshare.springdi.claims.model.policy;

import pl.infoshare.springdi.claims.model.Claim;
import pl.infoshare.springdi.shared.specification.CompositeSpecification;

public class DelayTimeExceeds extends CompositeSpecification<Claim> {
    private final Integer minutes;

    public DelayTimeExceeds(Integer minutes) {
        this.minutes = minutes;
    }

    @Override
    public boolean isSatisfiedBy(Claim candidate) {
        return candidate.getTotalDelay() > minutes;
    }
}
