package com.ew.gerocomium.common.util;

import com.ew.gerocomium.common.config.exception.BusinessRuntimeException;
import com.ew.gerocomium.common.constant.ExceptionEnum;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

import java.util.Collection;

/**
 * 断言工具类
 */
public class AssertUtil {
    public AssertUtil() {
    }

    public static void isTrue(boolean expression, ExceptionEnum type) throws BusinessRuntimeException {
        if (!expression) {
            throw new BusinessRuntimeException(type);
        }
    }

    public static void notTrue(boolean expression, ExceptionEnum type) throws BusinessRuntimeException {
        if (expression) {
            throw new BusinessRuntimeException(type);
        }
    }

    public static void isNull(Object object, ExceptionEnum type) throws BusinessRuntimeException {
        if (object != null) {
            throw new BusinessRuntimeException(type);
        }
    }

    public static void notNull(Object object, ExceptionEnum type) throws BusinessRuntimeException {
        if (object == null) {
            throw new BusinessRuntimeException(type);
        }
    }

    public static void isEmpty(Object[] array, ExceptionEnum type) throws BusinessRuntimeException {
        if (!ObjectUtils.isEmpty(array)) {
            throw new BusinessRuntimeException(type);
        }
    }

    public static void notEmpty(Object[] array, ExceptionEnum type) throws BusinessRuntimeException {
        if (ObjectUtils.isEmpty(array)) {
            throw new BusinessRuntimeException(type);
        }
    }

    public static void isEmpty(Collection<?> collection, ExceptionEnum type) throws BusinessRuntimeException {
        if (!CollectionUtils.isEmpty(collection)) {
            throw new BusinessRuntimeException(type);
        }
    }

    public static void notEmpty(Collection<?> collection, ExceptionEnum type) throws BusinessRuntimeException {
        if (CollectionUtils.isEmpty(collection)) {
            throw new BusinessRuntimeException(type);
        }
    }
}
