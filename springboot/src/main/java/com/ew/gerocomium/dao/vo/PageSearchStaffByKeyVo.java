package com.ew.gerocomium.dao.vo;

import com.ew.gerocomium.dao.base.Rank;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
@ApiModel(value = "分页搜索员工响应实体")
public class PageSearchStaffByKeyVo extends Rank {
    @ApiModelProperty(value = "id", example = "1")
    private Long id;
    @ApiModelProperty(value = "姓名", example = "张三")
    private String name;
    @ApiModelProperty(value = "性别", example = "男")
    private String sex;
    @ApiModelProperty(value = "电话", example = "13546574657")
    private String phone;
}
