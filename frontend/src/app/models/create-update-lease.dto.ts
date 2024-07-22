import { TenantDTO } from './tenant.dto';
import { ApartmentDTO } from './apartment.dto';

export interface CreateUpdateLeaseDTO {
  leaseStartDate: string; // ISO date string
  leaseEndDate: string; // ISO date string
  rentAmount: number;
  securityDepositAmount: number;
  tenant: TenantDTO;
  apartment: ApartmentDTO;
}

