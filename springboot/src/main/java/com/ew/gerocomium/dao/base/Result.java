package com.ew.gerocomium.dao.base;

import com.ew.gerocomium.common.constant.CodeEnum;
import com.ew.gerocomium.common.constant.ExceptionEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "请求响应实体")
public class Result {
    @ApiModelProperty(value = "响应码", required = true, example = "200")
    private Integer code;
    @ApiModelProperty(value = "响应消息", required = true, example = "成功")
    private String msg;
    @ApiModelProperty(value = "响应数据", required = true, example = "null")
    private Object data;

    /**
     * 成功
     *
     * @return
     */
    public static Result success() {
        return resultData(CodeEnum.SUCCESS.getCode(), CodeEnum.SUCCESS.getMsg(), null);
    }

    public static Result success(String msg) {
        return resultData(CodeEnum.SUCCESS.getCode(), msg, null);
    }

    public static Result success(Object data) {
        return resultData(CodeEnum.SUCCESS.getCode(), CodeEnum.SUCCESS.getMsg(), data);
    }

    public static Result success(String msg, Object data) {
        return resultData(CodeEnum.SUCCESS.getCode(), msg, data);
    }

    /**
     * 失败
     *
     * @return
     */
    public static Result error(Integer code, String msg) {
        return resultData(code, msg, null);
    }

    public static Result error(Integer code, String msg, Object data) {
        return resultData(code, msg, data);
    }

    public static Result error(ExceptionEnum exceptionEnum) {
        return resultData(exceptionEnum.getCode(), exceptionEnum.getMsg(), null);
    }

    public static Result error(ExceptionEnum exceptionEnum, Object data) {
        return resultData(exceptionEnum.getCode(), exceptionEnum.getMsg(), data);
    }

    private static Result resultData(Integer code, String msg, Object data) {
        Result result = new Result();
        result.setCode(code);
        result.setMsg(msg);
        result.setData(data);
        return result;
    }

}
