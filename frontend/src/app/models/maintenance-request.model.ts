export interface MaintenanceRequest {
    id: number;
    description: string;
    requestDate: string; // ISO date string
    tenantId: number;
    type: string;
}
