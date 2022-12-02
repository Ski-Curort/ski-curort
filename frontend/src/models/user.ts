import {RoleEnum} from "./roleEnum";

export interface User {
    displayName: string;
    username: string;
    email: string;
    imageUrl: string;
    roles: Role[];
}

export enum Role{
    USER = 'USER',
    ADMIN = 'ADMIN'
}

/**
 * @deprecated use User instead
 */
export interface UserData {
    userId: number;
    userName: string;
    userRole: RoleEnum
}
