package pl.infoshare.springdi.shared.specification;

public class ConjunctionSpecification<T> extends CompositeSpecification<T> {
    private final Specification<T>[] specifications;

    public ConjunctionSpecification(Specification<T>... specifications) {
        this.specifications = specifications;
    }

    @Override
    public boolean isSatisfiedBy(T candidate) {
        for (Specification<T> specification : specifications) {
            if (!specification.isSatisfiedBy(candidate)) {
                return false;
            }
        }
        return true;
    }
}
