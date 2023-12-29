package org.qr.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.qr.models.response.QR30Response;
import org.qr.models.response.SCBAuthorizeResponse;
import org.qr.models.response.SCBGenerateQR30Response;
import org.qr.models.response.SlipVerificationResponse;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class QRService {
    private final org.slf4j.Logger logger = LoggerFactory.getLogger(QRService.class);
    @Autowired
    private SCBService scbService;

    public QR30Response getQR30(float amount) {
        try {
            SCBAuthorizeResponse scbAuthorizeResponse = scbService.postSCBAuthorization();
            String accessToken = scbAuthorizeResponse.getData().getAccessToken();

            SCBGenerateQR30Response generatedQR = scbService.postSCBCreateQR30(accessToken, amount);
            return new QR30Response().setBase64QR30String(generatedQR.getData().getQrImage());
        } catch (JsonProcessingException e) {
            logger.error("Failed to authorize to SCB: ", e);
        }
        return null;
    }

    public SlipVerificationResponse getVerifySlip(String transactionId, String sendingBank) throws JsonProcessingException {
        SCBAuthorizeResponse scbAuthorizeResponse = scbService.postSCBAuthorization();
        String accessToken = scbAuthorizeResponse.getData().getAccessToken();

        return scbService.getVerifySlip(accessToken, transactionId, sendingBank);
    }
}
