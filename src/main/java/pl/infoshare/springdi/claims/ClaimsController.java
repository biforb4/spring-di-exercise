package pl.infoshare.springdi.claims;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import pl.infoshare.springdi.claims.model.ClaimEligibility;

@RestController
@AllArgsConstructor
public class ClaimsController {
    private final CreateClaimService claimService;

    @PostMapping("/api/eligibility-check")
    public ClaimEligibility postClaim(@RequestBody ClaimDto dto) {
        return claimService.createClaim(dto);
    }
}
