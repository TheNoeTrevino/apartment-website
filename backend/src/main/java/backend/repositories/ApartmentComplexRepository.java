package backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import backend.models.ApartmentComplex;

public interface ApartmentComplexRepository extends JpaRepository<ApartmentComplex, Long>,
        JpaSpecificationExecutor<ApartmentComplex> {
    @Modifying
    @Query("delete ApartmentComplex d where d.id = :id")
    int deleteByApartmentComplexId(@Param(value = "id") Long id);
}
