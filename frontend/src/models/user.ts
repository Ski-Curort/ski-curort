import {RoleEnum} from "./roleEnum";

export interface User {
    displayName: string;
    username: string;
    email: string;
    imageUrl: string;
}

export interface UserData {
    userId: number;
    userName: string;
    userRole:RoleEnum
}
