package com.example.skicurort.pdfGenerator;

import com.example.skicurort.bill.BillDto;
import com.example.skicurort.bill.BillService;
import java.io.IOException;
import java.util.Calendar;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType1Font;

public class PdfBox {
  private static BillService billService;

  public static void writeLine(
      PDPageContentStream content, int tx, int ty, PDFont font, int fontSize, String text)
      throws IOException {
    if (font == null) {
      font = PDType1Font.HELVETICA;
    }
    ;

    content.beginText();
    content.setFont(font, fontSize);
    content.newLineAtOffset(tx, ty);
    content.showText(text);
    content.endText();
  }

  public static void createPdf(Long id) throws IOException {
    BillDto bill = billService.findByBillId(id);
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
    writeLine(content, 280, 750, null, 30, "INVOICE");
    writeLine(content, 320, 730, null, 10, bill.creationDate().toString());
    writeLine(content, 300, 710, null, 15, "NR " + bill.id());
    writeLine(content, 50, 680, null, 12, bill.curort().getCurortName());
    writeLine(content, 470, 680, null, 12, bill.user().getName());
    writeLine(content, 50, 665, null, 12, bill.curort().getCurortAdress());
    writeLine(content, 470, 665, null, 12, bill.user().getEmail());
    writeLine(content, 50, 650, null, 12, bill.curort().getCurrortEmail());
    writeLine(content, 50, 635, null, 12, bill.curort().getCurortPhonenumber().toString());
    writeLine(content, 50, 605, null, 10, "NAME");
    writeLine(content, 220, 605, null, 10, "QUANTITY");
    writeLine(content, 310, 605, null, 10, "UNIT PRICE");
    writeLine(content, 400, 605, null, 10, "TOTAL PRICE");
    writeLine(
        content,
        50,
        600,
        null,
        10,
        "----------------------------------------------------------------------------------------------------------------------------------------------------------------");
    int tyItem = 580;
    for (int i = 0; i < bill.itemList().size(); i++) {
      writeLine(content, 50, tyItem, null, 10, bill.itemList().get(i).getItemName());
      writeLine(
          content,
          50,
          tyItem - 5,
          null,
          10,
          "------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
      writeLine(content, 220, tyItem, null, 10, "" + bill.itemList().get(i).getQuantity());
      writeLine(content, 310, tyItem, null, 10, "" + bill.itemList().get(i).getUnitePrice());
      writeLine(content, 400, tyItem, null, 10, "" + bill.itemList().get(i).getTotalPrice());
      tyItem -= 12;
    }
    content.close();
    document.save(pdfFileName);
    document.close();
  }
}
