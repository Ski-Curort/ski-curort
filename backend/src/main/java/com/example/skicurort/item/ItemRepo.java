package com.example.skicurort.item;


import org.springframework.data.jpa.repository.JpaRepository;


import java.util.List;

public interface ItemRepo extends JpaRepository<Item, Long>  {

    List<Item> findItemsByBillId(Long id);
}
