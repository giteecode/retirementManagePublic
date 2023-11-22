package com.ew.gerocomium.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.text.StrPool;
import com.ew.gerocomium.common.config.file.FileTempPath;
import com.ew.gerocomium.common.config.file.FileUploadConfigPropertity;
import com.ew.gerocomium.common.constant.YesNoEnum;
import com.ew.gerocomium.dao.base.Result;
import com.ew.gerocomium.dao.mapper.BaseAttachmentMapper;
import com.ew.gerocomium.dao.po.BaseAttachment;
import com.ew.gerocomium.dao.vo.FileInfoVo;
import com.ew.gerocomium.service.FileService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;

@Service
public class FileServiceImpl implements FileService {
    @Resource
    private FileTempPath fileTempPath;

    @Resource
    private BaseAttachmentMapper baseAttachmentMapper;

    @Resource
    private FileUploadConfigPropertity fileUploadConfigPropertity;

    @Override
    public Result save(MultipartFile file, String module) {
        long size = file.getSize();
        String originalFilename = file.getOriginalFilename();
        File save = fileTempPath.saveCommFile(file, module);
        String filePath = save.getAbsolutePath();
        String downloadUrl = fileUploadConfigPropertity.getUploadHead() + filePath
                .replace(fileTempPath.getRootPath(), "")
                .replace(StrPool.BACKSLASH, StrPool.SLASH);

        BaseAttachment baseAttachment = new BaseAttachment();
        baseAttachment.setName(save.getName());
        baseAttachment.setRealName(originalFilename);
        baseAttachment.setPath(save.getAbsolutePath());
        baseAttachment.setUrl(downloadUrl);
        baseAttachment.setSuff(FileUtil.getSuffix(save));
        baseAttachment.setSize(size);
        baseAttachment.setDelFlag(YesNoEnum.NO.getCode());
        baseAttachmentMapper.insert(baseAttachment);

        return Result.success(BeanUtil.toBean(baseAttachment, FileInfoVo.class));
    }
}
