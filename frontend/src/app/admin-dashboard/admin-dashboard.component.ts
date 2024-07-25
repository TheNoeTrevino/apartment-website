import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ReactiveFormsModule } from '@angular/forms';
import { MatPaginatorModule, PageEvent } from '@angular/material/paginator';
import { ApartmentDTO } from '../models/apartment.dto';
import { ApartmentService } from '../apartment.service';
import { AuthService } from '../auth.service';
import { warn } from 'console';
import { Router } from '@angular/router';

@Component({
  selector: 'app-admin-dashboard',
  standalone: true,
  imports: [CommonModule, MatPaginatorModule, ReactiveFormsModule],
  templateUrl: './admin-dashboard.component.html',
  styleUrls: ['./admin-dashboard.component.scss']
})
export class AdminDashboardComponent implements OnInit {

  apartments: ApartmentDTO[] = [];
  initialLoading = true;
  page = 0;
  size = 10;
  totalElements = 0;

  constructor(private apartmentService: ApartmentService, private authService: AuthService, private router: Router) { }

  ngOnInit() {
    this.getApartments();
  }

  logout() {
    this.authService.signOut();
    this.router.navigateByUrl('/');
  }

  getApartments() {
    this.apartmentService.getApartments(this.page, this.size).subscribe(
      (response) => {
        this.apartments = response.content;
        this.totalElements = response.totalElements;
        this.initialLoading = false;
      },
      (error) => {
        console.error('Error fetching apartments', error);
        this.initialLoading = false;
      }
    );
  }

  onPageChange(event: PageEvent) {
    this.page = event.pageIndex;
    this.size = event.pageSize;
    this.getApartments();
  }
}
