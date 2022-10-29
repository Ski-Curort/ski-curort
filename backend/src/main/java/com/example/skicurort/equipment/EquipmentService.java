package com.example.skicurort.equipment;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EquipmentService {

  private EquipmentRepository equipmentRepository;

  @Autowired
  public EquipmentService(EquipmentRepository equipmentRepository) {
    this.equipmentRepository = equipmentRepository;
  }

  List<Equipment> getAll() {
    return equipmentRepository.findAll();
  }
}
