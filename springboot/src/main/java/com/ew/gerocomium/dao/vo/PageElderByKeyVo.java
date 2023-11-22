package com.ew.gerocomium.dao.vo;

import com.ew.gerocomium.dao.base.Rank;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
@ApiModel(value = "分页查询长者响应实体")
public class PageElderByKeyVo extends Rank {
    @ApiModelProperty(value = "id", example = "1")
    private Long id;
    @ApiModelProperty(value = "床位名称", example = "爱心楼-1层-1房-1床")
    private String bedName;
    @ApiModelProperty(value = "老人姓名", example = "张三")
    private String name;
    @ApiModelProperty(value = "身份证号", example = "null")
    private String idNum;
    @ApiModelProperty(value = "老人年龄", example = "67")
    private Integer age;
    @ApiModelProperty(value = "老人性别", example = "男")
    private String sex;
    @ApiModelProperty(value = "老人电话", example = "13546578765")
    private String phone;
    @ApiModelProperty(value = "老人地址", example = "四川成都")
    private String address;
    @ApiModelProperty(value = "入住状态", example = "预定")
    private String checkFlag;
}
