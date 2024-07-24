package backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import backend.models.Apartment;

@Repository
public interface ApartmentRepository extends JpaRepository<Apartment, Long>,
        JpaSpecificationExecutor<Apartment> {
}

