import {Box} from "@chakra-ui/react";
import {NavBar} from "./navBar";
import {useContext} from "react";
import {DataContext} from "../App";


export const Confirmation = () => {
    const context = useContext(DataContext);
    return (
        <Box>
            {context.billData.itemList.map((item) => {
                return(
                <Box>{item.item.equipmentType}</Box>)})}

        </Box>)
}