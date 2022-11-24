package com.example.skicurort.pdfGenerator;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import java.io.File;
import java.io.IOException;
import java.util.Calendar;

public class QrGenerator {
  public static void generateQRcode(String data, String path, String charset, int h, int w)
      throws WriterException, IOException {
    BitMatrix matrix =
        new MultiFormatWriter()
            .encode(new String(data.getBytes(charset), charset), BarcodeFormat.QR_CODE, w, h);
    MatrixToImageWriter.writeToFile(
        matrix, path.substring(path.lastIndexOf('.') + 1), new File(path));
  }

  public String generate(Long id) throws IOException, WriterException {
    String content = String.valueOf(id);
    Calendar calendar = Calendar.getInstance();
    String path =
        "filesPdf/Quote_"
            + calendar.get(Calendar.DATE)
            + ""
            + calendar.get(Calendar.HOUR_OF_DAY)
            + "_"
            + calendar.get(Calendar.MINUTE)
            + "_"
            + calendar.get(Calendar.SECOND)
            + ".png";
    String charset = "UTF-8";
    generateQRcode(content, path, charset, 250, 250);
    return path;
  }
}
