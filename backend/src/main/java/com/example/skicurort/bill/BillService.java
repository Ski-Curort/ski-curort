package com.example.skicurort.bill;

import com.example.skicurort.item.Item;
import com.example.skicurort.item.ItemRepo;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.List;

import static com.example.skicurort.bill.BillMapper.mapToDTO;

public class BillService {
  static BillRepo billRepo;
  static ItemRepo itemRepo;
  static UserRepository userRepository;

  public BillDto save(Long userId) {
    Bill bill = new Bill();
    bill.setCreationDate(new SimpleDateFormat());
    bill.setUser(userRepository.findById(userId));
    billRepo.save(bill);
    return mapToDTO(bill);
  }

  public BigDecimal totalPriceById(Long billId) {

    List<Item> itemList = itemRepo.findItemsByBillId(billId);
    BigDecimal totalPrice = new BigDecimal(0);
    for (Item item : itemList) {
      totalPrice = totalPrice.add(item.getTotalPrice());
    }
    return totalPrice;
  }

  public BillDto findByUserName(String userName) {
    return mapToDTO(billRepo.findByUserName(userName));
  }
}
