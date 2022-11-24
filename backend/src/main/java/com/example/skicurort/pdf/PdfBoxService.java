package com.example.skicurort.pdf;

import com.example.skicurort.bill.BillDto;
import com.example.skicurort.bill.BillService;
import com.example.skicurort.item.ItemDto;
import com.example.skicurort.item.ItemService;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Calendar;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.graphics.image.JPEGFactory;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PdfBoxService {

  public static PDImageXObject createFromImage(PDDocument document, BufferedImage image)
      throws IOException {
    return JPEGFactory.createFromImage(document, image);
  }

  public static void writeLine(
      PDPageContentStream content, int tx, int ty, PDFont font, int fontSize, String text)
      throws IOException {
    if (font == null) {
      font = PDType1Font.HELVETICA;
    }
    content.beginText();
    content.setFont(font, fontSize);
    content.newLineAtOffset(tx, ty);
    content.showText(text);
    content.endText();
  }

  public static void drawImage(
      PDDocument document, PDPageContentStream content, int tx, int ty, BitMatrix bitMatrix) {
    BufferedImage bufferedImage = MatrixToImageWriter.toBufferedImage(bitMatrix);
    try {
      PDImageXObject image = createFromImage(document, bufferedImage);
      content.drawImage(image, tx, ty);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  static BillService billService;
  static ItemService itemService;

  public void createPdf(Long id) throws IOException, WriterException {
    List<ItemDto> items = itemService.findByBillId(id);
    BillDto billDto = billService.findByBillId(id);
    QrGenerator qrGenerator = new QrGenerator();
    BitMatrix bitMatrix = qrGenerator.generate(id);
    Calendar c = Calendar.getInstance();
    String pdfFileName =
        "filesPdf/invoice_"
            + c.get(Calendar.HOUR_OF_DAY)
            + ""
            + c.get(Calendar.MINUTE)
            + "_"
            + c.get(Calendar.SECOND)
            + ".pdf";
    PDDocument document = new PDDocument();
    PDPage page = new PDPage();
    document.addPage(page);
    PDPageContentStream content = new PDPageContentStream(document, page);
    writeLine(content, 275, 750, null, 30, "INVOICE");
    writeLine(content, 310, 730, null, 10, billDto.creationDate().toString());
    writeLine(content, 300, 710, null, 15, billDto.id().toString());
    writeLine(content, 50, 650, null, 12, billDto.curort().getCurortName());
    writeLine(content, 470, 650, null, 12, billDto.user().getName());
    writeLine(content, 50, 635, null, 12, billDto.curort().getCurortAdress());
    writeLine(content, 470, 635, null, 12, billDto.user().getEmail());
    writeLine(content, 50, 620, null, 12, billDto.curort().getCurrortEmail());
    writeLine(content, 50, 605, null, 12, billDto.curort().getCurortPhonenumber().toString());
    writeLine(content, 50, 550, null, 10, "NAME");
    writeLine(content, 350, 550, null, 10, "QUANTITY");
    writeLine(content, 420, 550, null, 10, "UNIT PRICE");
    writeLine(content, 490, 550, null, 10, "TOTAL PRICE");
    writeLine(
        content,
        50,
        540,
        null,
        10,
        "-------------------------------------------------------------------------------------------------------------------------------------------------------");
    int tyItem = 530;
    for (ItemDto itemDto : items) {

      writeLine(content, 50, tyItem, null, 10, itemDto.getItemName());
      writeLine(
          content,
          50,
          tyItem - 12,
          null,
          10,
          "-------------------------------------------------------------------------------------------------------------------------------------------------------");
      writeLine(content, 350, tyItem, null, 10, "" + itemDto.getQuantity());
      writeLine(content, 420, tyItem, null, 10, "" + itemDto.getUnitePrice());
      writeLine(content, 510, tyItem, null, 10, "" + itemDto.getTotalPrice());
      tyItem -= 24;
    }
    writeLine(content, 420, tyItem, null, 10, "TOTAL COST: ");
    writeLine(content, 510, tyItem, null, 10, "" + billService.totalPriceById(id));
    drawImage(document, content, 200, 10, bitMatrix);
    content.close();
    document.save(pdfFileName);
    document.close();
  }
}
