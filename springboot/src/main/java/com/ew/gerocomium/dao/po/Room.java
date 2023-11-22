package com.ew.gerocomium.dao.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.Version;
import com.baomidou.mybatisplus.annotation.TableId;
import com.ew.gerocomium.dao.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 房间表
 * </p>
 *
 * @author EmperorWen
 * @since 2022-12-31
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class Room extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 房间类型编号
     */
    private Long typeId;

    /**
     * 楼层编号
     */
    private Long floorId;

    /**
     * 房间名称
     */
    private String name;

    /**
     * 床位数量
     */
    private Integer bedNum;

    /**
     * 删除状态（Y/N）
     */
    private String delFlag;


}
