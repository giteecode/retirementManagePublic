package com.ew.gerocomium.common.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum RecipientEnum {
    ELDER("老人"),
    STAFF("员工"),
    ;
    private final String type;
}
