package com.example.skicurort.item;

import java.math.BigDecimal;

public class ItemDto {

  private String itemName;
  private Long quantity;
  private BigDecimal unitePrice;
  private Long idBill;
  private BigDecimal totalPrice;

  public ItemDto(
      String itemName, Long quantity, BigDecimal unitePrice, Long idBill, BigDecimal totalPrice) {
    this.itemName = itemName;
    this.quantity = quantity;
    this.unitePrice = unitePrice;
    this.idBill = idBill;
    this.totalPrice = totalPrice;
  }

  public ItemDto() {}

  public String getItemName() {
    return itemName;
  }

  public void setItemName(String itemName) {
    this.itemName = itemName;
  }

  public Long getQuantity() {
    return quantity;
  }

  public void setQuantity(Long quantity) {
    this.quantity = quantity;
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

  public BigDecimal getTotalPrice() {
    return totalPrice;
  }

  public void setTotalPrice(BigDecimal totalPrice) {
    this.totalPrice = totalPrice;
  }
}
