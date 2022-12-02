package com.example.skicurort.item;

import com.example.skicurort.equipment.EquipmentDTO;
import com.example.skicurort.exception.NoIdException;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/item/")
public class ItemController {

  private final ItemService itemService;

  @GetMapping("/{billid}")
  ResponseEntity<List<ItemDto>> findByBillId(@PathVariable Long billid) throws NoIdException {
    return ResponseEntity.ok(itemService.findByBillId(billid));
  }

  @PostMapping("/{billId}")
  public ResponseEntity<ItemDto> save(
      @RequestBody EquipmentDTO equipmentDTO, @PathVariable Long billId)
      throws NoIdException {

    return ResponseEntity.ok(itemService.addItem(equipmentDTO, billId));
  }

  @ExceptionHandler(NoIdException.class)
  @ResponseStatus(HttpStatus.NOT_FOUND)
  public Error idNotFoundHandler(NoIdException ex) {

    return new Error(ex.getMessage());
  }
}
