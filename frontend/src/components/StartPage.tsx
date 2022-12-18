import {Box, Stack,} from "@chakra-ui/react";
import "./StartPage.css";
import {useNavigate} from "react-router-dom";
import {DataContext} from "../App";
import React, {useContext, useEffect, useState} from "react";
import Bin from "../files/Vector (1).png"
import Bin1 from "../files/Vector (6).png"
import {EditMenu} from "../models/editModal";
import useUserContext from "../hooks/useUserContext";
import {Role} from "../models/user";
import {AddMenu} from "../models/addModal";
import {authorizedApi} from "../hooks/userAxios";
import {AxiosResponse} from "axios/index";
import {ResortData} from "../models/resorts";
import Edit1 from "../files/Vector (5).png";
import Edit from "../files/Vector.png";



export const StartPage = () => {
    const userContext = useUserContext();
    const context = useContext(DataContext);
    const [resorts, setResorts] = useState([context.resortData]);

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
    useEffect(() => {
        authorizedApi({method: "GET",
            url: `${process.env.REACT_APP_API_BASE_URL}/api/curort/`})
            .then((res:AxiosResponse<[ResortData]>) => {
                setResorts(res.data)
                context.isChangeModifier(false);

            });
    }, [context.isChanged]);


    function deleteResort(id: number) {
        authorizedApi({
            method: "DELETE",
            url: `${process.env.REACT_APP_API_BASE_URL}/api/curort/${id}`
        });
        context.isChangeModifier(true)
    }

    const navigate = useNavigate()
    return (<Box className={"background"} display="flex" flexDirection={"column"} alignContent={"center"}>
            <Box display={"flex"} flexDirection={"column"} alignItems={"center"}
                 justifyItems={"center"} justifyContent={"center"} height='100%'>
                <Stack maxWidth='557px' minWidth='557px' marginRight='51%' marginLeft='11%'>
                    <p className={"selectSki"}>Select Ski Resort</p>
                    {resorts.map((resort) => {
                        return (
                            <Box className={"boxSelect"}
                                 justifyContent={"space-between"} key={resort.id}>
                                <Box onClick={() => [navigate("../resort"),
                                    context.resortData = resort]}>{resort.curortName}
                                </Box>
                                {userContext.currentUser?.roles.includes(Role.USER) && (
                                    <Box display={"flex"} flexDirection={"row"} width='75px'
                                         justifyContent={"space-between"}>
                                        {userContext.currentUser?.roles.includes(Role.ADMIN)  && (
                                            <EditMenu ResortData={context.resortData}></EditMenu>)}
                                        {userContext.currentUser?.roles.includes(Role.ADMIN)  &&
                                            (<img alt={"Bin"} src={Bin} onMouseOver={buttonMouseOverHandler} onMouseLeave={buttonMouseLeavHandler} onClick={() => deleteResort(resort.id)}/>)}</Box>)}
                            </Box>)
                    })}
                    <Box display={"flex"}
                         flexDirection={"row-reverse"}>{userContext.currentUser?.roles.includes(Role.ADMIN)  && (<AddMenu></AddMenu>)}
                    </Box>
                </Stack>
            </Box>
        </Box>


    )
}