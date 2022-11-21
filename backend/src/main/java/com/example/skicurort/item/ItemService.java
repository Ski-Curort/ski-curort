package com.example.skicurort.item;

import static com.example.skicurort.item.ItemMapper.mapEquipmentToDto;
import static com.example.skicurort.item.ItemMapper.mapToDTO;
import static com.example.skicurort.item.ItemMapper.mapToEntity;

import com.example.skicurort.bill.BillRepo;
import com.example.skicurort.equipment.EquipmentDto;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class ItemService {

  private ItemRepo itemRepo;
  private BillRepo billRepo;

  public ItemDto addItem(EquipmentDto equipmentDto, Long billId, Long quantity) {
    ItemDto itemDto = mapEquipmentToDto(equipmentDto);
    itemDto.setIdBill(billId);
    itemDto.setQuantity(quantity);
    Item item = mapToEntity(itemDto);
    itemRepo.save(item);
    return mapToDTO(item);
  }

  public List<ItemDto> findByBillId(Long id) {
    return findByBillId(id);
  }
}
