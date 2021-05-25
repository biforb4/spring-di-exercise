package pl.infoshare.springdi.claims.model.policy;

import pl.infoshare.springdi.claims.model.Claim;
import pl.infoshare.springdi.shared.specification.CompositeSpecification;

public class CancelledFlight extends CompositeSpecification<Claim> {
    @Override
    public boolean isSatisfiedBy(Claim candidate) {
        return candidate.isCancelled();
    }
}
