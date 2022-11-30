import {DataContext} from "../App";
import {useContext} from "react";
import {useNavigate} from "react-router-dom";
import {Box, Button} from "@chakra-ui/react";
import {NavBar} from "./navBar";


export const Resort=()=> {

    const context = useContext(DataContext);
    const navigate = useNavigate()
    return (<Box>
            <NavBar/>
            <Box>
                {context.resortData.id}
                <Button className={"signButton"} onClick={() => navigate("/cart")}>CART</Button>

            </Box>
        </Box>

    )
}










