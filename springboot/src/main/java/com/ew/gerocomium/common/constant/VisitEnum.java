package com.ew.gerocomium.common.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum VisitEnum {
    STAY_LEAVE("待离开"),
    ALREADY_LEAVE("已离开"),
    ;
    private final String status;
}
