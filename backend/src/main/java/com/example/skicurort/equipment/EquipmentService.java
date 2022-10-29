package com.example.skicurort.equipment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EquipmentService {

    private EquipmentRepository equipmentRepository;
    @Autowired
    public EquipmentService(EquipmentRepository equipmentRepository) {
        this.equipmentRepository = equipmentRepository;
    }
    List<Equipment> getAll(){
        return equipmentRepository.findAll();
    }


}
