package com.ew.gerocomium.common.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum BedEnum {
    IDLE("空闲"),
    RESERVE("预定"),
    ENTER("入住"),
    EXIT_AUDIT("退住审核"),
    ;
    private final String status;
}
