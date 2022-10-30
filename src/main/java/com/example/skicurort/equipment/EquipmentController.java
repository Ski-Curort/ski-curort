package com.example.skicurort.equipment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class EquipmentController {
     private final EquipmentService equipmentService;
    @Autowired
    EquipmentController(EquipmentService equipmentService) {
        this.equipmentService = equipmentService;
    }
    @GetMapping("/all")
    List<Equipment> getAllEquipment(){
       return equipmentService.getAll();
    }
    @GetMapping("/type/{type}")
    List<Equipment> getEquipmentByType(String type){
        return equipmentService.getAllEquipmentByType(type);
    }
    @PostMapping("/newEquipment")
    Equipment addNewEqupiment(Equipment equipment){
        return equipmentService.addEquipment(equipment);
    }
    @PutMapping("/update/{id}")
    Equipment updateEquipment(Long id,Equipment equipment){
        return equipmentService.editEquipment(id,equipment);
    }
    @DeleteMapping("/delete/{id}")
        Equipment removeEquipment(Long id){
            return equipmentService.deleteEquipment(id);
        }
    }



}
