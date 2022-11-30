package com.example.skicurort.equipment;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EquipmentRepository extends JpaRepository<Equipment, Long> {
  List<Equipment> findAllByType(String type);

  List<Equipment> findAllByTypeAndSize(String type, String Size);

  List<Equipment> findAllByTypeAndSizeAndAvailable(String type, String Size, boolean available);
}
