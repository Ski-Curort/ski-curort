package com.example.skicurort.item;

import com.example.skicurort.bill.Bill;

import java.util.List;

public class ItemMapper {

  static ItemDto mapToDTO(Item item) {
    return new ItemDto(item.getItemName(), item.getQuantity(), item.getUnitePrice(), item.bill.getId());
  }

  static Item mapToEntity(ItemDto itemDto, Bill bill) {
    Item item = new Item();
    item.setItemName(itemDto.itemName());
    item.setQuantity(itemDto.quantity());
    item.setUnitePrice(itemDto.unitePrice());
    item.setBill(bill);
    return item;
  }

  static List<ItemDto> mapToDTOs(List<Item> items) {
    return items.stream().map(ItemMapper::mapToDTO).toList();
  }
}
