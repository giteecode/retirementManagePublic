package com.ew.gerocomium.dao.base;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
@ApiModel(value = "获取楼栋列表响应实体")
public class BuildingVo {
    @ApiModelProperty(value = "楼栋编号", example = "1")
    private Long id;
    @ApiModelProperty(value = "楼栋名称", example = "爱心楼")
    private String name;
    @ApiModelProperty(value = "楼层数量", example = "5")
    private Integer floorNum;
    @ApiModelProperty(value = "楼层列表", example = "{}")
    private List<BuildingItem> floorList;

    @Data
    @ApiModel(value = "获取楼层列表响应实体")
    public static class BuildingItem {
        @ApiModelProperty(value = "楼层编号", example = "1")
        private Long id;
        @ApiModelProperty(value = "楼层名称", example = "爱心楼-1层")
        private String name;
        @ApiModelProperty(value = "房间数量", example = "5")
        private Integer roomNum;
        @ApiModelProperty(value = "房间列表", example = "{}")
        private List<FloorItem> roomList;

        @Data
        @ApiModel(value = "获取房间列表响应实体")
        public static class FloorItem {
            @ApiModelProperty(value = "房间编号", example = "1")
            private Long id;
            @ApiModelProperty(value = "房间名称", example = "爱心楼-1层-1房")
            private String name;
            @ApiModelProperty(value = "床位数量", example = "5")
            private Integer bedNum;
            @ApiModelProperty(value = "床位列表", example = "{}")
            private List<RoomItem> bedList;

            @Data
            @ApiModel(value = "获取床位列表响应实体")
            public static class RoomItem {
                @ApiModelProperty(value = "床位编号", example = "1")
                private Long id;
                @ApiModelProperty(value = "床位名称", example = "爱心楼-1层-1房-1床")
                private String name;
                @ApiModelProperty(value = "床位状态", example = "空闲")
                private String bedFlag;
                @ApiModelProperty(value = "所住老人", example = "张三")
                private String elderName;
                @ApiModelProperty(value = "老人性别", example = "男")
                private String sex;
                @ApiModelProperty(value = "老人年龄", example = "70")
                private Integer age;
            }
        }
    }
}
