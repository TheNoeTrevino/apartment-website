
import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Router } from '@angular/router';

@Component({
  selector: 'app-tenant-dashboard',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './tenant-dashboard.component.html',
  styleUrls: ['./tenant-dashboard.component.scss']
})
export class TenantDashboardComponent {
  constructor(private router: Router) { }

  payRent() {
    this.router.navigate(['/pay-rent']);
  }

  makeMaintenanceRequest() {
    this.router.navigate(['/maintenance-request']);
  }
}
