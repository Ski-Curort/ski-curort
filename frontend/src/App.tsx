import React, {createContext, useState} from "react";
import "./App.css";
import {BrowserRouter, Route, Routes} from "react-router-dom";
import {Wrapper} from "./components/Wrapper/Wrapper";
import {StartPage} from "./components/StartPage";
import {Cart} from "./components/Cart";
import {Resort} from "./components/Resort";
import {ChakraProvider} from "@chakra-ui/react";
import {ResortData} from "./models/resorts";
import {UserData} from "./models/user";
import {RoleEnum} from "./models/roleEnum";
import {Confirmation} from "./components/Confirmation";
import {BillData} from "./models/bill";
import {RedirectHandler} from "./components/RedirectHandler";
import {Profile} from "./components/Profile/Profile";
import {AppContextProvider} from "./context/UserContext";
import {AdminPanel} from "./components/AdminPanel";
import {ProtectedRoute} from "./components/ProtectedRoute";
import {CartData} from "./models/itemsCartData";
import {EquipmentData} from "./models/equipment";

interface DataContext {
    resortData: ResortData;
    resortDataModifier: (value: ResortData) => void;
    userData: UserData;
    userDataModifier: (value: UserData) => void;
    isChanged: boolean;
    isChangeModifier: (value: boolean) => void;
    billData: BillData;
    billDataModifier: (value: BillData) => void;
    cartItemData: CartData;
    cartItemModifier: (value: CartData) => void;
    equipmentData: EquipmentData;
    equipmentDataModifier: (value: EquipmentData) => void;
    isChangedEquipment: boolean;
    isChangedEquipmentModifier: (value: boolean) => void;
}

export const DataContext = createContext<DataContext>({
    resortData: {
        curortAdress: "",
        curortName: "",
        curortPhonenumber: 0,
        currortEmail: "",
        id: 0,
    },
    userDataModifier: (value: UserData) => {},

    userData: {userId: 1, userName: "", userRole: RoleEnum.admin},
    resortDataModifier: (value: ResortData) => {},
    isChanged: false,
    isChangeModifier: (value: boolean) => {},
    isChangedEquipment: false,
    isChangedEquipmentModifier: (value: boolean) => {},
    billData: {
        id: 0,
        totalCost: 0,
        creationData: "",
        curort: {
            curortAdress: "",
            curortName: "",
            curortPhonenumber: 0,
            currortEmail: "",
            id: 0,
        },
        itemList: [
            {
                item: {
                    itemId: 0,
                    equipmentType: "",
                    brand: "",
                    totalPrice: 0,
                    amount: 0,
                },
            },
        ],
    },
    billDataModifier: (value: BillData) => {
    },
    cartItemData: {
        items: [
            {
                id: 0,
                type: "",
                size: "",
                mark: "",
                cost: 0,
            }
        ]
    },
    cartItemModifier: (value: CartData) => {
    },
    equipmentData: {
        id: 0,
        type: "",
        size: "",
        mark: "",
        available: "",
        cost: 0,
    },
    equipmentDataModifier: (value: EquipmentData) => {
    },
});

function App() {
    const [equipmentData, setEquipmentData] = useState<EquipmentData>({
        id: 0,
        type: "",
        size: "",
        mark: "",
        available: "",
        cost: 0,
    });

    const equipmentDataModifier = (value: EquipmentData) => {
        setEquipmentData(value);
    };
    const [resortData, setResortData] = useState<ResortData>({
        curortAdress: "",
        curortName: "",
        curortPhonenumber: 0,
        currortEmail: "",
        id: 0,
    });
    const resortDataModifier = (value: ResortData) => {
        setResortData(value);
    };
    const [userData, setUserData] = useState<UserData>({
        userId: 1,
        userName: "Tomek",
        userRole: RoleEnum.admin,
    });
    const userDataModifier = (value: UserData) => {
        setUserData(value);
    };
    const [isChanged, setIsChange] = useState<boolean>(false);
    const isChangeModifier = (value: boolean) => {
        setIsChange(value);
    };
    const [isChangedEquipment, setIsChangedEquipment] = useState<boolean>(false);
    const isChangedEquipmentModifier = (value: boolean) => {
        setIsChangedEquipment(value);
    };
    const [billData, setBillData] = useState<BillData>({
        id: 0,
        totalCost: 0,
        creationData: "",
        curort: {
            curortAdress: "",
            curortName: "",
            curortPhonenumber: 0,
            currortEmail: "",
            id: 0,
        },
        itemList: [
            {
                item: {
                    itemId: 0,
                    equipmentType: "",
                    brand: "",
                    totalPrice: 0,
                    amount: 0,
                },
            },
        ],
    });
    const billDataModifier = (value: BillData) => setBillData(value);
    const [cartData, setCartData] = useState<CartData>(
        {

            items: [
                {
                    id: 0,
                    type: "",
                    size: "",
                    mark: "",
                    cost: 0,
                }
            ]

        })
    const cartDataModifier = (value: CartData) => setCartData(value);

    return (
        <AppContextProvider>
            <DataContext.Provider
                value={{
                    resortData: resortData,
                    resortDataModifier: resortDataModifier,
                    userData: userData,
                    userDataModifier: userDataModifier,
                    isChanged: isChanged,
                    isChangeModifier: isChangeModifier,
                    billData: billData,
                    billDataModifier: billDataModifier,
                    cartItemData: cartData,
                    cartItemModifier: cartDataModifier,
                    equipmentData: equipmentData,
                    equipmentDataModifier: equipmentDataModifier,

                    isChangedEquipment: isChangedEquipment,
                    isChangedEquipmentModifier: isChangedEquipmentModifier,
                }}
            >
                <ChakraProvider>
                    <BrowserRouter>
                        <Routes>
                            <Route path="/" element={<Wrapper/>}>
                                <Route path="/" element={<StartPage/>}></Route>
                                <Route path="/resort" element={<Resort/>}></Route>
                                <Route path="/cart" element={<Cart/>}></Route>
                                <Route path="/confirmation" element={<Confirmation/>}></Route>
                                <Route index element={<StartPage/>}></Route>
                                <Route path="/profile" element={<Profile/>}></Route>
                            </Route>
                            <Route
                                path="/adminpanel"
                                element={
                                    <ProtectedRoute>
                                        <AdminPanel/>
                                    </ProtectedRoute>
                                }
                            ></Route>
                            <Route
                                path="/oauth2/redirect"
                                element={<RedirectHandler/>}
                            ></Route>
                        </Routes>
                    </BrowserRouter>
                </ChakraProvider>
            </DataContext.Provider>
        </AppContextProvider>
    );
}

export default App;
