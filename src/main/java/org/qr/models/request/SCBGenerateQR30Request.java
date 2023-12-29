package org.qr.models.request;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain = true)
public class SCBGenerateQR30Request {
    private String qrType = "PP";
    private String ppType = "BILLERID";
    private String ppId = "606446088637072";
    private float amount;
    private String ref1 = "PORTALSANDBOXREF1";
    private String ref2 = "PORTALSANDBOXREF1";
    private String ref3 = "YUA";
}
