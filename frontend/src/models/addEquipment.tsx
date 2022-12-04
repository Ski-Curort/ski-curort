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
import {useContext} from "react";
import {DataContext} from "../App";
import { authorizedApi } from "../hooks/userAxios";
import { AxiosResponse } from "axios";
import { EquipmentData } from "../models/equipment";
import {Simulate} from "react-dom/test-utils";
import contextMenu = Simulate.contextMenu;
import useUserContext from "../hooks/useUserContext";
import {Role} from "./user";
export function AddEquipment() {
    const userContext = useUserContext();
    const {isOpen, onOpen, onClose} = useDisclosure()
    const context = useContext(DataContext);
    const onEquipmentTypeChanged = (
        event: React.ChangeEvent<HTMLInputElement>
    ) => {
        context.equipmentDataModifier({
            ...context.equipmentData,
            type: event.currentTarget.value,
        });
    };
    const onEquipmentSizeChanged = (
        event: React.ChangeEvent<HTMLInputElement>
    ) => {
        context.equipmentDataModifier({
            ...context.equipmentData,
            size: event.currentTarget.value,
        });
    };
    const onEquipmentMarkChanged = (
        event: React.ChangeEvent<HTMLInputElement>
    ) => {
        context.equipmentDataModifier({
            ...context.equipmentData,
            mark: event.currentTarget.value,
        });
    };
    const onEquipmentAvailableChanged = (
        event: React.ChangeEvent<HTMLInputElement>
    ) => {
        context.equipmentDataModifier({
            ...context.equipmentData,
            available: event.currentTarget.value,
        });
    };
    const onEquipmentCostChanged = (
        event: React.ChangeEvent<HTMLInputElement>
    ) => {
        context.equipmentDataModifier({
            ...context.equipmentData,
            cost: event.currentTarget.valueAsNumber
        });
    };
    async function handleClic() {
        authorizedApi.post(`${process.env.REACT_APP_API_BASE_URL}/api/equipment/newEquipment`
            , {

                "type": context.equipmentData.type,
                "size": context.equipmentData.size,
                "mark": context.equipmentData.mark,
                "available": context.equipmentData.available,
                "cost": context.equipmentData.cost
            })
            .then((res:AxiosResponse<EquipmentData>) => {
                context.isChangedEquipmentModifier(true)
                console.log("Got saved resort data");
                console.log(res.data);
                console.log("Equipment name: "+ res.data.type);
                console.log("is changed equipment flag: " + context.isChangedEquipment)
                onClose();
            });
    }

    return (
        <>
            {userContext.currentUser?.roles.includes(Role.ADMIN) && (
                <button className={"buttonAdd"} onClick={onOpen}>+ Add New</button>)}

            <Modal
                isOpen={isOpen}
                onClose={onClose}
            >
                <ModalOverlay/>
                <ModalContent>
                    <ModalHeader>Add Equipment</ModalHeader>
                    <ModalCloseButton/>
                    <ModalBody pb={6}>
                        <FormControl>
                            <FormLabel>Type</FormLabel>
                            <Input placeholder='Type' value={context.equipmentData.type}
                                   onChange={onEquipmentTypeChanged}/>
                        </FormControl>
                        <FormControl mt={4}>
                            <FormLabel>Size</FormLabel>
                            <Input placeholder='Size' value={context.equipmentData.size}
                                   onChange={onEquipmentSizeChanged}/>
                        </FormControl>
                        <FormControl mt={4}>
                            <FormLabel>Brand</FormLabel>
                            <Input placeholder='Brand' value={context.equipmentData.mark}
                                   onChange={onEquipmentMarkChanged}/>
                        </FormControl>
                        <FormControl mt={4}>
                            <FormLabel>Available</FormLabel>
                            <Input placeholder='Available'
                                   value={context.equipmentData.available}
                                   onChange={onEquipmentAvailableChanged}/>
                        </FormControl>
                        <FormControl mt={4}>
                            <FormLabel>Cost</FormLabel>
                            <Input placeholder='Cost'
                                   type={"number"}
                                   value={context.equipmentData.cost}
                                   onChange={onEquipmentCostChanged}/>
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
