package backend.specifications;

import java.time.LocalDate;

import org.springframework.data.jpa.domain.Specification;

import backend.models.Apartment;
import backend.models.Lease;
import backend.models.Tenant;

public class LeaseSpecifications {

    public static Specification<Lease> queryLeaseStartDate(LocalDate leaseStartDate) {
        return (root, query, criteriaBuilder) -> {
            if (leaseStartDate == null) return null;
            return criteriaBuilder.equal(root.get("leaseStartDate"), leaseStartDate);
        };
    }

    public static Specification<Lease> queryLeaseEndDate(LocalDate leaseEndDate) {
        return (root, query, criteriaBuilder) -> {
            if (leaseEndDate == null) return null;
            return criteriaBuilder.equal(root.get("leaseEndDate"), leaseEndDate);
        };
    }

    public static Specification<Lease> queryRentAmount(Double rentAmount) {
        return (root, query, criteriaBuilder) ->
            criteriaBuilder.equal(root.get("rentAmount"), rentAmount);
    }

    public static Specification<Lease> querySecurityDepositAmount(Double securityDepositAmount) {
        return (root, query, criteriaBuilder) ->
            criteriaBuilder.equal(root.get("securityDepositAmount"), securityDepositAmount);
    }

    public static Specification<Lease> queryTenant(Tenant tenant) {
        return (root, query, criteriaBuilder) ->
            criteriaBuilder.equal(root.get("tenant"), tenant);
    }

    public static Specification<Lease> queryApartment(Apartment apartment) {
        return (root, query, criteriaBuilder) ->
            criteriaBuilder.equal(root.get("apartment"), apartment);
    }

    public static Specification<Lease> leasesExpiringSoon(LocalDate endDate) {
        return (root, query, criteriaBuilder) ->
            criteriaBuilder.lessThanOrEqualTo(root.get("leaseEndDate"), endDate);
    }
}

