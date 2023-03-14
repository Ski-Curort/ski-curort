import {DataContext} from "../App";
import {useContext} from "react";
import {useNavigate} from "react-router-dom";
import {
    Box,
    Button,
    Table,
    TableCaption,
    TableContainer,
    Tbody,
    Td,
    Tfoot,
    Th,
    Thead,
    Tr,
    useToast,
} from "@chakra-ui/react";
import React, {useEffect, useState} from 'react'
import {AddEquipment} from "../models/addEquipment";
import {authorizedApi} from "../hooks/userAxios";
import {AxiosResponse} from "axios";
import {EquipmentData} from "../models/equipment";
import Bin from "../files/Vector (1).png";
import Cart from "../files/Vector (4).png"
import {WeatherData} from "../models/weather";
import {Role} from "../models/user";
import useUserContext from "../hooks/useUserContext";
import Bin1 from "../files/Vector (6).png";


export const Resort = () => {
    const userContext = useUserContext();
    const context = useContext(DataContext);
    const [cartItems, setCartItems] = useState(context.cartItemData.items)
    const [equipments, setEquipments] = useState([context.equipmentData])
    const [weather, setWeather] = useState(context.weatherData);
    const newCart = context.cartItemData
    newCart.items.splice(0, 1)
    const toast = useToast();

    const buttonMouseOverHandler = (
        event: React.MouseEvent<HTMLImageElement>
    ) => {
        const btn: HTMLImageElement = event.currentTarget;
        btn.src = Bin1
    };
    const buttonMouseLeavHandler = (
        event: React.MouseEvent<HTMLImageElement>
    ) => {
        const btn: HTMLImageElement = event.currentTarget;
        btn.src = Bin
    };
    const cartMouseOverHandler = (
        event: React.MouseEvent<HTMLImageElement>
    ) => {
        const btn: HTMLImageElement = event.currentTarget;
        btn.src = Bin1
    };
    const cartMouseLeavHandler = (
        event: React.MouseEvent<HTMLImageElement>
    ) => {
        const btn: HTMLImageElement = event.currentTarget;
        btn.src = Bin
    };

    useEffect(() => {
        authorizedApi.get(`${process.env.REACT_APP_API_BASE_URL}/api/weather/${context.resortData.curortCity}`
        ).then((res: AxiosResponse<WeatherData>) => {
            setWeather(res.data)

        })
    }, [context.resortData.curortAdress]);

    useEffect(() => {
        console.log("About to refresh equipments list")
        authorizedApi.get(`http://localhost:8080/api/equipment/all`).then((res: AxiosResponse<EquipmentData[]>) => {
            setEquipments(res.data)
            console.log("recive")
            //context.equipmentDataModifier(equipments)
            context.isChangedEquipmentModifier(false)
            console.log(res.data)
        });
    }, [context.isChangedEquipment]);

    const handleClick = (a: EquipmentData) => {


        newCart.items.push(a)
        context.cartItemModifier(newCart)
        toast({
            position: 'top',
            duration: 700,
            render: () => (
                <Box color='white' p={3} bg='#4079A0' borderRadius='12px' textAlign={"center"}>
                    Just added items to cart
                </Box>
            ),
        });
    }

    function deleteEquipment(id: number) {
        authorizedApi({
            method: "DELETE",
            url: `${process.env.REACT_APP_API_BASE_URL}/api/equipment/delete/${id}`
        });
        context.isChangedEquipmentModifier(true)
    }

    const navigate = useNavigate()


    return (
        <Box backgroundColor={"#F8F8F8"} height={"100%"} display={"flex"} flexDirection={"column"} alignItems={"center"}
             boxShadow='4px'>
            <Box className={"weather"} width='1140px' marginTop='35px'>
                <Box className={"resort"} color={"white"} marginTop='10px'
                     marginLeft='10px'>{context.resortData.curortName}</Box>
                <Box>
                    <Table variant={"striped"} size='sm' >


                        <Tr >
                            <Td>Temperature</Td>
                            <Td>Pressure</Td>
                            <Td>Humidity</Td>
                            <Td>Wind velocity</Td>
                            <Td>Wind degree</Td>
                        </Tr>
                        <Tr >

                            <Td>{weather.temp} C</Td>
                            <Td>{weather.pressure} hPa</Td>
                            <Td>{weather.humidity} %</Td>
                            <Td>{weather.windSpeed} m/s</Td>
                            <Td>{weather.windDeg} </Td>
                        </Tr>

                    </Table>

                </Box>
            </Box>
            <Box className={"resort"} fontWeight={"bold"} height='45px' width='1140px' marginTop='24px'
                 marginBottom='12px'>
                <p>Select Equipment To Rent</p>
            </Box>
            <Box border='1px' borderRadius='12px' borderColor='#E2E8F0'
                 width='1140px' backgroundColor='white' display={"flex"} justifyContent={"center"}
                 flexDirection={"column"} alignItems={"center"}>
                <Box display={"flex"} justifyContent={"center"} flexDirection={"column"} alignItems={"center"}>


                    <Box width='1140px' marginRight={"25px"}>
                        <Table width={"100%"} marginLeft={"25px"}  >

                            <Thead fontWeight={"bold"}>
                                <Tr  >
                                    <Th>Equipment Type</Th>
                                    <Th>Brand</Th>
                                    <Th>Size</Th>
                                    <Th isNumeric>Cost</Th>
                                    <Th isNumeric>Amount</Th>
                                    <Th width='165px'></Th>
                                    <Box width='15px'/>
                                </Tr>
                            </Thead>
                            <Tbody>{equipments.map((equipment) => {
                                return (
                                    <Tr>
                                        <Th>{equipment.type}</Th>
                                        <Th>{equipment.mark}</Th>
                                        <Th>{equipment.size}</Th>
                                        <Th isNumeric>{equipment.cost}</Th>
                                        <Th isNumeric>1</Th>
                                        <Th  display={"flex"} justifyContent={"center"}>
                                            {userContext.currentUser?.roles.includes(Role.ADMIN) &&
                                                (<img alt={"Bin"} src={Bin} onMouseOver={buttonMouseOverHandler}
                                                      onMouseLeave={buttonMouseLeavHandler}
                                                      onClick={() => deleteEquipment(equipment.id)}/>)}
                                            <Box width={'30px'}></Box>
                                            <img alt={"Cart"} src={Cart} onClick={() => handleClick(equipment)}/>

                                        </Th>

                                    </Tr>
                                )
                            })}
                            </Tbody>

                        </Table>


                    </Box>


                </Box>

            </Box>
            <Box width='1140px' display={"flex"} flexDirection={"row"} justifyContent={"space-between"}
                 marginTop='20px'>
                <Box onClick={() => navigate('/resort')} display={"flex"} flexDirection={"row"} width='200px'>
                    {userContext.currentUser?.roles.includes(Role.ADMIN) && (<AddEquipment/>)}</Box>
                <button className={"buttonAdd"} onClick={() => navigate("/cart")}>Proceed
                </button>

            </Box>
        </Box>

    )
}