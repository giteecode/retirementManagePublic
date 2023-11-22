package com.ew.gerocomium.dao.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "合同过期紧急联系人响应实体")
public class ExpireContractEmergencyContactVo {
    @ApiModelProperty(value = "老人编号", example = "1")
    private Long elderId;
    @ApiModelProperty(value = "紧急联系人邮箱", example = "458670589@qq.com")
    private String email;
}
