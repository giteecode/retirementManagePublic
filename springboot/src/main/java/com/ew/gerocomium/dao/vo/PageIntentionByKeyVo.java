package com.ew.gerocomium.dao.vo;

import com.ew.gerocomium.dao.base.Rank;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@ApiModel(value = "分页查询意向客户响应实体")
public class PageIntentionByKeyVo extends Rank {
    @ApiModelProperty(value = "老人编号", example = "1")
    private Long id;
    @ApiModelProperty(value = "老人姓名", example = "李四")
    private String name;
    @ApiModelProperty(value = "老人电话", example = "13546565678")
    private String phone;
    @ApiModelProperty(value = "性别", example = "男")
    private String sex;
    @ApiModelProperty(value = "年龄", example = "67")
    private Integer age;
    @ApiModelProperty(value = "客户标签编号", example = "1")
    @JsonIgnore
    private String labelIdStr;
    @ApiModelProperty(value = "客户标签", example = "{}")
    private List<LabelVo> labelVoList;

    @Data
    @ApiModel(value = "客户标签响应实体")
    public static class LabelVo {
        @ApiModelProperty(value = "名称", example = "看书")
        private String name;
        @ApiModelProperty(value = "颜色", example = "rgb")
        private String color;
    }
}
