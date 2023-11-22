package com.ew.gerocomium.dao.query;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
@ApiModel(value = "操作入住签约请求实体")
public class OperateCheckContractQuery {
    @ApiModelProperty(value = "id", required = false, example = "1")
    private Long id;
    @ApiModelProperty(value = "护理等级编号", required = true, example = "1")
    private Long nursingGradeId;
    @ApiModelProperty(value = "餐饮套餐编号", required = true, example = "1")
    private Long cateringSetId;
    @ApiModelProperty(value = "床位编号", required = true, example = "1")
    private Long bedId;
    @ApiModelProperty(value = "姓名", required = true, example = "张三")
    private String name;
    @ApiModelProperty(value = "身份证号", required = true, example = "null")
    private String idNum;
    @ApiModelProperty(value = "年龄", required = true, example = "67")
    private Integer age;
    @ApiModelProperty(value = "性别", required = true, example = "男")
    private String sex;
    @ApiModelProperty(value = "电话", required = true, example = "13546574657")
    private String phone;
    @ApiModelProperty(value = "地址", required = true, example = "四川南充")
    private String address;
    @ApiModelProperty(value = "营销人员编号", required = true, example = "1")
    private Long staffId;
    @ApiModelProperty(value = "合同签订日期", required = true, example = "2022-12-23")
    private String signDate;
    @ApiModelProperty(value = "合同开始日期", required = true, example = "2022-12-23")
    private String startDate;
    @ApiModelProperty(value = "合同结束日期", required = true, example = "2022-12-24")
    private String endDate;
    @ApiModelProperty(value = "紧急联系人", required = true, example = "[{'name':'张三','phone':'13547564867','email':'3498579@qq.com','relation':'父子','receiveFlag':'Y'}]")
    private List<OperateEmergencyContactQuery> operateEmergencyContactQueryList;

    @Data
    @ApiModel(value = "操作紧急联系人请求实体")
    public static class OperateEmergencyContactQuery {
        @ApiModelProperty(value = "紧急联系人姓名", required = true, example = "张三")
        private String name;
        @ApiModelProperty(value = "紧急联系人电话", required = true, example = "13546574657")
        private String phone;
        @ApiModelProperty(value = "紧急联系人邮箱", required = true, example = "475689754@qq.com")
        private String email;
        @ApiModelProperty(value = "与老人关系", required = true, example = "父子")
        private String relation;
        @ApiModelProperty(value = "是否接收消息", required = true, example = "Y/N")
        private String receiveFlag;
    }
}
