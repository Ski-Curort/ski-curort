package com.example.skicurort.item;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepo extends JpaRepository<Item, Long> {

  List<Item> findItemsByBillId(Long id);
}
