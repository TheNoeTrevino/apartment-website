export interface Tenant {
    id: number;
    firstName: string;
    lastName: string;
    dateOfBirth: string; // ISO date string
    email: string;
    phoneNumber: string;
    socialSecurityNumber: string;
    driversLicenseNumber: string;
    currentAddress: string;
    previousAddress: string;
    employerName: string;
    jobTitle: string;
    annualIncome: number;
    emergencyContactName: string;
    emergencyContactRelationship: string;
    emergencyContactPhone: string;
    referenceName: string;
    referenceRelationship: string;
    referencePhone: string;
    tenantStatus: TenantStatus;
    apartmentId: number;
    leaseId: number;
    rentDue: number;
}

export enum TenantStatus {
    ACTIVE = "ACTIVE",
    INACTIVE = "INACTIVE"
}

