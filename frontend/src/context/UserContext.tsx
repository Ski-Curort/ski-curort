import { createContext, useState } from "react";
import { AppContextType } from "../models/AppContextType";
import { User } from "../models/user";

const defaultSettings: AppContextType = {
  currentUser: null,
  userModifier: (user: User | null) => {},
};

export const UserContext = createContext<AppContextType>(defaultSettings);

export const AppContextProvider = ({ children }: React.PropsWithChildren) => {
  const [currentUser, setCurrentUser] = useState<User | null>(null);

  const userModifier = (user: User | null) => {
    setCurrentUser(user);
  };

  return (
    <UserContext.Provider value={{ currentUser, userModifier }}>
      {children}
    </UserContext.Provider>
  );
};

export default UserContext;
