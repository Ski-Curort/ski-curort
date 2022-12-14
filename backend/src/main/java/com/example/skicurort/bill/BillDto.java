package com.example.skicurort.bill;

import com.example.skicurort.curort.Curort;
import com.example.skicurort.item.Item;
import java.math.BigDecimal;
import java.util.List;

public record BillDto(
    Long id,
    BigDecimal totalCost,
    String creationDate,
    String userName,
    Curort curort,
    List<Item> itemList) {}
