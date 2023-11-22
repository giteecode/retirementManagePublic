package com.ew.gerocomium.common.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum CodeEnum {
    SUCCESS(200, "操作成功"),
    ERROR(500, "操作失败"),
    ;
    private final Integer code;
    private final String msg;
}
