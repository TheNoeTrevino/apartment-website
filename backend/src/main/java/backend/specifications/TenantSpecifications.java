package backend.specifications;

import java.time.LocalDate;

import org.springframework.data.jpa.domain.Specification;

import backend.models.Apartment;
import backend.models.Tenant;

public class TenantSpecifications {

    public static Specification<Tenant> queryFirstName(String firstName) {
        return (root, query, criteriaBuilder) ->
            criteriaBuilder.like(root.get("firstName"), "%" + firstName + "%");
    }

    public static Specification<Tenant> queryLastName(String lastName) {
        return (root, query, criteriaBuilder) ->
            criteriaBuilder.like(root.get("lastName"), "%" + lastName + "%");
    }

    public static Specification<Tenant> queryDateOfBirth(LocalDate dateOfBirth) {
        return (root, query, criteriaBuilder) -> {
            if (dateOfBirth == null) return null;
            return criteriaBuilder.equal(root.get("dateOfBirth"), dateOfBirth);
        };
    }

    public static Specification<Tenant> queryEmail(String email) {
        return (root, query, criteriaBuilder) ->
            criteriaBuilder.equal(root.get("email"), email);
    }

    public static Specification<Tenant> queryPhoneNumber(String phoneNumber) {
        return (root, query, criteriaBuilder) ->
            criteriaBuilder.equal(root.get("phoneNumber"), phoneNumber);
    }

    public static Specification<Tenant> querySocialSecurityNumber(String socialSecurityNumber) {
        return (root, query, criteriaBuilder) ->
            criteriaBuilder.equal(root.get("socialSecurityNumber"), socialSecurityNumber);
    }

    public static Specification<Tenant> queryDriversLicenseNumber(String driversLicenseNumber) {
        return (root, query, criteriaBuilder) ->
            criteriaBuilder.equal(root.get("driversLicenseNumber"), driversLicenseNumber);
    }

    public static Specification<Tenant> queryCurrentAddress(String currentAddress) {
        return (root, query, criteriaBuilder) ->
            criteriaBuilder.equal(root.get("currentAddress"), currentAddress);
    }

    public static Specification<Tenant> queryPreviousAddress(String previousAddress) {
        return (root, query, criteriaBuilder) ->
            criteriaBuilder.equal(root.get("previousAddress"), previousAddress);
    }

    public static Specification<Tenant> queryEmployerName(String employerName) {
        return (root, query, criteriaBuilder) ->
            criteriaBuilder.equal(root.get("employerName"), employerName);
    }

    public static Specification<Tenant> queryJobTitle(String jobTitle) {
        return (root, query, criteriaBuilder) ->
            criteriaBuilder.equal(root.get("jobTitle"), jobTitle);
    }

    public static Specification<Tenant> queryAnnualIncome(Integer annualIncome) {
        return (root, query, criteriaBuilder) ->
            criteriaBuilder.equal(root.get("annualIncome"), annualIncome);
    }

    public static Specification<Tenant> queryEmergencyContactName(String emergencyContactName) {
        return (root, query, criteriaBuilder) ->
            criteriaBuilder.equal(root.get("emergencyContactName"), emergencyContactName);
    }

    public static Specification<Tenant> queryEmergencyContactRelationship(String emergencyContactRelationship) {
        return (root, query, criteriaBuilder) ->
            criteriaBuilder.equal(root.get("emergencyContactRelationship"), emergencyContactRelationship);
    }

    public static Specification<Tenant> queryEmergencyContactPhone(String emergencyContactPhone) {
        return (root, query, criteriaBuilder) ->
            criteriaBuilder.equal(root.get("emergencyContactPhone"), emergencyContactPhone);
    }

    public static Specification<Tenant> queryReferenceName(String referenceName) {
        return (root, query, criteriaBuilder) ->
            criteriaBuilder.equal(root.get("referenceName"), referenceName);
    }

    public static Specification<Tenant> queryReferenceRelationship(String referenceRelationship) {
        return (root, query, criteriaBuilder) ->
            criteriaBuilder.equal(root.get("referenceRelationship"), referenceRelationship);
    }

    public static Specification<Tenant> queryReferencePhone(String referencePhone) {
        return (root, query, criteriaBuilder) ->
            criteriaBuilder.equal(root.get("referencePhone"), referencePhone);
    }

    public static Specification<Tenant> queryApartment(Apartment apartment) {
        return (root, query, criteriaBuilder) ->
            criteriaBuilder.equal(root.get("apartment"), apartment);
    }
}

