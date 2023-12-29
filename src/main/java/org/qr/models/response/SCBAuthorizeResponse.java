package org.qr.models.response;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.qr.models.BaseStatus;

@Getter
@Setter
@Accessors(chain = true)
public class SCBAuthorizeResponse {
    private BaseStatus status;
    private AuthorizedCredential data;
}
