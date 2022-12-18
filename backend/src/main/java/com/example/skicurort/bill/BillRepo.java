package com.example.skicurort.bill;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BillRepo extends JpaRepository<Bill, Long> {

Optional<Bill> findBillById(Long id);

}
