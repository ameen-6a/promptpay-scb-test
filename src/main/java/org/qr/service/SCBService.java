package org.qr.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.qr.models.request.SCBAuthorizeRequest;
import org.qr.models.request.SCBGenerateQR30Request;
import org.qr.models.response.SCBAuthorizeResponse;
import org.qr.models.response.SCBGenerateQR30Response;
import org.qr.models.response.SlipVerificationResponse;
import org.qr.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
public class SCBService {
    @Autowired
    private RestTemplate restTemplate;

    @Value("${scb.key.resource-owner-id}")
    private String resourceOwnerId;
    @Value("${scb.key.application-key}")
    private String applicationKey;
    @Value("${scb.key.application-secret}")
    private String applicationSecret;

    private final String SCB_URL = "https://api-sandbox.partners.scb/partners/sandbox";

    public SCBAuthorizeResponse postSCBAuthorization() throws JsonProcessingException {
        HttpHeaders headers = new HttpHeaders();
        getSCBHeader(headers);

        SCBAuthorizeRequest request = new SCBAuthorizeRequest();
        request.setApplicationKey(applicationKey);
        request.setApplicationSecret(applicationSecret);

        HttpEntity<String> requestEntity = new HttpEntity<String>(Constants.OBJECT_MAPPER.writeValueAsString(request), headers);

        ResponseEntity<SCBAuthorizeResponse> responseEntity = restTemplate.exchange(
                SCB_URL + "/v1/oauth/token",
                HttpMethod.POST,
                requestEntity,
                new ParameterizedTypeReference<SCBAuthorizeResponse>() {});

        return responseEntity.getBody();
    }

    private void getSCBHeader(HttpHeaders headers) {
        headers.add("resourceOwnerId", this.resourceOwnerId);
        headers.add("requestUId", UUID.randomUUID().toString());
        headers.add("Content-Type", "application/json");
    }

    public SCBGenerateQR30Response postSCBCreateQR30(String accessToken, float amount) throws JsonProcessingException {
        HttpHeaders headers = new HttpHeaders();
        headers.add("authorization", "Bearer " + accessToken);
        getSCBHeader(headers);

        SCBGenerateQR30Request request = new SCBGenerateQR30Request();
        request.setAmount(amount);

        HttpEntity<String> requestEntity = new HttpEntity<String>(Constants.OBJECT_MAPPER.writeValueAsString(request), headers);

        ResponseEntity<SCBGenerateQR30Response> responseEntity = restTemplate.exchange(
                SCB_URL + "/v1/payment/qrcode/create",
                HttpMethod.POST,
                requestEntity,
                new ParameterizedTypeReference<SCBGenerateQR30Response>() {});

        return responseEntity.getBody();
    }

    public SlipVerificationResponse getVerifySlip(String accessToken, String transactionId, String sendingBank) throws JsonProcessingException {
        HttpHeaders headers = new HttpHeaders();
        headers.add("authorization", "Bearer " + accessToken);
        getSCBHeader(headers);

        Map<String, Object> params = new HashMap<>(1);
        params.put("sendingBank", sendingBank);

        HttpEntity<String> requestEntity = new HttpEntity<String>(null, headers);

        String callingUrl = SCB_URL + "/v1/payment/billpayment/transactions/" + transactionId;
        if (StringUtils.hasText(sendingBank)) {
            callingUrl += "?sendingBank=" + sendingBank;
        }

        ResponseEntity<SlipVerificationResponse> responseEntity = restTemplate.exchange(
                callingUrl,
                HttpMethod.GET,
                requestEntity,
                new ParameterizedTypeReference<SlipVerificationResponse>() {},
                params);

        return responseEntity.getBody();
    }
}
