package com.ew.gerocomium.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.ObjUtil;
import com.ew.gerocomium.common.constant.CodeEnum;
import com.ew.gerocomium.common.constant.ExceptionEnum;
import com.ew.gerocomium.common.util.AssertUtil;
import com.ew.gerocomium.common.util.ExcelUtil;
import com.ew.gerocomium.common.util.PageUtil;
import com.ew.gerocomium.dao.base.DropDown;
import com.ew.gerocomium.dao.base.PageResult;
import com.ew.gerocomium.dao.base.Result;
import com.ew.gerocomium.dao.mapper.BedMapper;
import com.ew.gerocomium.dao.mapper.ElderMapper;
import com.ew.gerocomium.dao.po.Elder;
import com.ew.gerocomium.dao.query.PageElderByKeyQuery;
import com.ew.gerocomium.dao.vo.GetElderRecordByIdVo;
import com.ew.gerocomium.dao.vo.PageElderByKeyVo;
import com.ew.gerocomium.service.CheckContractService;
import com.ew.gerocomium.service.ElderRecordService;
import com.ew.gerocomium.service.common.CateringSetFunc;
import com.ew.gerocomium.service.common.EmergencyContactFunc;
import com.ew.gerocomium.service.common.NurseGradeFunc;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class ElderRecordServiceImpl implements ElderRecordService {
    @Resource
    private ElderMapper elderMapper;
    @Resource
    private PageUtil pageUtil;
    @Resource
    private BedMapper bedMapper;
    @Resource
    private EmergencyContactFunc emergencyContactFunc;
    @Resource
    private NurseGradeFunc nurseGradeFunc;
    @Resource
    private CateringSetFunc cateringSetFunc;
    @Resource
    private ExcelUtil excelUtil;

    @Override
    public Result exportExcel() throws IOException {
        // 获取长者列表
        List<PageElderByKeyVo> pageElderByKeyVoList = elderMapper.listElderByKey(new PageElderByKeyQuery());
        // 导出excel并返回requestPath
        String requestPath = excelUtil.exportExcel(pageElderByKeyVoList, PageElderByKeyVo.class);
        return Result.success(CodeEnum.SUCCESS.getMsg(), requestPath);
    }

    @Override
    public Result pageElderByKey(PageElderByKeyQuery query) {
        // 根据关键字获取长者列表
        List<PageElderByKeyVo> pageElderByKeyVoList = elderMapper.listElderByKey(query);
        // 封装返回数据
        PageResult<PageElderByKeyVo> pageResult = pageUtil.packPageResultData(pageElderByKeyVoList, query.getPageNum(), query.getPageSize());
        return Result.success(pageResult);
    }

    @Override
    public Result getElderRecordById(Long elderId) {
        // 根据编号获取老人
        Elder elder = elderMapper.selectById(elderId);
        // 验证老人是否存在
        AssertUtil.notNull(elder, ExceptionEnum.DATA_NOT_EXIST);
        // 实体转换
        GetElderRecordByIdVo getElderRecordByIdVo = BeanUtil.toBean(elder, GetElderRecordByIdVo.class);
        // 根据老人编号获取紧急联系人
        getElderRecordByIdVo.setElderEmergencyContactByIdVoList(emergencyContactFunc.listEmgencyContactByElderId(elderId));
        // 根据编号获取护理等级
        if (ObjUtil.isNotEmpty(elder.getNursingGradeId())) {
            getElderRecordByIdVo.setElderNurseGradeByIdVo(nurseGradeFunc.getNurseGradeById(elder.getNursingGradeId()));
        }
        // 根据编号获取餐饮套餐
        if (ObjUtil.isNotEmpty(elder.getCateringSetId())) {
            getElderRecordByIdVo.setElderCateringSetByIdVo(cateringSetFunc.getCateringSetById(elder.getCateringSetId()));
        }
        // 根据编号获取床位
        if (ObjUtil.isNotEmpty(elder.getBedId())) {
            getElderRecordByIdVo.setElderBedByIdVo(bedMapper.getBedById(elder.getBedId()));
        }
        return Result.success(getElderRecordByIdVo);
    }
}
