package com.ew.gerocomium.dao.vo;

import com.ew.gerocomium.dao.base.Rank;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

@EqualsAndHashCode(callSuper = true)
@Data
@ApiModel(value = "分页查询沟通记录响应实体")
public class PageCommunicationRecordVo extends Rank {
    @ApiModelProperty(value = "id", example = "1")
    private Long id;
    @ApiModelProperty(value = "记录时间", example = "2022-12-13")
    private Date recordDate;
    @ApiModelProperty(value = "沟通记录", example = "测试记录")
    private String communicationRecord;
}
