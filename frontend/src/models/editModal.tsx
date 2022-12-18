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
import Edit1 from "../files/Vector (5).png";
import React, {CSSProperties, ImgHTMLAttributes, useContext} from "react";
import {DataContext} from "../App";
import {authorizedApi} from "../hooks/userAxios";
import {ResortData} from "./resorts";

interface EditMenuProps {
    ResortData:ResortData
}

export function EditMenu(props: EditMenuProps) {

    const {isOpen, onOpen, onClose} = useDisclosure()
    const context = useContext(DataContext);
    const buttonMouseOverHandler = (
        event: React.MouseEvent<HTMLImageElement>
    ) => {
        const btn: HTMLImageElement = event.currentTarget;
        btn.src = Edit1
    };
    const buttonMouseLeavHandler = (
        event: React.MouseEvent<HTMLImageElement>
    ) => {
        const btn: HTMLImageElement = event.currentTarget;
        btn.src = Edit
    };
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

            <img alt={"Edit"} src={Edit} onMouseOver={buttonMouseOverHandler} onMouseLeave={buttonMouseLeavHandler} onClick={onOpen
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
                            <Input placeholder={props.ResortData.curortName} value={context.resortData.curortName}
                                   onChange={onResortNameChanged}/>
                        </FormControl>

                        <FormControl mt={4}>
                            <FormLabel>Address</FormLabel>
                            <Input placeholder={props.ResortData.curortAdress} value={context.resortData.curortAdress}
                                   onChange={onResortAddressChanged}/>
                        </FormControl>
                        <FormControl mt={4}>
                            <FormLabel>Phone number</FormLabel>
                            <Input placeholder={props.ResortData.curortPhonenumber.toString()} value={context.resortData.curortPhonenumber}
                                   onChange={onResortPhoneChanged}/>
                        </FormControl>
                        <FormControl mt={4}>
                            <FormLabel>E-mail</FormLabel>
                            <Input placeholder={props.ResortData.currortEmail}
                                   value={context.resortData.currortEmail}
                                   onChange={onResortEmailChanged}/>
                        </FormControl>
                    </ModalBody>

                    <ModalFooter>
                        <Button className={"button"} colorScheme='blue' mr={3}
                                onClick={() => handleClic(props.ResortData.id)}>
                            Edit
                        </Button>
                        <Button className={"button"} onClick={onClose}>Cancel</Button>
                    </ModalFooter>
                </ModalContent>
            </Modal>
        </>
    )
}

