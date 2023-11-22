package com.ew.gerocomium.dao.vo;

import com.ew.gerocomium.dao.base.Rank;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
@ApiModel(value = "分页查询员工响应实体")
public class PageStaffByKeyVo extends Rank {
    @ApiModelProperty(value = "id", example = "1")
    private Long id;
    @ApiModelProperty(value = "员工姓名", example = "张三")
    private String name;
    @ApiModelProperty(value = "角色名称", example = "销售管理员")
    private String roleName;
    @ApiModelProperty(value = "身份证号", example = "null")
    private String idNum;
    @ApiModelProperty(value = "员工性别", example = "男")
    private String sex;
    @ApiModelProperty(value = "员工电话", example = "13546574657")
    private String phone;
    @ApiModelProperty(value = "员工邮箱", example = "934869348@qq.com")
    private String email;
    @ApiModelProperty(value = "离职状态", example = "N")
    private String leaveFlag;
}
