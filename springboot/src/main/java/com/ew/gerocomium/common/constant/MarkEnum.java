package com.ew.gerocomium.common.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum MarkEnum {
    BUILDING("楼栋"),
    FLOOR("楼层"),
    ROOM("房间"),
    ;
    private final String mark;
}
