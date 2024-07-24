export interface User {
    id: number;
    firstName: string;
    lastName: string;
    userName: string;
    password: string;
    authority: Authority;
}

export enum Authority {
    ADMIN = "ADMIN",
    TENANT = "TENANT"
}

