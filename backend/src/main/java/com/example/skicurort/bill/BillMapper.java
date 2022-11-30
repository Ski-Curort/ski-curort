package com.example.skicurort.bill;

import java.util.Optional;

public class BillMapper {

  static BillDto mapToDTO(Optional<Bill> bill) {
    return new BillDto(
        bill.get().getId(),
        bill.get().getTotalCost(),
        bill.get().getCreationDate(),
        // bill.get().getUser(),
        bill.get().getCurort(),
        bill.get().itemList);
  }

  static Bill mapToEntity(BillDto billDto) {
    Bill bill = new Bill();
    bill.setId(billDto.id());
    bill.setTotalCost(billDto.totalCost());
    bill.setCreationDate(billDto.creationDate());
    // bill.setUser(billDto.user());
    bill.setCurort(billDto.curort());
    bill.setItemList(billDto.itemList());

    return bill;
  }
}
