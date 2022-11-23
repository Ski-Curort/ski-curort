package com.example.skicurort.item;

import com.example.skicurort.equipment.EquipmentDTO;
import java.math.BigDecimal;
import java.util.List;

public class ItemMapper {

  static ItemDto mapToDTO(Item item) {
    return new ItemDto(
        item.getItemName(),
        item.getQuantity(),
        item.getUnitePrice(),
        item.bill.getId(),
        item.getTotalPrice());
  }

  static ItemDto mapEquipmentToDto(EquipmentDTO equipmentDto) {
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

  static List<ItemDto> mapToDTOs(List<Item> items) {
    return items.stream().map(ItemMapper::mapToDTO).toList();
  }
}
