package com.qr.generator.qrcodegenerator.controller;


import com.google.zxing.WriterException;
import com.qr.generator.qrcodegenerator.QrCodeGeneratorService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;


@Slf4j
@RestController
@RequestMapping("/api")
public class QrCodeController {

    private final QrCodeGeneratorService qrCodeGeneratorService;

    public QrCodeController(QrCodeGeneratorService qrCodeGeneratorService) {
        this.qrCodeGeneratorService = qrCodeGeneratorService;
    }


    @PostMapping("/qrcodegeneration")
    public ResponseEntity handleRequest(@RequestBody QrCodeRequest qrCodeRequest){
        log.info("QR code generate qrCodeRequest :{}",qrCodeRequest);
        if(qrCodeRequest.qrEmbedableUrl() == null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(HttpHeaders.CONTENT_TYPE,"image/png");
        log.info("Start QR Code generation");
        try {
            return new ResponseEntity<byte[]>(
                    qrCodeGeneratorService.generateQrCode(qrCodeRequest.qrEmbedableUrl(), 512, 512),httpHeaders, HttpStatus.OK);
        } catch (WriterException e) {
            log.error("Error Occured WriterException:{]",e.getMessage());
            throw new RuntimeException(e);
        } catch (IOException e) {
            log.error("Error Occured IOException :{]",e.getMessage());
            throw new RuntimeException(e);
        }

    }
}
