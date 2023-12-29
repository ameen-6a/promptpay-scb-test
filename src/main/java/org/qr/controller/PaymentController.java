package org.qr.controller;

import org.qr.models.response.QR30Response;
import org.qr.service.QRService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.logging.Level;
import java.util.logging.Logger;

@RestController
public class PaymentController {
    @Autowired
    private QRService qrService;

    private static final Logger logger = Logger.getLogger(PaymentController.class.getName());

    @GetMapping("/v1/payment/qrcode/create")
    public @ResponseBody QR30Response getCreateQR(@RequestParam float amount) {
        logger.log(Level.INFO, "Request on /v1/payment/qrcode/create amount {0}", amount);
        return qrService.getQR30(amount);
    }
}
