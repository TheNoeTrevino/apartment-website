import { ApartmentComplexDTO } from "./apartment-complex.dto";

export interface ApartmentDTO {
  id: number;
  apartmentName: string;
  apartmentLocation: string;
  numOfRooms: number;
  squareFootage: number;
  dateBuilt: string;
  currentTenantName?: string;
  currentTenantEmail?: string;
  currentTenantPhone?: string;
  apartmentComplexId: number;
}

