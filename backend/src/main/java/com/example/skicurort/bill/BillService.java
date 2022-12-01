package com.example.skicurort.bill;

import static com.example.skicurort.bill.BillMapper.mapToDTO;

import com.example.skicurort.item.Item;
import com.example.skicurort.item.ItemRepo;
import com.example.skicurort.user.UserRepository;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BillService {
  private final BillRepo billRepo;
  private final ItemRepo itemRepo;
  private final UserRepository userRepository;

  public BillDto save(UUID userId) {
    Bill bill = new Bill();
    bill.setCreationDate(new Date());
    bill.setUser(userRepository.getReferenceById(userId));
    billRepo.save(bill);
    return mapToDTO(Optional.of(bill));
  }

  public BigDecimal totalPriceById(Long billId) {

    List<Item> itemList = itemRepo.findItemsByBillId(billId);
    BigDecimal totalPrice = new BigDecimal(0);
    for (Item item : itemList) {
      totalPrice = totalPrice.add(item.getTotalPrice());
    }
    return totalPrice;
  }

  public BillDto findByBillId(Long id) {
    return mapToDTO(Optional.ofNullable(billRepo.findBillById(id)));
  }
}
