export interface MaintenanceRequest {
  id?: number;
  tenantId: number;
  description: string;
  requestDate: Date;
  status: string;
  priority: string;
}

