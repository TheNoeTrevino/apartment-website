export interface CreateUpdateApartmentDTO {
  complexId: number;
  apartmentName: string;
  apartmentLocation: string;
  numOfRooms: number;
  squareFootage: number;
  dateBuilt: string; // ISO date string
  currentTenantName?: string;
  currentTenantEmail?: string;
  currentTenantPhone?: string;
}

