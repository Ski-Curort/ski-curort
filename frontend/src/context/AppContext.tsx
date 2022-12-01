import { createContext, useState } from "react";
import { AppContextType } from "../models/AppContextType";
import { User } from "../models/user";

const defaultSettings: AppContextType = {
  currentUser: null,
  userModifier: (user: User | null) => {},
};

export const AppContext = createContext<AppContextType>(defaultSettings);

export const AppContextProvider = ({ children }: React.PropsWithChildren) => {
  const [currentUser, setCurrentUser] = useState<User | null>(null);

  const userModifier = (user: User | null) => {
    setCurrentUser(user);
  };

  return (
    <AppContext.Provider value={{ currentUser, userModifier }}>
      {children}
    </AppContext.Provider>
  );
};

export default AppContext;
