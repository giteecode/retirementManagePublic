package com.ew.gerocomium.dao.vo;

import com.ew.gerocomium.dao.base.Rank;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
@ApiModel(value = "分页查询入住签约请求实体")
public class PageCheckContractByKeyVo extends Rank {
    @ApiModelProperty(value = "id", example = "1")
    private Long id;
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
    @ApiModelProperty(value = "入住状态", example = "入住")
    private String checkFlag;
}
