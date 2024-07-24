import { Authority } from "./user.model";

export interface CreateUpdateUserDTO {
    firstName: string;
    lastName: string;
    userName: string;
    password: string;
    authority: Authority;
}
