import {
    Box, Button,
} from "@chakra-ui/react";

import {useNavigate} from "react-router-dom";
import Bin from "../files/Vector (1).png";
import React, {useContext, useEffect, useState} from "react";
import {DataContext} from "../App";
import {authorizedApi} from "../hooks/userAxios";
import UserContext from "../context/UserContext";
import {AxiosResponse} from "axios";
import {ResortData} from "../models/resorts";
import {BillData} from "../models/bill";
import {Item} from "../models/item";

export const Cart = () => {



    const navigate = useNavigate()
    const context = useContext(DataContext);
    const [bill, setBill] = useState(context.billData)
    const contextUser = useContext(UserContext)


    useEffect(() => {
        authorizedApi.post(`${process.env.REACT_APP_API_BASE_URL}/api/bill/${contextUser.currentUser?.displayName}`,
        ).then((res: AxiosResponse<BillData>) => {
            setBill(res.data)
            context.cartItemData.items.splice(0,1)

        })
    }, []);

    function confirmBill() {
        context.cartItemData.items.forEach((item) => {

            authorizedApi.post(`${process.env.REACT_APP_API_BASE_URL}/api/item/${bill.id}`,
                {
                    "id": item.id,
                    "type": item.type,
                    "mark": item.mark,
                    "cost": item.cost
                }).then(()=>navigate("/confirmation"));

        })}

    context.billDataModifier(bill)



    return (
        <Box>
            <Box display={"flex"} justifyContent={"center"} flexDirection={"column"} alignItems={"center"}>
                <p className={"summary"}>Summary:</p>

                <Box width='908px'>
                    <Box className={"summaryBar"} background={"white"} height='40px' display={"flex"}
                         flexDirection={"row"} marginTop='12px' marginBottom='12px'>
                        <Box width='230px' marginLeft='16px' paddingLeft='24px'>Equipment Type</Box>
                        <Box width='252px' paddingLeft='24px'>Brand</Box>
                        <Box width='100px' paddingLeft='24px'>Size</Box>
                        <Box width='160px' paddingLeft='24px'>Cost</Box>
                        <Box width='175px' paddingLeft='24px'>Amount</Box>


                    </Box>
                    {context.cartItemData.items.map((item) => {
                        return (<Box className={"summaryBar"} background={"white"} height='40px' display={"flex"}
                                     flexDirection={"row"} marginTop='12px' marginBottom='12px'
                                     key={item.id}>
                            <Box width='230px' marginLeft='16px' paddingLeft='24px'>{item.type}</Box>
                            <Box width='252px' paddingLeft='24px'>{item.mark}</Box>
                            <Box width='100px' paddingLeft='24px'>{item.size}</Box>
                            <Box width='160px' paddingLeft='24px'>{item.cost}</Box>
                            <Box width='100px' paddingLeft='24px'>1</Box>

                        </Box>)
                    })}
                </Box>
                <Box width='908px' display={"flex"} flexDirection={"row"} justifyContent={"space-between"}
                     marginTop='20px'>
                    <Box onClick={() => navigate('/resort')} display={"flex"} flexDirection={"row"} width='200px'>
                        <Box className={"backToShop"} marginLeft='14px' width='144px' height='40px'> Back to
                            Shop </Box></Box>
                    <button className={"buttonAdd"} onClick={() => confirmBill()}>Confirm
                        Order
                    </button>

                </Box>
            </Box>
        </Box>)
}