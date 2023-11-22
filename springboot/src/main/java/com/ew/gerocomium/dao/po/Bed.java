package com.ew.gerocomium.dao.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.Version;
import com.baomidou.mybatisplus.annotation.TableId;
import com.ew.gerocomium.dao.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 床位表
 * </p>
 *
 * @author EmperorWen
 * @since 2022-12-31
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class Bed extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 房间编号
     */
    private Long roomId;

    /**
     * 床位名称
     */
    private String name;

    /**
     * 床位状态(空闲/预定/入住/退住审核)
     */
    private String bedFlag;

    /**
     * 删除状态（Y/N）
     */
    private String delFlag;


}
