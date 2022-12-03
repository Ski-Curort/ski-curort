import {DataContext} from "../App";
import {useContext} from "react";
import {useNavigate} from "react-router-dom";
import {Box, } from "@chakra-ui/react";
import React,{useEffect, useState} from 'react'
import {AddEquipment} from "../models/addEquipment";
import {authorizedApi} from "../hooks/userAxios";
import {AxiosResponse} from "axios";

import {EquipmentData} from "../models/equipment";
import Bin from "../files/Vector (1).png";
import Cart from "../files/Vector (4).png"
export const Resort = () => {

    const context = useContext(DataContext);
    const[cartItems, setCartItems]=useState(context.cartItemData.items)
const [equipments, setEquipments]=useState([context.equipmentData])

    useEffect(()=> {
        console.log("About to refresh equipments list")
        authorizedApi.get(`http://localhost:8080/api/equipment/all`).then((res:AxiosResponse<EquipmentData[]>) => {
            setEquipments(res.data)
            console.log("recive")
            //context.equipmentDataModifier(equipments)
            context.isChangedEquipmentModifier(false)
            console.log(res.data)

        });
    }, [context.isChangedEquipment]);

function addToCart(a:EquipmentData){


    const newCart=context.cartItemData
    newCart.items.push(a)
    context.cartItemModifier(newCart)


}
    function deleteEquipment(id: number) {
        authorizedApi({
            method: "DELETE",
            url: `${process.env.REACT_APP_API_BASE_URL}/api/equipment/delete/${id}`
        });
        context.isChangeModifier(true)
    }
    const navigate = useNavigate()
    return ( <Box>
            <Box display={"flex"} justifyContent={"center"} flexDirection={"column"} alignItems={"center"}>
                <p className={"summary"}>{context.resortData.curortName}</p>

                <Box width='908px'>
                    <Box className={"summaryBar"} background={"white"} height='40px' display={"flex"}
                         flexDirection={"row"} marginTop='12px' marginBottom='12px'>
                        <Box width='210px' marginLeft='16px' paddingLeft='24px'>Equipment Type</Box>
                        <Box width='252px' paddingLeft='24px'>Brand</Box>
                        <Box width='100px' paddingLeft='24px'>Sieze</Box>
                        <Box width='130px' paddingLeft='24px'>Cost</Box>
                        <Box width='175px' paddingLeft='24px'>Amount</Box>
                        <Box width='96px'></Box>

                    </Box>
                    {equipments.map((equipment) => {
                        return (<Box className={"summaryBar"} background={"white"} height='40px' display={"flex"}
                                     flexDirection={"row"} marginTop='12px' marginBottom='12px'
                                     key={equipment.id}>
                            <Box width='210px' marginLeft='16px' paddingLeft='24px'>{equipment.type}</Box>
                            <Box width='252px' paddingLeft='24px'>{equipment.mark}</Box>
                            <Box width='100px' paddingLeft='24px'>{equipment.size}</Box>
                            <Box width='130px' paddingLeft='24px'>{equipment.cost}</Box>
                            <Box width='175px' paddingLeft='24px'>1</Box>
                            <Box width='96px' display={"flex"} justifyContent={"center"}>
                                <img alt={"Bin"} src={Bin} onClick={() => deleteEquipment(equipment.id)}/>
                                <Box width={'30px'}></Box>
                                <img alt={"Cart"} src={Cart} onClick={() => addToCart(equipment)}/>
                            </Box>
                        </Box>)
                    })}
                </Box>
                <Box width='908px' display={"flex"} flexDirection={"row"} justifyContent={"space-between"}
                     marginTop='20px'>
                    <Box onClick={() => navigate('/resort')} display={"flex"} flexDirection={"row"} width='200px'>
                        <AddEquipment/></Box>
                    <button className={"buttonAdd"} onClick={()=>navigate("/cart")}>Proceed to Order
                    </button>

                </Box>
            </Box>

        </Box>


    )
}





