package com.ew.gerocomium.common.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum YesNoEnum {
    YES("是", "Y"),
    NO("否", "N"),
    ;
    private final String name;
    private final String code;
}
