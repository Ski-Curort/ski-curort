package com.example.skicurort.equipment;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/equipment")
public class EquipmentControler {
  private final EquipmentService equipmentService;

  @Autowired
  EquipmentControler(EquipmentService equipmentService) {
    this.equipmentService = equipmentService;
  }

  @GetMapping("/all")
  ResponseEntity<List<EquipmentDTO>> getAllEquipment() {
    return ResponseEntity.ok(equipmentService.getAll());
  }

  @GetMapping("/type/{type}")
  ResponseEntity<List<EquipmentDTO>> getEquipmentByType(@PathVariable String type) {
    return ResponseEntity.ok(equipmentService.getAllEquipmentByType(type));
  }

  @PostMapping("/newEquipment")
  ResponseEntity<EquipmentDTO> addNewEquipment(@RequestBody EquipmentDTO equipmentDTO) {
    equipmentService.addEquipment(equipmentDTO);
    return ResponseEntity.ok(equipmentDTO);
  }

  @PutMapping("/update/{id}")
  ResponseEntity<EquipmentDTO> updateEquipment(@PathVariable Long id, @RequestBody EquipmentDTO equipmentDTO) {
    return ResponseEntity.ok(equipmentService.editEquipment(id, equipmentDTO));
  }

  @DeleteMapping("/delete/{id}")
  HttpStatus removeEquipment(@PathVariable Long id) {
    equipmentService.deleteEquipment(id);
    return HttpStatus.OK;
  }
}
