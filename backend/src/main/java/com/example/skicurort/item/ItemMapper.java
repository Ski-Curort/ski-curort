package com.example.skicurort.item;

import com.example.skicurort.equipment.EquipmentDTO;
import java.util.List;

public class ItemMapper {

  static ItemDto mapToDTO(Item item) {
    return new ItemDto(item.getItemName(), item.getUnitePrice(), item.bill.getId());
  }

  static Item mapEquipmentToItem(EquipmentDTO equipmentDto) {
    Item item = new Item();
    item.setItemName(equipmentDto.type());
    item.setUnitePrice(equipmentDto.cost());
    return item;
  }

  static Item mapToEntity(ItemDto itemDto) {
    Item item = new Item();
    item.setItemName(itemDto.getItemName());
    item.setUnitePrice(itemDto.getUnitePrice());
    item.bill.setId(item.getId());
    return item;
  }

  static List<ItemDto> mapToDTOs(List<Item> items) {
    return items.stream().map(ItemMapper::mapToDTO).toList();
  }
}
