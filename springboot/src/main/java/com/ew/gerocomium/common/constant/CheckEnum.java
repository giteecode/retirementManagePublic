package com.ew.gerocomium.common.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum CheckEnum {
    CONSULT("咨询中"),
    INTENTION("意向跟进"),
    RESERVE("预定"),
    ENTER("入住"),
    EXIT_AUDIT("退住审核"),
    EXIT("已退住"),
    FAILURE("已失效"),
    ;
    private final String status;
}
