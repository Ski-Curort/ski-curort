package com.example.skicurort.equipment;

import static com.example.skicurort.equipment.EquipmentMapper.mapToDTO;
import static com.example.skicurort.equipment.EquipmentMapper.mapToDTOs;
import static com.example.skicurort.equipment.EquipmentMapper.mapToEntity;

import java.util.List;
import java.util.NoSuchElementException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EquipmentService {

  private EquipmentRepository equipmentRepository;

  @Autowired
  EquipmentService(EquipmentRepository equipmentRepository) {
    this.equipmentRepository = equipmentRepository;
  }

  List<EquipmentDTO> getAll() {
    return mapToDTOs(equipmentRepository.findAll());
  }

  List<EquipmentDTO> getAllEquipmentByType(String type) {
    return mapToDTOs(equipmentRepository.findAllByType(type));
  }

  EquipmentDTO getOneItem(Long id) {
    return mapToDTO(
        equipmentRepository
            .findById(id)
            .orElseThrow(() -> new NoSuchElementException("item with id: " + id + " not exist")));
  }

  EquipmentDTO addEquipment(EquipmentDTO equipmentDTO) {
    return mapToDTO(equipmentRepository.save(mapToEntity(equipmentDTO)));
  }

  EquipmentDTO editEquipment(Long id, EquipmentDTO equipmentDTO) throws NoSuchElementException {
    Equipment equipment =
        equipmentRepository
            .findById(id)
            .orElseThrow(() -> new NoSuchElementException("item with id: " + id + " not exist"));
    equipment.setType(equipmentDTO.type());
    equipment.setAvailable(equipmentDTO.available());
    equipment.setMark(equipmentDTO.mark());
    equipment.setCost(equipmentDTO.cost());
    return mapToDTO(equipmentRepository.save(equipment));
  }

  void deleteEquipment(Long id) {
    Equipment equipment =
        equipmentRepository
            .findById(id)
            .orElseThrow(() -> new NoSuchElementException("item with id: " + id + " not exist"));
    equipmentRepository.deleteById(id);
  }
}
