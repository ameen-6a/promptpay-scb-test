package org.qr.models.request;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class SCBAuthorizeRequest {
    private String applicationKey;
    private String applicationSecret;

}
