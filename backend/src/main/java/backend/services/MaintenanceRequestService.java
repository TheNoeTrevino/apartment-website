package backend.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import backend.models.MaintenanceRequest;
import backend.repositories.MaintenanceRequestRepository;

import java.util.List;

@Service
public class MaintenanceRequestService {

  @Autowired
  private MaintenanceRequestRepository maintenanceRequestRepository;

  public List<MaintenanceRequest> getAllRequests() {
    return maintenanceRequestRepository.findAll();
  }

  public MaintenanceRequest getRequestById(Long id) {
    return maintenanceRequestRepository.findById(id).orElse(null);
  }

  public MaintenanceRequest createRequest(MaintenanceRequest request) {
    return maintenanceRequestRepository.save(request);
  }

  public MaintenanceRequest updateRequest(Long id, MaintenanceRequest requestDetails) {
    MaintenanceRequest request = maintenanceRequestRepository.findById(id).orElse(null);
    if (request != null) {
      request.setDescription(requestDetails.getDescription());
      request.setRequestDate(requestDetails.getRequestDate());
      request.setTenant(requestDetails.getTenant());
      request.setType(requestDetails.getType());
      return maintenanceRequestRepository.save(request);
    } else {
      return null;
    }
  }

  public void deleteRequest(Long id) {
    maintenanceRequestRepository.deleteById(id);
  }
}
