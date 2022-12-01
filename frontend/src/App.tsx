import React, {createContext, useState} from 'react';
import './App.css';
import logo from "./logo.svg";
import {BrowserRouter, Route, Routes} from "react-router-dom";
import { Wrapper } from "./components/Wrapper/Wrapper";
import {StartPage} from "./components/StartPage";
import {Cart} from "./components/Cart";
import {Resort} from "./components/Resort";
import {ChakraProvider, ListItem} from "@chakra-ui/react";
import {ResortData} from "./models/resorts"
import {UserData} from "./models/user";
import {RoleEnum} from "./models/roleEnum";
import {Confirmation} from "./components/Confirmation";
import {BillData} from "./models/bill";
import { LogInPage } from "./LoginPage/LogInPage";
import { RedirectHandler } from "./components/RedirectHandler";
import { Profile } from "./components/Profile/Profile";
import { AppContextProvider } from "./context/AppContext";
import { AdminPanel } from "./components/AdminPanel";
import { ProtectedRoute } from "./components/ProtectedRoute";

interface DataContext {
    resortData: ResortData;
    resortDataModifier: (value: ResortData) => void;
    userData: UserData;
    userDataModifier: (value: UserData) => void;
    isChanged:boolean
    isChangeModifier: (value: boolean)=>void;
    billData: BillData;
    billDataModifier: (value: BillData) =>void;
}

export const DataContext = createContext<DataContext>({
    resortData: {
        curortAdress: "", curortName: "", curortPhonenumber: 0, currortEmail: "", id: 0,
    },
    userDataModifier: (value: UserData) => {
    },

    userData: {userId: 1, userName: "", userRole: RoleEnum.admin},
    resortDataModifier: (value: ResortData) => {
    },
    isChanged: false,
    isChangeModifier:(value: boolean)=>{},
    billData:{

        id: 0,
        totalCost: 0,
        creationData: "",
        curort: {
            curortAdress: "", curortName: "", curortPhonenumber: 0, currortEmail: "", id: 0,
        },
        itemList: [{item:{
            itemId:0,
            equipmentType:"",
            brand:"",
            totalPrice:0,
            amount:0}}]

    },
    billDataModifier:(value: BillData)=> {}


});

function App() {
    const [resortData, setResortData] = useState<ResortData>({

        curortAdress: "",
        curortName: "",
        curortPhonenumber: 0,
        currortEmail: "",
        id: 0,

    },);
    const resortDataModifier = (value: ResortData) => {
        setResortData(value);
    };
    const [userData, setUserData] = useState<UserData>({userId: 1, userName: "Tomek", userRole: RoleEnum.admin},);
    const userDataModifier = (value: UserData) => {
        setUserData(value)
    }
    const [isChanged, setIsChange] = useState<boolean>(false);
    const isChangeModifier=(value:boolean)=>{setIsChange(value)}
const [billData, setBillData]=useState<BillData>({

    id: 0,
    totalCost: 0,
    creationData: "",
    curort: {
        curortAdress: "", curortName: "", curortPhonenumber: 0, currortEmail: "", id: 0,
    },
    itemList: [{item:{
            itemId:0,
            equipmentType:"",
            brand:"",
            totalPrice:0,
            amount:0}}]

});
    const billDataModifier=(value: BillData)=>(setBillData(value))

    return (
        <AppContextProvider>
          <DataContext.Provider value={{
              resortData: resortData,
              resortDataModifier: resortDataModifier,
              userData: userData,
              userDataModifier: userDataModifier,
              isChanged:isChanged,
              isChangeModifier:isChangeModifier,
              billData: billData,
              billDataModifier: billDataModifier
          }}>
              <ChakraProvider>
                  <BrowserRouter>
                      <Routes>
                        <Route path="/" element={<Wrapper />}>
                          <Route path="/" element={<StartPage/>}></Route>
                          <Route path="/resort" element={<Resort/>}></Route>
                          <Route path="/cart" element={<Cart/>}></Route>
                          <Route path="/confirmation" element={<Confirmation/>}></Route>
                          <Route index element={<StartPage />}></Route>
                          <Route path="/login" element={<LogInPage />}></Route>
                          <Route path="/profile" element={<Profile />}></Route>
                        </Route>
                        <Route path="/adminpanel" element={<ProtectedRoute><AdminPanel /></ProtectedRoute>}></Route>
                        <Route path="/oauth2/redirect" element={<RedirectHandler />}></Route>
                      </Routes>
                  </BrowserRouter>
              </ChakraProvider>
          </DataContext.Provider>
        </AppContextProvider>
    );
}

export default App;
