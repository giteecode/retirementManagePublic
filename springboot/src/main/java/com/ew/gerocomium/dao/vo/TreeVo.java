package com.ew.gerocomium.dao.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
@ApiModel(value = "楼栋树响应实体")
public class TreeVo {
    @ApiModelProperty(value = "编号", example = "1")
    private Long id;
    @ApiModelProperty(value = "名称", example = "楼栋2-1层-1房-2床")
    private String name;
    @ApiModelProperty(value = "层级", example = "1")
    private Integer level;
    @ApiModelProperty(value = "有序唯一标识", example = "1")
    private Integer onlyMark;
    @ApiModelProperty(value = "楼栋编号", example = "1")
    private Long buildId;
    @ApiModelProperty(value = "楼层限制", example = "1")
    private Integer floorLimit;
    @ApiModelProperty(value = "楼层编号", example = "1")
    private Long floorId;
    @ApiModelProperty(value = "房间限制", example = "1")
    private Integer roomLimit;
    @ApiModelProperty(value = "房间编号", example = "1")
    private Long roomId;
    @ApiModelProperty(value = "床位限制", example = "1")
    private Integer bedLimit;
    @ApiModelProperty(value = "子级", example = "null")
    private List<TreeVo> childrenList;
}
