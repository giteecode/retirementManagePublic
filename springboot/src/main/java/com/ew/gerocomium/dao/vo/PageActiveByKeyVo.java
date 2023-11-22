package com.ew.gerocomium.dao.vo;

import com.ew.gerocomium.dao.base.Rank;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
@ApiModel(value = "分页查询活动分类响应实体")
public class PageActiveByKeyVo extends Rank {
    @ApiModelProperty(value = "id", example = "1")
    private Long id;
    @ApiModelProperty(value = "活动分类名称", example = "文艺演出")
    private String typeName;
    @ApiModelProperty(value = "活动主题", example = "关爱老人")
    private String theme;
    @ApiModelProperty(value = "活动名称", example = "文艺汇演")
    private String name;
    @ApiModelProperty(value = "活动内容", example = "文艺汇演内容")
    private String content;
    @ApiModelProperty(value = "活动地点", example = "礼堂")
    private String address;
    @ApiModelProperty(value = "组织者姓名", example = "张三")
    private String organizer;
    @ApiModelProperty(value = "组织者电话", example = "13546574657")
    private String phone;
    @ApiModelProperty(value = "活动日期", example = "2022-12-13")
    private String activeDate;
    @ApiModelProperty(value = "活动图片", example = "url")
    private String activePicture;
}
