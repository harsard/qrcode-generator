package com.qr.generator.qrcodegenerator;


import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageConfig;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;


@Slf4j
@Service
public class QrCodeGeneratorService {
    public byte[] generateQrCode(String text, int width, int height) throws WriterException, IOException {
        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        BitMatrix bitMatrix = qrCodeWriter.encode(text, BarcodeFormat.QR_CODE,width,height);
        ByteArrayOutputStream pngOutPutStream = new ByteArrayOutputStream();
        MatrixToImageConfig matrixToImageConfig = new MatrixToImageConfig();

        MatrixToImageWriter.writeToStream(bitMatrix,"PNG",pngOutPutStream,matrixToImageConfig);
        byte[] pngData = pngOutPutStream.toByteArray();
        log.info("QR code generated : {]",text);
        return pngData;
    }

}
