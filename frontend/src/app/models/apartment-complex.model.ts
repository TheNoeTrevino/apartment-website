import { Apartment } from "./apartment.model";

export interface ApartmentComplex {
  id: number;
  complexName: string;
  complexLocation: string;
  numOfBuildings: number;
  numOfUnits: number;
  dateBuilt: string;
  managerName: string;
  managerEmail: string;
  managerPhone: string;
  apartments: Apartment[];
}
