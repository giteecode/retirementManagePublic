package com.ew.gerocomium.dao.query;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "根据咨询人编号和老人编号获取咨询信息请求实体")
public class GetConsultByConsultIdAndElderIdQuery {
    @ApiModelProperty(value = "咨询人编号", required = true, example = "1")
    private Long consultId;
    @ApiModelProperty(value = "老人编号", required = true, example = "1")
    private Long elderId;
}
