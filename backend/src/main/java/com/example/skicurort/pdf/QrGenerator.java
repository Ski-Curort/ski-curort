package com.example.skicurort.pdf;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import java.io.IOException;

public class QrGenerator {
  public static BitMatrix generateQRcode(String data, String charset, int h, int w)
      throws WriterException, IOException {
    return new MultiFormatWriter()
        .encode(new String(data.getBytes(charset), charset), BarcodeFormat.QR_CODE, w, h);
  }

  public BitMatrix generate(Long id) throws IOException, WriterException {
    String content = String.valueOf(id);
    String charset = "UTF-8";
    return generateQRcode(content, charset, 250, 250);
  }
}
