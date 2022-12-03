package com.example.skicurort.item;

import static com.example.skicurort.item.ItemMapper.mapEquipmentToItem;
import static com.example.skicurort.item.ItemMapper.mapToDTO;
import static com.example.skicurort.item.ItemMapper.mapToDTOs;

import com.example.skicurort.bill.BillRepo;
import com.example.skicurort.equipment.EquipmentDTO;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ItemService {

  private final ItemRepo itemRepo;
  private final BillRepo billRepo;

  public ItemDto addItem(EquipmentDTO equipmentDto, Long billId) {

    Item item = mapEquipmentToItem(equipmentDto);
    billRepo.findById(billId).get().getItemList().add(item);
    itemRepo.save(item);
    return mapToDTO(item);
  }

  public List<ItemDto> findByBillId(Long id) {
    return mapToDTOs(itemRepo.findItemsByBillId(id));
  }
}
