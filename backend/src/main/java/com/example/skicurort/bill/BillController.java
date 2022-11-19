package com.example.skicurort.bill;

import com.example.skicurort.equipment.EquipmentDto;
import com.example.skicurort.item.ItemDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/bill/")
public class BillController {

  private final BillService billService;

  @GetMapping("/price/{billid}")
  ResponseEntity<BigDecimal> totalPriceById(@PathVariable Long billid) {
    return ResponseEntity.ok(billService.totalPriceById(billid));
  }

  @PostMapping("/{userId}")
  public ResponseEntity<BillDto> save(@PathVariable Long userId) {

    return ResponseEntity.ok(billService.save(userId));
  }

  @GetMapping("/{userName)")
  ResponseEntity<BillDto> findByUserName(@PathVariable String userName) {
    return ResponseEntity.ok(billService.findByUserName(userName));
  }
}
