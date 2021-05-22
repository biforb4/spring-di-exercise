package pl.infoshare.springdi.shared.specification;

abstract public class CompositeSpecification<T> implements Specification<T>{
    @Override
    public Specification<T> and(Specification<T> other) {
        return new AndSpecification<T>(this, other);
    }
}
