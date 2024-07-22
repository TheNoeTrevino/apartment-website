
import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { HttpClientModule } from '@angular/common/http';
import { ApartmentService } from '../apartment.service';
import { Apartment } from '../models/apartment.model';

@Component({
  selector: 'app-apartment',
  standalone: true,
  imports: [CommonModule, HttpClientModule],
  templateUrl: './apartment.component.html',
  styleUrls: ['./apartment.component.scss']
})
export class ApartmentComponent implements OnInit {
  apartments: Apartment[] = [];

  constructor(private apartmentService: ApartmentService) { }

  ngOnInit() {
    this.apartmentService.getApartments().subscribe((data: Apartment[]) => {
      this.apartments = data;
    });
  }
}

