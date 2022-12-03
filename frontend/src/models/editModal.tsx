import {
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
import Edit from "../files/Vector.png";
import React, {useContext} from "react";
import {DataContext} from "../App";
import {authorizedApi} from "../hooks/userAxios";

interface EditMenuProps {
    resortId: number
}

export function EditMenu(props: EditMenuProps) {

    const {isOpen, onOpen, onClose} = useDisclosure()
    const context = useContext(DataContext);

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


    async function handleClic(id: number) {
        await authorizedApi({
            method: 'put',
            url: `${process.env.REACT_APP_API_BASE_URL}/api/curort/${id}`,
            data:
                {
                    "curortName": context.resortData.curortName,
                    "curortAdress": context.resortData.curortAdress,
                    "currortEmail": context.resortData.currortEmail,
                    "curortPhonenumber": context.resortData.curortPhonenumber
                }

        })
        context.isChangeModifier(true)
        onClose()


    }

    return (
        <>

            <img alt={"Edit"} src={Edit} onClick={onOpen
            }/>

            <Modal

                isOpen={isOpen}
                onClose={onClose}
            >
                <ModalOverlay/>
                <ModalContent>
                    <ModalHeader>Edit Resort</ModalHeader>
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
                        <Button className={"button"} colorScheme='blue' mr={3}
                                onClick={() => handleClic(props.resortId)}>
                            Edit
                        </Button>
                        <Button className={"button"} onClick={onClose}>Cancel</Button>
                    </ModalFooter>
                </ModalContent>
            </Modal>
        </>
    )
}

