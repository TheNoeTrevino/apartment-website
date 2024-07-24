export interface Lease {
    id: number;
    leaseStartDate: string;
    leaseEndDate: string;
    rentAmount: number;
    securityDepositAmount: number;
    tenantId: number;
    apartmentId: number;
}
