package com.example.skicurort.equipment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/equipment")
public class EquipmentControler {
  private final EquipmentService equipmentService;
  @Autowired
  EquipmentControler(EquipmentService equipmentService) {
    this.equipmentService = equipmentService;
  }
  @GetMapping("/all")
  ResponseEntity<List<EquipmentDTO>> getAllEquipment(){
    return ResponseEntity.ok(equipmentService.getAll());
  }
  @GetMapping("/type/{type}")
  ResponseEntity<List<EquipmentDTO>> getEquipmentByType(String type){
    return ResponseEntity.ok(equipmentService.getAllEquipmentByType(type));
  }
  @PostMapping("/newEquipment")
  ResponseEntity<EquipmentDTO> addNewEquipment(EquipmentDTO equipmentDTO){
    equipmentService.addEquipment(equipmentDTO);
    return ResponseEntity.ok(equipmentDTO);
  }
  @PutMapping("/update/{id}")
  ResponseEntity<EquipmentDTO> updateEquipment(Long id,EquipmentDTO equipmentDTO){
    return ResponseEntity.ok(equipmentService.editEquipment(id,equipmentDTO));
  }
  @DeleteMapping("/delete/{id}")
  HttpStatus removeEquipment(Long id){
    equipmentService.deleteEquipment(id);
    return HttpStatus.OK;
  }

}
