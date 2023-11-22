package com.ew.gerocomium.common.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ConsumeEnum {
    NURSE("护理"),
    DISHES("点餐"),
    ;
    private final String type;
}
