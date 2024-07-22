
import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { HttpClientModule } from '@angular/common/http';
import { AdminService } from '../admin.service';
import { Apartment } from '../models/apartment.model';

@Component({
  selector: 'app-admin-dashboard',
  standalone: true,
  imports: [CommonModule, HttpClientModule],
  templateUrl: './admin-dashboard.component.html',
  styleUrls: ['./admin-dashboard.component.scss']
})
export class AdminDashboardComponent implements OnInit {
  apartments: Apartment[] = [];
  initialLoading = true;

  constructor(private adminService: AdminService) { }

  ngOnInit() {
    this.getApartments();
  }

  getApartments() {
    // Hard-coded apartment data
    this.apartments = [
      {
        id: 1,
        apartmentName: 'Apartment A',
        apartmentLocation: 'Location A',
        numOfRooms: 2,
        squareFootage: 800,
        dateBuilt: '2020-01-01',
        currentTenantName: 'John Doe',
        currentTenantEmail: 'johndoe@example.com',
        currentTenantPhone: '+123456789',
        apartmentComplex: {
          id: 1,
          complexName: 'Complex A',
          complexLocation: 'Complex Location A',
          numOfBuildings: 3,
          numOfUnits: 10,
          dateBuilt: '2015-05-15',
          managerName: 'Manager A',
          managerEmail: 'managera@example.com',
          managerPhone: '+987654321',
          apartments: []
        }
      },
      {
        id: 2,
        apartmentName: 'Apartment B',
        apartmentLocation: 'Location B',
        numOfRooms: 3,
        squareFootage: 1200,
        dateBuilt: '2018-05-15',
        currentTenantName: 'Jane Doe',
        currentTenantEmail: 'janedoe@example.com',
        currentTenantPhone: '+987654321',
        apartmentComplex: {
          id: 1,
          complexName: 'Complex A',
          complexLocation: 'Complex Location A',
          numOfBuildings: 3,
          numOfUnits: 10,
          dateBuilt: '2015-05-15',
          managerName: 'Manager A',
          managerEmail: 'managera@example.com',
          managerPhone: '+987654321',
          apartments: []
        }
      },
      {
        id: 3,
        apartmentName: 'Apartment C',
        apartmentLocation: 'Location B',
        numOfRooms: 3,
        squareFootage: 1200,
        dateBuilt: '2018-05-15',
        currentTenantName: 'Jane Doe',
        currentTenantEmail: 'janedoe@example.com',
        currentTenantPhone: '+987654321',
        apartmentComplex: {
          id: 1,
          complexName: 'Complex A',
          complexLocation: 'Complex Location A',
          numOfBuildings: 5,
          numOfUnits: 10,
          dateBuilt: '2015-05-15',
          managerName: 'Manager A',
          managerEmail: 'managera@example.com',
          managerPhone: '+987654321',
          apartments: []
        }
      }
    ];
    this.initialLoading = false;
  }
}
