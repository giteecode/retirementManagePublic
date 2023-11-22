package com.ew.gerocomium.service;

import com.ew.gerocomium.dao.base.Result;
import com.ew.gerocomium.dao.query.*;

public interface OutwardService {
    /**
     * 分页查询外出登记
     *
     * @param pageOutwardByKeyQuery
     * @return
     */
    Result pageOutwardByKey(PageOutwardByKeyQuery pageOutwardByKeyQuery);

    /**
     * 分页搜索老人
     * @param query
     * @return
     */
    Result pageSearchElderByKey(PageSearchElderByKeyQuery query);

    /**
     * 分页搜索护工
     *
     * @param pageSearchStaffByKeyQuery
     * @return
     */
    Result pageSearchStaffByKey(PageSearchStaffByKeyQuery pageSearchStaffByKeyQuery);

    /**
     * 获取护工列表
     *
     * @return
     */
    Result listOutwardStaff();

    /**
     * 分页获取紧急联系人
     *
     * @param pageSearchEmergencyContactQuery
     * @return
     */
    Result pageEmergencyContact(PageSearchEmergencyContactQuery pageSearchEmergencyContactQuery);

    /**
     * 获取紧急联系人列表
     *
     * @return
     */
    Result listContactByElderId(Long elderId);

    /**
     * 新增外出登记
     *
     * @param addOutwardQuery
     * @return
     */
    Result addOutward(AddOutwardQuery addOutwardQuery);

    /**
     * 根据编号获取外出登记
     *
     * @param outwardId
     * @return
     */
    Result getOutwardById(Long outwardId);

    /**
     * 延期返回
     *
     * @param delayReturnQuery
     * @return
     */
    Result delayReturn(DelayReturnQuery delayReturnQuery);

    /**
     * 登记返回
     *
     * @param recordReturnQuery
     * @return
     */
    Result recordReturn(RecordReturnQuery recordReturnQuery);

    /**
     * 删除外出登记
     *
     * @param outwardId
     * @return
     */
    Result deleteOutward(Long outwardId);
}
