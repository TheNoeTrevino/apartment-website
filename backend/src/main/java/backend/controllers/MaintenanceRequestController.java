package backend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import backend.models.MaintenanceRequest;
import backend.services.MaintenanceRequestService;
import jakarta.validation.Valid;

import java.util.List;

@RestController
@RequestMapping("/maintenance-requests")
public class MaintenanceRequestController {

  @Autowired
  private MaintenanceRequestService maintenanceRequestService;

  @GetMapping("")
  public List<MaintenanceRequest> getAllRequests() {
    return maintenanceRequestService.getAllRequests();
  }

  @GetMapping("/{id}")
  public ResponseEntity<MaintenanceRequest> getRequestById(@PathVariable Long id) {
    MaintenanceRequest request = maintenanceRequestService.getRequestById(id);
    if (request != null) {
      return ResponseEntity.ok(request);
    } else {
      return ResponseEntity.notFound().build();
    }
  }

  @PostMapping("")
  public MaintenanceRequest createRequest(@Valid @RequestBody MaintenanceRequest request) {
    return maintenanceRequestService.createRequest(request);
  }

  @PatchMapping("/{id}")
  public ResponseEntity<MaintenanceRequest> updateRequest(@PathVariable Long id,
      @Valid @RequestBody MaintenanceRequest requestDetails) {
    MaintenanceRequest updatedRequest = maintenanceRequestService.updateRequest(id, requestDetails);
    if (updatedRequest != null) {
      return ResponseEntity.ok(updatedRequest);
    } else {
      return ResponseEntity.notFound().build();
    }
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteRequest(@PathVariable Long id) {
    maintenanceRequestService.deleteRequest(id);
    return ResponseEntity.ok().build();
  }
}
