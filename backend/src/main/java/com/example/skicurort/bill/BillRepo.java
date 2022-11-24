package com.example.skicurort.bill;

import org.springframework.data.jpa.repository.JpaRepository;

public interface BillRepo extends JpaRepository<Bill, Long> {

  Bill findBillById(Long id);
}
