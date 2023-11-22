package com.ew.gerocomium.service;

import com.ew.gerocomium.dao.base.Result;
import com.ew.gerocomium.dao.query.PageElderByKeyQuery;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface ElderRecordService {
    /**
     * 导出excel
     */
    Result exportExcel() throws IOException;

    /**
     * 分页查询长者
     *
     * @param pageElderByKeyQuery
     * @return
     */
    Result pageElderByKey(PageElderByKeyQuery pageElderByKeyQuery);

    /**
     * 根据编号获取长者档案
     *
     * @param elderId
     * @return
     */
    Result getElderRecordById(Long elderId);
}
