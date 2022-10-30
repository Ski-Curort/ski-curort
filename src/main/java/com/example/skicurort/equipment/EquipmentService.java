package com.example.skicurort.equipment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class EquipmentService {

    private EquipmentRepository equipmentRepository;
    @Autowired
    EquipmentService(EquipmentRepository equipmentRepository) {
        this.equipmentRepository = equipmentRepository;
    }
    List<Equipment> getAll(){
        return equipmentRepository.findAll();
    }
    List<Equipment> getAllEquipmentByType(String type){
        return equipmentRepository.findAllByType(type);
    }
    Equipment getOneItem(Long id){
        return equipmentRepository.findById(id).orElseThrow(()->new NoSuchElementException("item with id: "+id+" not exist"));
    }

    Equipment addEquipment(@RequestBody Equipment equipment){
        return equipmentRepository.save(equipment);
    }
    Equipment editEquipment(@PathVariable Long id, @RequestBody Equipment equipmentToUpdate){
        return equipmentRepository.findById(id).map(equipment -> {
            equipment.setType(equipmentToUpdate.getType());
            equipment.setAvailable(equipmentToUpdate.isAvailable());
            equipment.setMark(equipmentToUpdate.getMark());
            equipment.setCost(equipmentToUpdate.getCost());
            return equipmentRepository.save(equipment);
        }).orElseThrow(()->new NoSuchElementException("item with id: "+id+" not exist"));
    }
    Equipment deleteEquipment(Long id){
        Equipment equipment = equipmentRepository.findById(id)
                .orElseThrow(()->new NoSuchElementException("item with id: "+id+" not exist"));
        equipmentRepository.deleteById(id);
        return equipment;
    }

}
