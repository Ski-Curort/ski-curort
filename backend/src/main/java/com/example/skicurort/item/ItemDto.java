package com.example.skicurort.item;

import java.math.BigDecimal;

public class ItemDto {

  private String itemName;
  private BigDecimal unitePrice;
  private Long idBill;


  public ItemDto(
      String itemName,  BigDecimal unitePrice, Long idBill ) {
    this.itemName = itemName;
    this.unitePrice = unitePrice;
    this.idBill = idBill;
  }

  public ItemDto() {}

  public String getItemName() {
    return itemName;
  }

  public void setItemName(String itemName) {
    this.itemName = itemName;
  }





  public BigDecimal getUnitePrice() {
    return unitePrice;
  }

  public void setUnitePrice(BigDecimal unitePrice) {
    this.unitePrice = unitePrice;
  }

  public Long getIdBill() {
    return idBill;
  }

  public void setIdBill(Long idBill) {
    this.idBill = idBill;
  }




}
