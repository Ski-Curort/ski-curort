import {Box, Button} from "@chakra-ui/react";
import {useContext} from "react";
import {DataContext} from "../App";
import {useNavigate} from "react-router-dom";
import UserContext from "../context/UserContext";
import {authorizedApi} from "../hooks/userAxios";



export const Confirmation = () => {




    const context = useContext(DataContext);
    const contextUser=useContext(UserContext)
    const navigate = useNavigate()

    function printBill(){authorizedApi.
    get(`${process.env.REACT_APP_API_BASE_URL}/api/pdf/${context.billData.id}`)
    }

    const now = new Date().toDateString();

    const totalPrice=function () {
        return(context.cartItemData.items.reduce((sum, current) => sum + current.cost, 0))

    }

    return (
        <Box>

            <Box>
                <Box display={"flex"} justifyContent={"center"} flexDirection={"column"} alignItems={"center"}>
                    <Box> <p className={"summary"}>Confirmation:</p>
                        <Box>
                            <Box>
                                <p>Bill NO. {context.billData.id}</p>
                                <p> Date: {now} </p>
                                <p>Resort Name: {context.resortData.curortName}</p>
                            </Box>
                            <Box>
                                <p>User name: {contextUser.currentUser?.displayName}</p>
                                <p>User e-mail: {contextUser.currentUser?.email}</p>
                            </Box>
                        </Box>
                    </Box>
                    <Box></Box>
                    <Box width='908px'>
                        <Box className={"summaryBar"} background={"white"} height='40px' display={"flex"}
                             flexDirection={"row"} marginTop='12px' marginBottom='12px'>
                            <Box width='280px' marginLeft='16px' paddingLeft='24px'>Equipment Type</Box>
                            <Box width='290px' paddingLeft='24px'>Brand</Box>
                            <Box width='150px' paddingLeft='24px'>Cost</Box>
                            <Box width='100px' paddingLeft='24px'>Amount</Box>


                        </Box>
                        {context.cartItemData.items.map((item) => {
                            return (<Box className={"summaryBar"} background={"white"} height='40px' display={"flex"}
                                         flexDirection={"row"} marginTop='12px' marginBottom='12px' key={item.id}>
                                <Box width='280px' marginLeft='16px' paddingLeft='24px'>{item.type}</Box>
                                <Box width='290px' paddingLeft='24px'>{item.mark}</Box>
                                <Box width='150px' paddingLeft='24px'>{item.cost}</Box>
                                <Box width='100px' paddingLeft='24px'>1</Box>    </Box>)
                        })}
                    </Box><Box width='908px' display={"flex"} flexDirection={"column"} alignItems={"center"} justifyContent={"center"}
                               marginTop='20px'><Box className={"summaryBar"} fontSize={'24px'} marginTop={'25px'}>
                    TOTAL COST {totalPrice()}
                </Box></Box>
                    <Box width='908px' display={"flex"} flexDirection={"row"} justifyContent={"space-between"}
                         marginTop='20px'>
                        <Box onClick={() => navigate('/cart')} display={"flex"} flexDirection={"row"} width='200px'>
                            <Box className={"backToShop"} marginLeft='14px' width='144px' height='40px'> Back to Shop </Box></Box>
                        <button className={"buttonAdd"} onClick={() =>navigate("/")}>Change Resort</button>

                    </Box>

                </Box>

            </Box>)

        </Box>)
}