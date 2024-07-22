import { ApartmentDTO } from './apartment.dto';

export interface CreateUpdateTenantDTO {
  firstName: string;
  lastName: string;
  dateOfBirth: string; // ISO date string
  email: string;
  phoneNumber: string;
  socialSecurityNumber: string;
  driversLicenseNumber: string;
  currentAddress: string;
  previousAddress?: string;
  employerName: string;
  jobTitle?: string;
  annualIncome: number;
  emergencyContactName: string;
  emergencyContactRelationship: string;
  emergencyContactPhone: string;
  referenceName: string;
  referenceRelationship: string;
  referencePhone: string;
  apartment: ApartmentDTO;
}

