package org.qr.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.NonNull;
import org.qr.models.response.SlipVerificationResponse;
import org.qr.service.QRService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.bind.DefaultValue;
import org.springframework.web.bind.annotation.*;

import java.util.logging.Level;
import java.util.logging.Logger;

@RestController
public class BillingController {

    @Autowired
    private QRService qrService;

    private static final Logger logger = Logger.getLogger(BillingController.class.getName());
    @GetMapping("/v1/billing/notification/receive")
    public @ResponseBody String getReceiveNotification(@RequestParam String now, @RequestBody String request) {
        logger.log(Level.INFO, "Request from /v1/billing/notification/receive request: {0}", request);
        return "yo";
    }

    @GetMapping("/v1/billing/validate/{transactionId}")
    public @ResponseBody SlipVerificationResponse getSlipVerification(
            @PathVariable String transactionId,
            @RequestParam(required = false, defaultValue = "014") String sendingBank
    ) throws JsonProcessingException {
        return qrService.getVerifySlip(transactionId, sendingBank);
    }
}
