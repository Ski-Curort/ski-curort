import { User } from "./user";

export type AppContextType = {
    currentUser: User | null;
    userModifier: (user: User | null) => void;
}