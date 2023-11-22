package com.ew.gerocomium.common.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum AuditEnum {
    PASS("通过"),
    NO_PASS("不通过"),
    STAY_AUDIT("待审核"),
    NOT_PASS("未通过"),
    HAVE_PASS("已通过"),
    ;
    private final String status;
}
