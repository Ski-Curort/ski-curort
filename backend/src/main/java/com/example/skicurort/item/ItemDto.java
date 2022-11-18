package com.example.skicurort.item;

import com.example.skicurort.bill.Bill;

import java.math.BigDecimal;

public record ItemDto(String itemName, Long quantity, BigDecimal unitePrice, Long idBill) {
}
