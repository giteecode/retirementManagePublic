package com.ew.gerocomium.dao.vo;

import com.ew.gerocomium.dao.query.OperateCheckContractQuery;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
@ApiModel(value = "根据编号获取长者档案响应实体")
public class GetElderRecordByIdVo {
    @ApiModelProperty(value = "姓名", example = "张三")
    private String name;
    @ApiModelProperty(value = "身份证号", example = "null")
    private String idNum;
    @ApiModelProperty(value = "年龄", example = "67")
    private Integer age;
    @ApiModelProperty(value = "性别", example = "男")
    private String sex;
    @ApiModelProperty(value = "电话", example = "13546574657")
    private String phone;
    @ApiModelProperty(value = "地址", example = "四川南充")
    private String address;
    @ApiModelProperty(value = "长者紧急联系人", example = "{}")
    private List<OperateCheckContractQuery.OperateEmergencyContactQuery> elderEmergencyContactByIdVoList;
    @ApiModelProperty(value = "长者护理等级", example = "null")
    private GetNurseGradeByIdVo elderNurseGradeByIdVo;
    @ApiModelProperty(value = "长者餐饮套餐", example = "null")
    private GetCateringSetByIdVo elderCateringSetByIdVo;
    @ApiModelProperty(value = "长者床位", example = "null")
    private GetBedByIdVo elderBedByIdVo;
}
