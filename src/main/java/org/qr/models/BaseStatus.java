package org.qr.models;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain = true)
public class BaseStatus {
    @NonNull
    private String code;
    @NonNull
    private String description;
}
