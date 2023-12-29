package org.qr.models.response;

import lombok.Data;
import lombok.experimental.Accessors;
import org.qr.models.response.user.UserDetail;

@Data
@Accessors(chain = true)
public class SlipVerificationData {
    private String transRef;
    private String sendingBank;
    private String receivingBank;
    private String transDate;
    private String transTime;
    private UserDetail sender;
    private UserDetail receiver;
    private String amount;
    private String paidLocalAmount;
    private String paidLocalCurrency;
    private String countryCode;
    private String ref1;
    private String ref2;
    private String ref3;
}
