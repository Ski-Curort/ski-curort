package com.example.skicurort.bill;

public class BillMapper {

  static BillDto mapToDTO(Bill bill) {
    return new BillDto(
        bill.getId(),
        bill.getTotalCost(),
        bill.getCreationDate(),
        bill.getUser(),
        bill.getCurort(),
        bill.itemList);
  }

  static Bill mapToEntity(BillDto billDto) {
    Bill bill = new Bill();
    bill.setId(billDto.id());
    bill.setTotalCost(billDto.totalCost());
    bill.setCreationDate(billDto.creationDate());
    bill.setUser(billDto.user());
    bill.setCurort(billDto.curort());
    bill.setItemList(billDto.itemList());

    return bill;
  }
}
