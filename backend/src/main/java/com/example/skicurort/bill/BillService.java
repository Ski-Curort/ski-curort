package com.example.skicurort.bill;

import static com.example.skicurort.bill.BillMapper.mapToDTO;

import com.example.skicurort.item.Item;
import com.example.skicurort.item.ItemRepo;
import com.example.skicurort.user.UserRepository;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BillService {
  private final BillRepo billRepo;
  private final ItemRepo itemRepo;
  private final UserRepository userRepository;

  public BillDto save(String name) {
    Bill bill = new Bill();
    bill.setCreationDate(LocalDate.now().toString());
    bill.setUserName(name);
    billRepo.save(bill);
    return mapToDTO(Optional.of(bill));
  }

  public BigDecimal totalPriceById(Long billId) {
    BigDecimal totalPrice = new BigDecimal(0);
    List<Item> itemList = itemRepo.findItemsByBillId(billId);
    itemList.stream().map(item -> item.getUnitePrice().add(totalPrice));

    return totalPrice;
  }

  public BillDto findByBillId(Long id) {
    return mapToDTO(billRepo.findBillById(id));
  }
}
