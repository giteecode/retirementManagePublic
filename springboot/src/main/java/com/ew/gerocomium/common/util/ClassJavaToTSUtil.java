package com.ew.gerocomium.common.util;

import cn.hutool.core.util.ObjUtil;
import com.ew.gerocomium.dao.query.*;
import com.ew.gerocomium.dao.vo.PageNurseReserveByKeyVo;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.math.BigDecimal;

public class ClassJavaToTSUtil {
    public static String convertClassToTS(Class<?> clazz, String returnClassName) {
        StringBuilder sb = new StringBuilder();
        sb.append("export interface ");
        sb.append(ObjUtil.isNotEmpty(returnClassName) ? returnClassName : getClassName(clazz));
        sb.append(" {\n");

        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            if (!Modifier.isStatic(field.getModifiers()) && !isGetterOrSetter(field.getName())) {
                sb.append("\t");
                sb.append(field.getName());
                sb.append(": ");
                sb.append(convertTypeToTS(field.getType()));
                sb.append(";\n");
            }
        }
        sb.append("}");

        return sb.toString();
    }

    private static String getClassName(Class<?> clazz) {
        String name = clazz.getSimpleName();
        return name.substring(0, 1).toUpperCase() + name.substring(1);
    }

    private static String convertTypeToTS(Class<?> type) {
        if (type == int.class || type == Integer.class ||
                type == long.class || type == Long.class ||
                type == float.class || type == Float.class ||
                type == double.class || type == Double.class ||
                type == BigDecimal.class) {
            return "number";
        } else if (type == boolean.class || type == Boolean.class) {
            return "boolean";
        } else if (type == String.class) {
            return "string";
        } else {
            return getClassName(type);
        }
    }

    private static boolean isGetterOrSetter(String fieldName) {
        return fieldName.startsWith("get") ||
                fieldName.startsWith("set") ||
                fieldName.startsWith("is");
    }

    public static void main(String[] args) {
        System.out.println(convertClassToTS(PageNurseReserveByKeyVo.class,null));
    }
}
