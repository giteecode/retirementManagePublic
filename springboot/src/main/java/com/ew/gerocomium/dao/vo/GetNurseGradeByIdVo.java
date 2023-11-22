package com.ew.gerocomium.dao.vo;

import com.ew.gerocomium.dao.query.OperateNurseGradeQuery;
import com.ew.gerocomium.dao.query.OperateServiceQuery;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@ApiModel(value = "根据编号查询护理等级响应实体")
public class GetNurseGradeByIdVo extends OperateNurseGradeQuery {
    @ApiModelProperty(value = "护理等级服务列表", example = "{}")
    private List<NurseGradeServiceVo> nurseGradeServiceVoList;

    @ApiModel(value = "根据编号查询护理等级服务响应实体")
    public static class NurseGradeServiceVo extends OperateServiceQuery {
    }
}
