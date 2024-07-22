package backend.specifications;

import java.time.LocalDate;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.lang.Nullable;

import backend.models.ApartmentComplex;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

public class ApartmentComplexSpecifications implements Specification<ApartmentComplex> {

    public static Specification<ApartmentComplex> queryComplexName(String complexName) {
        return (root, query, criteriaBuilder) -> {
            return criteriaBuilder.like(root.get("complexName"), complexName);
        };
    }

    public static Specification<ApartmentComplex> queryComplexLocation(String complexLocation) {
        return (root, query, criteriaBuilder) -> {
            return criteriaBuilder.equal(root.get("complexLocation"), complexLocation);
        };
    }

    public static Specification<ApartmentComplex> queryNumOfBuildings(Integer numOfBuildings) {
        return (root, query, criteriaBuilder) -> {
            return criteriaBuilder.equal(root.get("numOfBuildings"), numOfBuildings);
        };
    }

    public static Specification<ApartmentComplex> queryNumOfUnits(Integer numOfUnits) {
        return (root, query, criteriaBuilder) -> {
            return criteriaBuilder.equal(root.get("numOfUnits"), numOfUnits);
        };
    }

    public static Specification<ApartmentComplex> queryDateBuilt(LocalDate datebuilt) {
        return (root, query, criteriaBuilder) -> {
            if (datebuilt == null) return null;
            return criteriaBuilder.equal(root.get("datebuilt"), datebuilt);
        };
    }

    public static Specification<ApartmentComplex> queryManagerName(String managerName) {
        return (root, query, criteriaBuilder) -> {
            return criteriaBuilder.equal(root.get("managerName"), managerName);
        };
    }

    public static Specification<ApartmentComplex> queryManagerEmail(String managerEmail) {
        return (root, query, criteriaBuilder) -> {
            return criteriaBuilder.equal(root.get("managerEmail"), managerEmail);
        };
    }

    public static Specification<ApartmentComplex> queryManagerPhone(String managerPhone) {
        return (root, query, criteriaBuilder) -> {
            return criteriaBuilder.equal(root.get("managerPhone"), managerPhone);
        };
    }

    @SuppressWarnings("null")
    @Override
    @Nullable
    public Predicate toPredicate(Root<ApartmentComplex> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        throw new UnsupportedOperationException("Unimplemented method 'toPredicate'");
    }
}