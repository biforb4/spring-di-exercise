package pl.infoshare.springdi.claims.model;

import pl.infoshare.springdi.claims.model.policy.BrazilPolicy;
import pl.infoshare.springdi.claims.model.policy.EC261Policy;

import java.util.stream.Stream;

/**
 * Uwaga: Potrzebne dopiero do zadania piątego.
 */
public enum ClaimRegulation {
    EC_261 {
        public final Integer PRIORITY = 10;

        public boolean eligible(Claim claim) {
            return new EC261Policy().isSatisfiedBy(claim);
        }
    },
    BRAZIL {
        public final Integer PRIORITY = 5;

        public boolean eligible(Claim claim) {
            return new BrazilPolicy().isSatisfiedBy(claim);
        }
    },
    NONE;

    public final Integer PRIORITY = 0;

    public boolean eligible(Claim claim) {
        return false;
    }
}
