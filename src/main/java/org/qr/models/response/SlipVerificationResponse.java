package org.qr.models.response;

import lombok.Data;
import lombok.experimental.Accessors;
import org.qr.models.BaseStatus;

@Data
@Accessors(chain = true)
public class SlipVerificationResponse {
    private BaseStatus status;
    private SlipVerificationData data;
}
