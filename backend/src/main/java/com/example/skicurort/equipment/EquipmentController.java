package com.example.skicurort.equipment;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EquipmentController {
  private final EquipmentService equipmentService;

  @Autowired
  public EquipmentController(EquipmentService equipmentService) {
    this.equipmentService = equipmentService;
  }

  @GetMapping
  List<Equipment> getAllEqiupment() {
    return equipmentService.getAll();
  }
}
