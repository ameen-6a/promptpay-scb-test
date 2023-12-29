package org.qr.models.response;

import lombok.Data;
import org.qr.models.BaseStatus;

@Data
public class SCBGenerateQR30Response {
    private BaseStatus status;
    private QR30Information data;
}
