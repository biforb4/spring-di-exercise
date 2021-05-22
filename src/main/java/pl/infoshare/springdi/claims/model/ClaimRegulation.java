package pl.infoshare.springdi.claims.model;

import pl.infoshare.springdi.claims.model.policy.EC261Policy;

import java.util.stream.Stream;

/**
 * Uwaga: Potrzebne dopiero do zadania piątego.
 */
public enum ClaimRegulation {
    EC_261 {
        protected boolean eligible(Claim claim) {
            return new EC261Policy().isSatisfiedBy(claim);
        }
    },
    BRAZIL {
        protected boolean eligible(Claim claim) {
            return true;
        }
    }, NONE;

    static ClaimEligibility getEligibilityfor(Claim claim) {
        var regulation = Stream.of(ClaimRegulation.values())
                .filter(c -> c.eligible(claim))
                .findFirst();

        if (regulation.isEmpty()) {
            return new ClaimEligibility(false, ClaimRegulation.NONE);
        }

        return new ClaimEligibility(true, regulation.get());
    }

    protected boolean eligible(Claim claim) {
        return false;
    }
}
