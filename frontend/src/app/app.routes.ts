import { Routes } from '@angular/router';
import { ServicesComponent } from './services/services.component';
import { HomeComponent } from './home/home.component';
import { SubmitApplicationComponent } from './submit-application/submit-application.component';
import { ContactUsPageComponent } from './contact-us-page/contact-us-page.component';
import { ScheduleViewingComponent } from './schedule-viewing/schedule-viewing.component';
import { LoginComponent } from './login/login.component';
import { MaintenanceRequestComponent } from './maintenance-request/maintenance-request.component';
import { AdminDashboardComponent } from './admin-dashboard/admin-dashboard.component';
import { TenantDashboardComponent } from './tenant-dashboard/tenant-dashboard.component';
import { PayRentComponent } from './pay-rent/pay-rent.component';
import { AuthService } from './auth.service';
import { inject } from '@angular/core';

export const routes: Routes = [
  { path: '', component: HomeComponent },
  { path: 'contact-us', component: ContactUsPageComponent },
  { path: 'services', component: ServicesComponent },
  { path: 'submit-application', component: SubmitApplicationComponent },
  { path: 'schedule-viewing', component: ScheduleViewingComponent },
  { path: 'login', component: LoginComponent },
  { path: 'maintenance-request', component: MaintenanceRequestComponent },
  { path: 'admin-dashboard', component: AdminDashboardComponent},
  { path: 'tenant-dashboard', component: TenantDashboardComponent },
  { path: 'pay-rent', component: PayRentComponent },
];
