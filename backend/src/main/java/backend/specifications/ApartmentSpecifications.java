package backend.specifications;

import java.time.LocalDate;

import org.springframework.data.jpa.domain.Specification;

import backend.models.Apartment;
import backend.models.ApartmentComplex;

public class ApartmentSpecifications {

    public static Specification<Apartment> queryApartmentName(String apartmentName) {
        return (root, query, criteriaBuilder) ->
            criteriaBuilder.like(root.get("apartmentName"), "%" + apartmentName + "%");
    }

    public static Specification<Apartment> queryApartmentLocation(String apartmentLocation) {
        return (root, query, criteriaBuilder) ->
            criteriaBuilder.equal(root.get("apartmentLocation"), apartmentLocation);
    }

    public static Specification<Apartment> queryNumOfRooms(Integer numOfRooms) {
        return (root, query, criteriaBuilder) ->
            criteriaBuilder.equal(root.get("numOfRooms"), numOfRooms);
    }

    public static Specification<Apartment> querySquareFootage(Integer squareFootage) {
        return (root, query, criteriaBuilder) ->
            criteriaBuilder.equal(root.get("squareFootage"), squareFootage);
    }

    public static Specification<Apartment> queryDateBuilt(LocalDate dateBuilt) {
        return (root, query, criteriaBuilder) -> {
            if (dateBuilt == null) return null;
            return criteriaBuilder.equal(root.get("dateBuilt"), dateBuilt);
        };
    }

    public static Specification<Apartment> queryCurrentTenantName(String currentTenantName) {
        return (root, query, criteriaBuilder) ->
            criteriaBuilder.equal(root.get("currentTenantName"), currentTenantName);
    }

    public static Specification<Apartment> queryCurrentTenantEmail(String currentTenantEmail) {
        return (root, query, criteriaBuilder) ->
            criteriaBuilder.equal(root.get("currentTenantEmail"), currentTenantEmail);
    }

    public static Specification<Apartment> queryCurrentTenantPhone(String currentTenantPhone) {
        return (root, query, criteriaBuilder) ->
            criteriaBuilder.equal(root.get("currentTenantPhone"), currentTenantPhone);
    }

    public static Specification<Apartment> queryApartmentComplex(ApartmentComplex apartmentComplex) {
        return (root, query, criteriaBuilder) ->
            criteriaBuilder.equal(root.get("apartmentComplex"), apartmentComplex);
    }
}

