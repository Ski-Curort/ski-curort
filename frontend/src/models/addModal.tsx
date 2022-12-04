import {
    Box,
    Button,
    FormControl, FormLabel, Input,
    Modal,
    ModalBody,
    ModalCloseButton,
    ModalContent, ModalFooter,
    ModalHeader,
    ModalOverlay,
    useDisclosure
} from "@chakra-ui/react";
import { AxiosResponse } from "axios";
import {useContext} from "react";
import {DataContext} from "../App";
import { authorizedApi } from "../hooks/userAxios";
import {ResortData} from "./resorts";
import {Role} from "./user";
import {EditMenu} from "./editModal";
import Bin from "../files/Vector (1).png";
import useUserContext from "../hooks/useUserContext";

export function AddMenu() {
    const {isOpen, onOpen, onClose} = useDisclosure()
    const context = useContext(DataContext);
    const userContext = useUserContext();

    const onResortNameChanged = (
        event: React.ChangeEvent<HTMLInputElement>
    ) => {
        context.resortDataModifier({
            ...context.resortData,
            curortName: event.currentTarget.value,
        });
    };
    const onResortEmailChanged = (
        event: React.ChangeEvent<HTMLInputElement>
    ) => {
        context.resortDataModifier({
            ...context.resortData,
            currortEmail: event.currentTarget.value,
        });
    };
    const onResortAddressChanged = (
        event: React.ChangeEvent<HTMLInputElement>
    ) => {
        context.resortDataModifier({
            ...context.resortData,
            curortAdress: event.currentTarget.value,
        });
    };
    const onResortPhoneChanged = (
        event: React.ChangeEvent<HTMLInputElement>
    ) => {
        context.resortDataModifier({
            ...context.resortData,
            curortPhonenumber: event.currentTarget.valueAsNumber
        });
    };

    async function handleClic() {
        authorizedApi.post(`${process.env.REACT_APP_API_BASE_URL}/api/curort/`, {
            curortName: context.resortData.curortName,
            curortAdress: context.resortData.curortAdress,
            currortEmail: context.resortData.currortEmail,
            curortPhonenumber: context.resortData.curortPhonenumber
        })
            .then((res:AxiosResponse<ResortData>) => {
                context.isChangeModifier(true)
                console.log("Got saved resort data");
                console.log(res.data);
                console.log("Resort name: "+ res.data.curortName);
                onClose();
            });
    }
    return (
        <>

            {userContext.currentUser?.roles.includes(Role.ADMIN) && (
                <button className={"buttonAdd"} onClick={onOpen}>+ Add Resort</button>)}


            <Modal

                isOpen={isOpen}
                onClose={onClose}
            >
                <ModalOverlay/>
                <ModalContent>
                    <ModalHeader>Add your account</ModalHeader>
                    <ModalCloseButton/>
                    <ModalBody pb={6}>
                        <FormControl>
                            <FormLabel>Resort Name</FormLabel>
                            <Input placeholder='Resort name' value={context.resortData.curortName}
                                   onChange={onResortNameChanged}/>
                        </FormControl>

                        <FormControl mt={4}>
                            <FormLabel>Address</FormLabel>
                            <Input placeholder='Address' value={context.resortData.curortAdress}
                                   onChange={onResortAddressChanged}/>
                        </FormControl>
                        <FormControl mt={4}>
                            <FormLabel>Phone number</FormLabel>
                            <Input placeholder='Phone number' value={context.resortData.curortPhonenumber}
                                   type={"number"}
                                   onChange={onResortPhoneChanged}/>
                        </FormControl>
                        <FormControl mt={4}>
                            <FormLabel>E-mail</FormLabel>
                            <Input placeholder='E-mail'
                                   value={context.resortData.currortEmail}
                                   onChange={onResortEmailChanged}/>
                        </FormControl>
                    </ModalBody>

                    <ModalFooter>
                        <Button colorScheme='blue' mr={3} onClick={() => handleClic()}>
                            Add
                        </Button>
                        <Button onClick={onClose}>Cancel</Button>
                    </ModalFooter>
                </ModalContent>
            </Modal>
        </>
    )
}

