import { User } from "./User";

export type AppContextType = {
    currentUser: User | null;
    userModifier: (user: User | null) => void;
}