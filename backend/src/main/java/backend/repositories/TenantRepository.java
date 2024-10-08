package backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import backend.models.Tenant;

@Repository
public interface TenantRepository extends JpaRepository<Tenant, Long>,
        JpaSpecificationExecutor<Tenant> {}
