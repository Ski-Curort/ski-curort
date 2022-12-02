package com.example.skicurort.equipment;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
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

  @GetMapping("/search/{id}")
  ResponseEntity<EquipmentDTO> getEquipmentById(@PathVariable Long id) {
    return ResponseEntity.ok(equipmentService.getOneItem(id));
  }

  @GetMapping("/search?type={type}&size={size}")
  ResponseEntity<List<EquipmentDTO>> getEquipmentByTypeAndSize(
      @PathVariable String type, @PathVariable String size) {
    return ResponseEntity.ok(equipmentService.getEquipmentByTypeAndSize(type, size));
  }

  @GetMapping("/search?type={type}&size={size}&available={available}")
  ResponseEntity<List<EquipmentDTO>> getEquipmentByTypeAndSizeAndAvailable(
      @PathVariable String type, @PathVariable String size, @PathVariable boolean available) {
    return ResponseEntity.ok(
        equipmentService.getEquipmentByTypeAndSizeAndAvailable(type, size, available));
  }

  @PostMapping("/newEquipment")
  ResponseEntity<EquipmentDTO> addNewEquipment(@RequestBody EquipmentDTO equipmentDTO) {
    equipmentService.addEquipment(equipmentDTO);
    return ResponseEntity.ok(equipmentDTO);
  }

  @GetMapping("/{id}")
  ResponseEntity<List<EquipmentDTO>> getAllEquipmentByCurortId(@PathVariable Long id) {
    return ResponseEntity.ok(equipmentService.getByCurortId(id));
  }

  @PutMapping("/update/{id}")
  ResponseEntity<EquipmentDTO> updateEquipment(
      @PathVariable Long id, @RequestBody EquipmentDTO equipmentDTO) {
    return ResponseEntity.ok(equipmentService.editEquipment(equipmentDTO, id));
  }

  @DeleteMapping("/delete/{id}")
  HttpStatus removeEquipment(@PathVariable Long id) {
    equipmentService.deleteEquipment(id);
    return HttpStatus.OK;
  }
}
