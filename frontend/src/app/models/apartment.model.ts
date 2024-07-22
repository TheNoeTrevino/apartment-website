import { ApartmentComplex } from "./apartment-complex.model";

export interface Apartment {
  id: number;
  apartmentName: string;
  apartmentLocation: string;
  numOfRooms: number;
  squareFootage: number;
  dateBuilt: string; // Use ISO string format for dates
  currentTenantName?: string;
  currentTenantEmail?: string;
  currentTenantPhone?: string;
  apartmentComplex: ApartmentComplex;
}
