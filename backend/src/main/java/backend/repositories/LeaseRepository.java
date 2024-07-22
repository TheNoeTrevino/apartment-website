package backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import backend.models.Lease;

@Repository
public interface LeaseRepository extends JpaRepository<Lease, Long>,
        JpaSpecificationExecutor<Lease> {}

