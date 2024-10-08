export interface ApartmentComplex {
    id: number;
    complexName: string;
    complexLocation: string;
    numOfBuildings: number;
    numOfUnits: number;
    dateBuilt: string; // ISO date string
    managerName: string;
    managerEmail: string;
    managerPhone: string;
    apartmentIds: number[]; // List of apartment IDs
}

