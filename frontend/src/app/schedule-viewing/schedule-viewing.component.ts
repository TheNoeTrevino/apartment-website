import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { MatDatepickerModule } from '@angular/material/datepicker';
import { MatNativeDateModule } from '@angular/material/core';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatSelectModule } from '@angular/material/select';
import { MatButtonModule } from '@angular/material/button';
import { ToastrService } from 'ngx-toastr';
import { MapComponent } from '../map/map.component';

@Component({
  selector: 'app-schedule-viewing',
  standalone: true,
  imports: [
    CommonModule,
    FormsModule,
    ReactiveFormsModule,
    MatDatepickerModule,
    MatNativeDateModule,
    MatFormFieldModule,
    MatInputModule,
    MatSelectModule,
    MatButtonModule,
    MapComponent,
    // MatTimepickerModule, // Uncomment this if using a time picker
  ],
  templateUrl: './schedule-viewing.component.html',
  styleUrl: './schedule-viewing.component.scss'
})
export class ScheduleViewingComponent {
  appointmentDate: Date | null = null;
  appointmentTime: string | null = null;
  location: string | null = null;
  phoneNumber: string = '';
  email: string = '';

  availableTimes: string[] = [
    '08:00 AM', '09:00 AM', '10:00 AM', '11:00 AM', '12:00 PM',
    '01:00 PM', '02:00 PM', '03:00 PM', '04:00 PM', '05:00 PM'
  ];

  locations: { id: string, name: string }[] = [
    { id: '1', name: 'Jackson South Apartments' },
    { id: '2', name: 'Santa Lucia Apartments' }
  ];

  constructor(private toastr: ToastrService) { }

  onSubmit() {
    if (this.appointmentDate && this.appointmentTime && this.location && this.phoneNumber && this.email) {
      console.log('Appointment set for:', this.appointmentDate, this.appointmentTime, this.location, this.phoneNumber, this.email);
      this.toastr.success('Appointment set successfully!', 'Success');
    } else {
      this.toastr.error('Please fill out all fields', 'Error');
    }
  }
}
