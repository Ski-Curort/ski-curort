package com.example.skicurort.item;

import com.example.skicurort.equipment.EquipmentDto;
import java.math.BigDecimal;

public class ItemMapper {

  static ItemDto mapToDTO(Item item) {
    return new ItemDto(
        item.getItemName(),
        item.getQuantity(),
        item.getUnitePrice(),
        item.bill.getId(),
        item.getTotalPrice());
  }

  static ItemDto mapEquipmentToDto(EquipmentDto equipmentDto) {
    ItemDto itemDto = new ItemDto();
    itemDto.setItemName(equipmentDto.mark());
    return itemDto;
  }

  static Item mapToEntity(ItemDto itemDto) {
    Item item = new Item();
    item.setItemName(itemDto.getItemName());
    item.setQuantity(itemDto.getQuantity());
    item.setUnitePrice(itemDto.getUnitePrice());
    item.bill.setId(item.getId());
    item.setTotalPrice(itemDto.getUnitePrice().multiply(BigDecimal.valueOf(itemDto.getQuantity())));
    return item;
  }
}
