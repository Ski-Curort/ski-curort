import {DataContext} from "../App";
import {useContext} from "react";
import {useNavigate} from "react-router-dom";
import {Box, Button} from "@chakra-ui/react";



export const Resort = () => {

    const context = useContext(DataContext);
    const navigate = useNavigate()
    return (<Box>
            <Box>
                {context.resortData.id}
                <Button className={"signButton"} onClick={() => navigate("/cart")}>CART</Button>

            </Box>
        </Box>

    )
}










