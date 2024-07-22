export interface CreateUpdateApartmentComplexDTO {
  complexName: string;
  complexLocation: string;
  numOfBuildings: number;
  numOfUnits: number;
  dateBuilt: string; // ISO date string
  managerName: string;
  managerEmail: string;
  managerPhone: string;
}

