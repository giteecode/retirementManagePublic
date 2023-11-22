package com.ew.gerocomium.dao.vo;

import com.ew.gerocomium.dao.query.OperateActiveQuery;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@ApiModel(value = "根据活动编号获取活动响应实体")
public class GetActiveByIdVo extends OperateActiveQuery {
    @ApiModelProperty(value = "参加活动老人列表", example = "{}")
    private List<ParticipateElderVo> participateElderVoList;

    @Data
    @ApiModel(value = "参加活动老人响应实体")
    public static class ParticipateElderVo {
        @ApiModelProperty(value = "id", example = "1")
        private Long id;
        @ApiModelProperty(value = "老人姓名", example = "张三")
        private String name;
        @ApiModelProperty(value = "老人电话", example = "13546574756")
        private String phone;
    }
}
