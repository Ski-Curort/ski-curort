import {Box} from "@chakra-ui/react";
import {useContext} from "react";
import {DataContext} from "../App";
import {useNavigate} from "react-router-dom";
import UserContext from "../context/UserContext";


export const Confirmation = () => {
    const context = useContext(DataContext);
    const contextUser=useContext(UserContext)
    const navigate = useNavigate()
    function printBill(){
        fetch(`http://localhost:8080/api/pdf/${context.billData.id}`,
            {method: 'GET'})


    }
    return (
        <Box>

            <Box>
                <Box display={"flex"} justifyContent={"center"} flexDirection={"column"} alignItems={"center"}>
                    <p className={"summary"}>Confirmation:</p>
                    <p>Bill NO. {context.billData.id}</p>
                    <p>Date  {context.billData.creationData}</p>
                    <p>User name: {contextUser.currentUser?.displayName}</p>
                    <p>User e-mail: {contextUser.currentUser?.email}</p>

                    <Box width='908px'>
                        <Box className={"summaryBar"} background={"white"} height='40px' display={"flex"}
                             flexDirection={"row"} marginTop='12px' marginBottom='12px'>
                            <Box width='210px' marginLeft='16px' paddingLeft='24px'>Equipment Type</Box>
                            <Box width='252px' paddingLeft='24px'>Brand</Box>
                            <Box width='130px' paddingLeft='24px'>Cost</Box>
                            <Box width='175px' paddingLeft='24px'>Amount</Box>
                            <Box width='96px'></Box>

                        </Box>
                        {context.billData.itemList.map((item) => {
                            return (<Box className={"summaryBar"} background={"white"} height='40px' display={"flex"}
                                         flexDirection={"row"} marginTop='12px' marginBottom='12px' key={item.item.itemId}>
                                <Box width='210px' marginLeft='16px' paddingLeft='24px'>{item.item.equipmentType}</Box>
                                <Box width='252px' paddingLeft='24px'>{item.item.brand}</Box>
                                <Box width='130px' paddingLeft='24px'>{item.item.totalPrice}</Box>
                                <Box width='175px' paddingLeft='24px'>{item.item.amount}</Box>

                            </Box>)
                        })}
                    </Box>
                    <Box width='908px' display={"flex"} flexDirection={"row"} justifyContent={"space-between"}
                         marginTop='20px'>
                        <Box onClick={() => navigate('/cart')} display={"flex"} flexDirection={"row"} width='200px'>
                            <Box className={"backToShop"} marginLeft='14px' width='144px' height='40px'> Back to Shop </Box></Box>
                        <button className={"buttonAdd"} onClick={() => printBill()}>Print Order</button>
                    </Box>

                </Box>

            </Box>)

        </Box>)
}