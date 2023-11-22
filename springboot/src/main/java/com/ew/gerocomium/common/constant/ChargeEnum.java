package com.ew.gerocomium.common.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ChargeEnum {
    ONCE("按次"),
    MONTH("按月"),
    ALL("所有"),
    ;
    private final String method;
}
