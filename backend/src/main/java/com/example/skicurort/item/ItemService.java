package com.example.skicurort.item;

import static com.example.skicurort.item.ItemMapper.mapEquipmentToDto;
import static com.example.skicurort.item.ItemMapper.mapToDTO;
import static com.example.skicurort.item.ItemMapper.mapToDTOs;
import static com.example.skicurort.item.ItemMapper.mapToEntity;

import com.example.skicurort.equipment.EquipmentDTO;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ItemService {

  private final ItemRepo itemRepo;

  public ItemDto addItem(EquipmentDTO equipmentDto, Long billId, Long quantity) {
    ItemDto itemDto = mapEquipmentToDto(equipmentDto);
    itemDto.setIdBill(billId);
    itemDto.setQuantity(quantity);
    Item item = mapToEntity(itemDto);
    itemRepo.save(item);
    return mapToDTO(item);
  }

  public List<ItemDto> findByBillId(Long id) {
    return mapToDTOs(itemRepo.findItemsByBillId(id));
  }
}
