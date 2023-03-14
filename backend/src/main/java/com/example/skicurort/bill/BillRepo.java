package com.example.skicurort.bill;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BillRepo extends JpaRepository<Bill, Long> {

  Optional<Bill> findBillById(Long id);
}
