package com.example.skicurort.item;

import com.example.skicurort.equipment.Equipment;
import com.example.skicurort.equipment.EquipmentDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/item/")
public class ItemController {

  private final ItemService itemService;

  @GetMapping("/{billid}")
  ResponseEntity<List<ItemDto>> findByBillId(@PathVariable Long billid) {
    return ResponseEntity.ok(itemService.findByBillId(billid));
  }

  @PostMapping("/{billId}/{quantity}")
  public ResponseEntity<ItemDto> save(@RequestBody EquipmentDto equipmentDto, @PathVariable Long billId,Long quantity) {

    return ResponseEntity.ok(itemService.addItem(equipmentDto,billId,quantity));
  }
}
