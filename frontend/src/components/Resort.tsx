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
import { WeatherData } from "../models/weather";

export const Resort = () => {

    const context = useContext(DataContext);
    const[cartItems, setCartItems]=useState(context.cartItemData.items)
const [equipments, setEquipments]=useState([context.equipmentData])
const[weather, setWeather] = useState(context.weatherData);

 useEffect(() => {
    authorizedApi.get(`${process.env.REACT_APP_API_BASE_URL}/api/weather/${context.resortData.curortAdress}`
).then((res: AxiosResponse<WeatherData>) => {
        setWeather(res.data)

    })
},[context.resortData.curortAdress]);

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
    const navigate = useNavigate()
    return ( <Stack>

<div className="weather-background">
        <Table size = 'sm'>
          <Thead>
            <Tr>
              <Th scope = "col">City</Th>
              <Th scope = "col">Temperature</Th>
              <Th scope = "col">Pressure</Th>
              <Th scope = "col">Humidity</Th>
              <Th scope = "col">Wind velocity</Th>
              <Th scope = "col">Wind degree</Th>
            </Tr>
          </Thead>
          
          <Tbody>
              <Tr>
              <Td>{weather.cityName}</Td> 
              <Td>{weather.temp} C</Td> 
              <Td>{weather.pressure} hPa</Td> 
              <Td>{weather.humidity} %</Td> 
              <Td>{weather.windSpeed} m/s</Td> 
              <Td>{weather.windDeg} </Td> 
              </Tr>
          </Tbody>
        </Table>
      </div>

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
                <Button onClick={()=>navigate("/confirmation")}>Proceed to Order</Button>
            </Box>
        </Stack>
    )
}





