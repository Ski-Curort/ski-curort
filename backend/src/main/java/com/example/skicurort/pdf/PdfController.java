package com.example.skicurort.pdf;

import com.example.skicurort.bill.BillDto;
import com.example.skicurort.bill.BillService;
import com.example.skicurort.exception.NoIdException;
import com.example.skicurort.item.ItemService;
import com.google.zxing.WriterException;
import java.io.IOException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/pdf/")
public class PdfController {

  ItemService itemService;
  BillService billService;

  PdfBoxService pdfBoxService;

  @GetMapping("/{billid}")
  ResponseEntity<BillDto> totalPriceById(@PathVariable Long billid)
      throws NoIdException, IOException, WriterException {
    pdfBoxService.createPdf(billid);
    itemService.findByBillId(billid);
    return ResponseEntity.ok(billService.findByBillId(billid));
  }
}
