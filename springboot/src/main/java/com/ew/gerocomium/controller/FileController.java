package com.ew.gerocomium.controller;

import com.ew.gerocomium.common.constant.Constant;
import com.ew.gerocomium.dao.base.Result;
import com.ew.gerocomium.service.FileService;
import io.swagger.annotations.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;

@Api(tags = "文件上传")
@RestController
@RequestMapping("/file")
public class FileController {
    @Resource
    private FileService fileService;

    @PostMapping("/uploadImg")
    @ApiOperation(value = "单文件上传-图片", notes = Constant.DEVELOPER + Constant.EMPEROR_WEN)
    public Result uploadImg(@ApiParam(value = "图片文件", required = true) @RequestBody MultipartFile file,
                            @ApiParam(value = "接口访问请求头", required = true) @RequestHeader String token) {
        return fileService.save(file, Constant.STR_UPLOAD_IMG);
    }

    @PostMapping("/uploadFile")
    @ApiOperation(value = "单文件上传-附件", notes = Constant.DEVELOPER + Constant.EMPEROR_WEN)
    public Result uploadFile(@ApiParam(value = "附件文件", required = true) @RequestBody MultipartFile file,
                             @ApiParam(value = "接口访问请求头", required = true) @RequestHeader String token) {
        return fileService.save(file, Constant.STR_UPLOAD_FILE);
    }
}
