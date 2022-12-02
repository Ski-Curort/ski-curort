import {Box, Stack,} from "@chakra-ui/react";
import "./StartPage.css";
import {useNavigate} from "react-router-dom";
import {DataContext} from "../App";
import {useContext, useEffect, useState} from "react";
import Bin from "../files/Vector (1).png"
import {EditMenu} from "../models/editModal";
import useUserContext from "../hooks/useUserContext";
import {Role} from "../models/user";
import {AddMenu} from "../models/addModal";
import {authorizedApi} from "../hooks/userAxios";



export const StartPage = () => {
    const userContext = useUserContext();
    const context = useContext(DataContext);
    const [resorts, setResorts] = useState([context.resortData]);


    useEffect(() => {
        getApiData()
    }, [context.isChanged]);

    const getApiData = async () => {
        const response = await fetch(
            "http://localhost:8080/api/curort/"
        ).then((response) => response.json());

        setResorts(response);
        context.isChangeModifier(false)
    };

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
                                        <EditMenu resortId={resort.id}></EditMenu>
                                        <img alt={"Bin"} src={Bin} onClick={() => deleteResort(resort.id)}/></Box>)}
                            </Box>)
                    })}
                    <Box display={"flex"}
                         flexDirection={"row-reverse"}>{userContext.currentUser?.roles.includes(Role.USER)  && (<AddMenu></AddMenu>)}
                    </Box>
                </Stack>
            </Box>
        </Box>


    )
}