
// src/app/maintenance-request/maintenance-request.component.ts
import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule, ReactiveFormsModule, FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ToastrService } from 'ngx-toastr';
import { MaintenanceService } from '../maintenance.service';
import { MaintenanceRequest } from '../models/maintenance-request.model';

@Component({
  selector: 'app-maintenance-request',
  standalone: true,
  imports: [CommonModule, FormsModule, ReactiveFormsModule],
  templateUrl: './maintenance-request.component.html',
  styleUrls: ['./maintenance-request.component.scss']
})
export class MaintenanceRequestComponent {
  maintenanceForm: FormGroup;
  loading = false;
  selectedFiles: File[] = [];

  constructor(
    private fb: FormBuilder,
    private maintenanceService: MaintenanceService,
    private toastr: ToastrService
  ) {
    this.maintenanceForm = this.fb.group({
      tenantId: [null, Validators.required],
      description: ['', Validators.required],
      requestDate: [new Date(), Validators.required],
      status: ['Pending', Validators.required],
      priority: ['Normal', Validators.required]
    });
  }

  onFileSelected(event: Event) {
    const input = event.target as HTMLInputElement;
    if (input.files) {
      Array.from(input.files).forEach(file => this.selectedFiles.push(file));
    }
  }

  onDragOver(event: DragEvent) {
    event.preventDefault();
  }

  onDrop(event: DragEvent) {
    event.preventDefault();
    if (event.dataTransfer?.files) {
      Array.from(event.dataTransfer.files).forEach(file => this.selectedFiles.push(file));
    }
  }

  onSubmit() {
    if (this.maintenanceForm.invalid) {
      return;
    }

    this.loading = true;
    const maintenanceRequest: MaintenanceRequest = this.maintenanceForm.value;

    this.maintenanceService.createMaintenanceRequest(maintenanceRequest).subscribe(
      (response: MaintenanceRequest) => {
        this.loading = false;
        this.toastr.success('Maintenance request submitted successfully!', 'Success');
        this.maintenanceForm.reset();
        this.selectedFiles = [];
      },
      (error: any) => {
        this.loading = false;
        this.toastr.error('Failed to submit maintenance request. Please try again.', 'Error');
        console.error(error);
      }
    );
  }
}

