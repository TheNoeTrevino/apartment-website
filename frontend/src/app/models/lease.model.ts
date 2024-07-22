import { Apartment } from "./apartment.model";
import { Tenant } from "./tenant.model";

export interface Lease {
  id: number;
  leaseStartDate: string;
  leaseEndDate: string;
  rentAmount: number;
  securityDepositAmount: number;
  tenant: Tenant;
  apartment: Apartment;
}

