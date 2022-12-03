import {DataContext} from "../App";
import {useContext} from "react";
import {useNavigate} from "react-router-dom";
import {Box, Button,Stack,Table, Thead,
    Tbody,
    Tr,
    Th,
    Td,} from "@chakra-ui/react";
import React,{useEffect, useState} from 'react'
import {AddEquipment} from "../models/addEquipment";
import {authorizedApi} from "../hooks/userAxios";
import {AxiosResponse} from "axios";

import {EquipmentData} from "../models/equipment";

export const Resort = () => {

    const context = useContext(DataContext);
    const[cartItems, setCartItems]=useState(context.cartItemData.items)
const [equipments, setEquipments]=useState([context.equipmentData])

    useEffect(()=> {
        authorizedApi.get(`http://localhost:8080/api/equipment/all`).then((res:AxiosResponse<EquipmentData[]>) => {
            setEquipments(res.data)
            console.log("recive")
            context.equipmentDataModifier(equipments)
            context.isChangedEquipmentModifier(false)
            console.log(res.data)

        });
    }, [context.isChangedEquipment]);

function addToCart(a:EquipmentData){


    const newCart=context.cartItemData
    newCart.items.push(a)
    context.cartItemModifier(newCart)


}

    return ( <Stack>





            <Box display = {"flex"} alignContent = {"center"} justifyContent={"center"}>
                <Table size = 'lg' >
                    <Thead>
                        <Tr>
                            <Th scope = "col">Type</Th>
                            <Th scope = "col">Size</Th>
                            <Th scope = "col">Brand</Th>
                            <Th scope = "col">cost</Th>
                        </Tr>
                    </Thead>
                    <Tbody>
                {equipments.map((equipmentData) => {
                    return (<Box key = {context.resortData.id}>

                                <Tr>
                                    <Td>{equipmentData.type}</Td>
                                    <Td>{equipmentData.size} </Td>
                                    <Td>{equipmentData.mark}</Td>
                                    <Td>{equipmentData.cost} $</Td>
                                    <Td>
                                        <Button colorScheme='blue' mr={3} onClick={()=>addToCart(equipmentData)} >Add to card</Button>


                                    </Td>
                                </Tr>

                    </Box>)})}
                    </Tbody>
                </Table>
                <AddEquipment></AddEquipment>
            </Box>
        </Stack>
    )
}





