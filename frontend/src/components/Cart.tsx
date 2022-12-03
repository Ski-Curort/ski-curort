import {
    Box,
} from "@chakra-ui/react";

import {useNavigate} from "react-router-dom";
import Bin from "../files/Vector (1).png";
import {useContext, useEffect, useState} from "react";
import {DataContext} from "../App";
import {authorizedApi} from "../hooks/userAxios";
import UserContext from "../context/UserContext";
import {AxiosResponse} from "axios";
import {ResortData} from "../models/resorts";
import {BillData} from "../models/bill";

export const Cart  = () => {

    const equipments = [{
        itemId: 1,
        equipmentType: "Ski",
        brand: "gf",
        totalPrice: 11,
        amount: 0,
    }, {
        itemId: 2,
        equipmentType: "google",
        brand: "sdf",
        totalPrice: 22,
        amount: 0,
    }, {
        itemId: 3,
        equipmentType: "snowboard",
        brand: "gdf",
        totalPrice: 33,
        amount: 0,
    }, {
        itemId: 4,
        equipmentType: "sank",
        brand: "fdg",
        totalPrice: 44,
        amount: 0,
    }]

    const navigate = useNavigate()
    const context = useContext(DataContext);
    const [bill, setBill] = useState(context.billData)
    const [resort, setResort] = useState(context.billData.curort)
    const contextUser=useContext(UserContext)


    useEffect(() => {
        getApiData()
    },[]);
    useEffect(() => {
        setResort(context.resortData);
    }, []);
    const getApiData = async () => {
        const response = await authorizedApi.post(`${process.env.REACT_APP_API_BASE_URL}/api/bill/${contextUser.currentUser?.displayName}`,
        ).then((res:AxiosResponse<BillData>)=>{
            setBill(res.data)

        })

    };



    function confirmBill() {
        equipments.forEach((equipment) => {

             authorizedApi.post(`${process.env.REACT_APP_API_BASE_URL}/api/item/${bill.id}`,
                {
                "id": equipment.itemId,
                    "type": equipment.equipmentType,
                    "mark": equipment.brand,
                    "cost": equipment.totalPrice
            });

        })
    }

    function deleteItem(id: number) {
        const newEquipments = equipments.filter(equipment => equipment.itemId !== id)
        equipments.splice(0, equipments.length);
        return equipments.concat(newEquipments);
    }

    return (
        <Box>
            <Box display={"flex"} justifyContent={"center"} flexDirection={"column"} alignItems={"center"}>
                <p className={"summary"}>Summary:</p>

                <Box width='908px'>
                    <Box className={"summaryBar"} background={"white"} height='40px' display={"flex"}
                         flexDirection={"row"} marginTop='12px' marginBottom='12px'>
                        <Box width='210px' marginLeft='16px' paddingLeft='24px'>Equipment Type</Box>
                        <Box width='252px' paddingLeft='24px'>Brand</Box>
                        <Box width='130px' paddingLeft='24px'>Cost</Box>
                        <Box width='175px' paddingLeft='24px'>Amount</Box>
                        <Box width='96px'></Box>

                    </Box>
                    {equipments.map((equipment) => {
                        return (<Box className={"summaryBar"} background={"white"} height='40px' display={"flex"}
                                     flexDirection={"row"} marginTop='12px' marginBottom='12px' key={equipment.itemId}>
                            <Box width='210px' marginLeft='16px' paddingLeft='24px'>{equipment.equipmentType}</Box>
                            <Box width='252px' paddingLeft='24px'>{equipment.brand}</Box>
                            <Box width='130px' paddingLeft='24px'>{equipment.totalPrice}</Box>
                            <Box width='175px' paddingLeft='24px'>{equipment.amount}</Box>
                            <Box width='96px' display={"flex"} justifyContent={"center"}>
                                <img alt={"Bin"} src={Bin} onClick={() => deleteItem(equipment.itemId)}/>
                            </Box>
                        </Box>)
                    })}
                </Box>
                <Box width='908px' display={"flex"} flexDirection={"row"} justifyContent={"space-between"}
                     marginTop='20px'>
                    <Box onClick={() => navigate('/resort')} display={"flex"} flexDirection={"row"} width='200px'>
                        <Box className={"backToShop"} marginLeft='14px' width='144px' height='40px'> Back to Shop </Box></Box>
                    <button className={"buttonAdd"} onClick={() => [confirmBill(), navigate("/confirmation")]}>Confirm
                        Order
                    </button>
                </Box>
                <Box>{bill.id}</Box>
            </Box>

        </Box>)
}