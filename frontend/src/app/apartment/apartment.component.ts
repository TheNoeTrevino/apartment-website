import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { HttpClientModule } from '@angular/common/http';
import { ApartmentService } from '../apartment.service';
import { ApartmentDTO } from '../models/apartment.dto';
import { MatPaginatorModule } from '@angular/material/paginator';

@Component({
  selector: 'app-apartment',
  standalone: true,
  imports: [CommonModule, HttpClientModule, MatPaginatorModule],
  templateUrl: './apartment.component.html',
  styleUrls: ['./apartment.component.scss']
})
export class ApartmentComponent implements OnInit {

  apartments: ApartmentDTO[] = [];
  initialLoading = true;
  page = 0;
  size = 10;
  totalElements = 0;

  constructor(private apartmentService: ApartmentService) { }

  ngOnInit() {
    this.getApartments();
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

  getApartmentById(id: number) {
    this.apartmentService.getApartmentById(id).subscribe(
      (apartment) => {
        console.log(apartment);
      },
      (error) => {
        console.error('Error fetching apartment', error);
      }
    );
  }

  createApartment(apartment: ApartmentDTO) {
    this.apartmentService.createApartment(apartment).subscribe(
      (newApartment) => {
        this.apartments.push(newApartment);
      },
      (error) => {
        console.error('Error creating apartment', error);
      }
    );
  }

  updateApartment(id: number, apartment: ApartmentDTO) {
    this.apartmentService.updateApartment(id, apartment).subscribe(
      (updatedApartment) => {
        const index = this.apartments.findIndex(a => a.id === id);
        if (index !== -1) {
          this.apartments[index] = updatedApartment;
        }
      },
      (error) => {
        console.error('Error updating apartment', error);
      }
    );
  }

  deleteApartment(id: number) {
    this.apartmentService.deleteApartment(id).subscribe(
      () => {
        this.apartments = this.apartments.filter(a => a.id !== id);
      },
      (error) => {
        console.error('Error deleting apartment', error);
      }
    );
  }

  onPageChange(event: any) {
    this.page = event.pageIndex;
    this.size = event.pageSize;
    this.getApartments();
  }
}
