package com.example.skicurort.equipment;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.Optional;
import org.junit.jupiter.api.Test;

class EquipmentServiceTest {
  private static final Equipment equipment =
      new Equipment(1L, "ski", "XL", "HEAD", true, new BigDecimal(450.99));
  private static final EquipmentDTO equipmentDTO =
      new EquipmentDTO(1L, "ski", "XL", "HEAD", true, new BigDecimal(450.99));
  private static final EquipmentDTO newEquipmentDTO =
      new EquipmentDTO(1L, "snowboard", "XL", "HEAD", false, new BigDecimal(450.99));

  @Test
  void shouldFindEquipmentById() {
    // GIVEN
    EquipmentRepository mockEquipmentRepository = mock(EquipmentRepository.class);
    when(mockEquipmentRepository.findById(1L)).thenReturn(Optional.of(equipment));
    // WHEN
    EquipmentService equipmentService = new EquipmentService(mockEquipmentRepository);
    // THEN
    assertEquals(equipmentDTO, equipmentService.getOneItem(1L));
  }

  @Test
  void shouldAddEquipment() {
    // GIVEN
    EquipmentRepository mockEquipmentRepository = mock(EquipmentRepository.class);
    when(mockEquipmentRepository.save(any())).thenReturn(equipment);
    // WHEN
    EquipmentService equipmentService = new EquipmentService(mockEquipmentRepository);
    // THEN
    assertEquals(equipmentDTO, equipmentService.addEquipment(equipmentDTO));
  }

  @Test
  void shouldEditEquipment() {
    // GIVEN
    EquipmentRepository mockEquipmentRepository = mock(EquipmentRepository.class);
    EquipmentService equipmentService = new EquipmentService(mockEquipmentRepository);
    // WHEN
    when(mockEquipmentRepository.findById(any())).thenReturn(Optional.of(equipment));
    when(mockEquipmentRepository.save(any())).thenReturn(equipment);
    equipmentService.editEquipment(newEquipmentDTO, 1L);
    // THEN
    assertEquals(equipment.getType(), newEquipmentDTO.type());
    assertEquals(equipment.isAvailable(), newEquipmentDTO.available());
  }

  @Test
  void shouldDeleteEquipment() {
    // GIVEN
    EquipmentRepository mockEquipmentRepository = mock(EquipmentRepository.class);
    doNothing().when(mockEquipmentRepository).deleteById(any());
    when(mockEquipmentRepository.existsById(any())).thenReturn(true);
    // WHEN
    EquipmentService equipmentService = new EquipmentService(mockEquipmentRepository);
    equipmentService.deleteEquipment(any());
    // THEN
    verify(mockEquipmentRepository).deleteById(any());
  }
}
