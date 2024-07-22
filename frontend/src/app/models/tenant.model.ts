import { Apartment } from "./apartment.model";

export interface Tenant {
  id: number;
  firstName: string;
  lastName: string;
  dateOfBirth: string;
  email: string;
  phoneNumber: string;
  socialSecurityNumber: string;
  driversLicenseNumber: string;
  currentAddress: string;
  previousAddress: string;
  employerName: string;
  jobTitle?: string;
  annualIncome?: number;
  emergencyContactName: string;
  emergencyContactRelationship: string;
  emergencyContactPhone: string;
  referenceName: string;
  referenceRelationship: string;
  referencePhone: string;
  tenantStatus: TenantStatus;
  apartment: Apartment;
}

export enum TenantStatus {
  ACTIVE = 'ACTIVE',
  INACTIVE = 'INACTIVE',
  EVICTED = 'EVICTED',
  VACATED = 'VACATED'
}
