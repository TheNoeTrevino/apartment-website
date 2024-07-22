package backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import backend.models.MaintenanceRequest;

public interface MaintenanceRequestRepository extends JpaRepository<MaintenanceRequest, Long> {
}
