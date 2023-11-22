package com.ew.gerocomium.dao.query;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
@ApiModel(value = "操作活动请求实体")
public class OperateActiveQuery {
    @ApiModelProperty(value = "id", required = false, example = "1")
    private Long id;
    @ApiModelProperty(value = "活动类型编号", required = true, example = "1")
    private String typeId;
    @ApiModelProperty(value = "活动主题", required = true, example = "关爱老人")
    private String theme;
    @ApiModelProperty(value = "活动名称", required = true, example = "文艺汇演")
    private String name;
    @ApiModelProperty(value = "活动内容", required = true, example = "文艺汇演内容")
    private String content;
    @ApiModelProperty(value = "活动地点", required = true, example = "礼堂")
    private String address;
    @ApiModelProperty(value = "组织者姓名", required = true, example = "张三")
    private String organizer;
    @ApiModelProperty(value = "组织者电话", required = true, example = "13546574657")
    private String phone;
    @ApiModelProperty(value = "活动日期", required = true, example = "2022-12-13")
    private String activeDate;
    @ApiModelProperty(value = "活动图片", required = true, example = "url")
    private String activePicture;
    @ApiModelProperty(value = "参加活动老人编号列表", required = true, example = "{}")
    private List<Long> elderIdList;
}
