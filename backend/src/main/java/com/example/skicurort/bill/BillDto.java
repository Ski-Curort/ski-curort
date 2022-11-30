package com.example.skicurort.bill;

import com.example.skicurort.curort.Curort;
import com.example.skicurort.item.Item;
// import com.example.skicurort.user.User;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public record BillDto(
    Long id,
    BigDecimal totalCost,
    Date creationDate,
    // User user,
    Curort curort,
    List<Item> itemList) {}
