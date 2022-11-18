package com.example.skicurort.item;

import com.example.skicurort.bill.Bill;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.example.skicurort.item.ItemMapper.mapToDTO;
import static com.example.skicurort.item.ItemMapper.mapToEntity;

@Service
public class ItemService {

  private ItemRepo itemRepo;
  private BillRepo billRepo;

  public ItemDto save(ItemDto itemDto, Long id) {
bill
    return mapToDTO(itemRepo.save(mapToEntity(itemDto)));
  }
public Item save2 (Item item, Long id){
    item.bill.setId(id);
    return itemRepo.save(item)
}
  public List<ItemDto> findByBillId(Long id) {
    return findByBillId(id);
  }
}
