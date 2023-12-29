package org.qr.models.response;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain = true)
public class AuthorizedCredential {
    private String accessToken;
    private String tokenType;
    private double expiresIn;
    private double expiresAt;
}
