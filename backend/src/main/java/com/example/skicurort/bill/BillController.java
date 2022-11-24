package com.example.skicurort.bill;

import com.example.skicurort.exception.Error;
import com.example.skicurort.exception.NoIdException;
import java.math.BigDecimal;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/bill/")
public class BillController {

  private final BillService billService;

  @GetMapping("/{billid}/price")
  ResponseEntity<BigDecimal> totalPriceById(@PathVariable Long billid) throws NoIdException {
    return ResponseEntity.ok(billService.totalPriceById(billid));
  }

  @PostMapping("/user/{userId}")
  public ResponseEntity<BillDto> save(@PathVariable Long userId) throws NoIdException {

    return ResponseEntity.ok(billService.save(userId));
  }


  @GetMapping("/user/{userName)")
  ResponseEntity<BillDto> findByUserName(@PathVariable String userName) throws NoIdException {
    return ResponseEntity.ok(billService.findByUserName(userName));

  }

  @ExceptionHandler(NoIdException.class)
  @ResponseStatus(HttpStatus.NOT_FOUND)
  public Error idNotFoundHandler(NoIdException ex) {

    return new Error(ex.getMessage());
  }
}
