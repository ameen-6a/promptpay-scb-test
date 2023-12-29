package org.qr.models.response;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class QR30Response {
    private String Base64QR30String;
}
