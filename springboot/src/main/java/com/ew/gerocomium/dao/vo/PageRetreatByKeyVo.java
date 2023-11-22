package com.ew.gerocomium.dao.vo;

import com.ew.gerocomium.dao.base.Rank;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
@ApiModel(value = "分页查询预定响应实体")
public class PageRetreatByKeyVo extends Rank {
    @ApiModelProperty(value = "申请编号", example = "1")
    private Long applyId;
    @ApiModelProperty(value = "老人编号", example = "1")
    private Long elderId;
    @ApiModelProperty(value = "老人姓名", example = "张三")
    private String elderName;
    @ApiModelProperty(value = "老人性别", example = "男")
    private String elderSex;
    @ApiModelProperty(value = "身份证号", example = "null")
    private String idNum;
    @ApiModelProperty(value = "床位名称", example = "爱心楼-1层-1房-1床")
    private String bedName;
    @ApiModelProperty(value = "审核状态", example = "待审核")
    private String applyFlag;
}
