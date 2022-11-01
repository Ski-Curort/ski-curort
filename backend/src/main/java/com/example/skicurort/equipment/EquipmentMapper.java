package com.example.skicurort.equipment;

import java.util.List;

public class EquipmentMapper {
  public EquipmentMapper() {}

  static EquipmentDTO mapToDTO(Equipment equipment) {
    return new EquipmentDTO(
        equipment.getId(),
        equipment.getType(),
        equipment.getMark(),
        equipment.isAvailable(),
        equipment.getCost());
  }

  static Equipment mapToEntity(EquipmentDTO equipmentDTO) {
    Equipment equipment = new Equipment();
    equipment.setId(equipmentDTO.id());
    equipment.setType(equipmentDTO.type());
    equipment.setMark(equipmentDTO.mark());
    equipment.setAvailable(equipmentDTO.available());
    equipment.setCost(equipmentDTO.cost());
    return equipment;
  }

  static List<EquipmentDTO> mapToDTOs(List<Equipment> equipments) {
    return equipments.stream().map(EquipmentMapper::mapToDTO).toList();
  }
}
