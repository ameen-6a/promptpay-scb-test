package org.qr.models.response.user;

import lombok.Data;

@Data
public class UserDetail {
    private String displayName;
    private String name;
    private Proxy proxy;
    private Account account;
}
