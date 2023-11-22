package com.ew.gerocomium.common.util;

import com.ew.gerocomium.dao.po.Elder;

import java.lang.reflect.*;

public class JavaToTsConverter {
    
    public String convert(Class<?> clazz) {
        StringBuilder sb = new StringBuilder();
        
        sb.append("interface ").append(clazz.getSimpleName()).append(" {\n");
        
        // 处理字段
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            sb.append("\t").append(field.getName()).append(": ").append(getTypeScriptType(field.getType())).append(";\n");
        }
        
        // 处理方法
        Method[] methods = clazz.getDeclaredMethods();
        for (Method method : methods) {
            if (Modifier.isPublic(method.getModifiers())) {
                sb.append("\t").append(method.getName()).append("(");
                
                // 处理参数
                Class<?>[] paramTypes = method.getParameterTypes();
                for (int i = 0; i < paramTypes.length; i++) {
                    if (i > 0) {
                        sb.append(", ");
                    }
                    sb.append("arg").append(i).append(": ").append(getTypeScriptType(paramTypes[i]));
                }
                
                sb.append("): ").append(getTypeScriptType(method.getReturnType())).append(";\n");
            }
        }
        
        sb.append("}");
        
        return sb.toString();
    }
    
    private String getTypeScriptType(Class<?> type) {
        if (type == boolean.class || type == Boolean.class) {
            return "boolean";
        } else if (type == byte.class || type == Byte.class ||
                   type == short.class || type == Short.class ||
                   type == int.class || type == Integer.class ||
                   type == long.class || type == Long.class ||
                   type == float.class || type == Float.class ||
                   type == double.class || type == Double.class) {
            return "number";
        } else if (type == String.class || type == char.class || type == Character.class) {
            return "string";
        } else {
            return type.getSimpleName();
        }
    }

    public static void main(String[] args) {
        JavaToTsConverter converter = new JavaToTsConverter();
        String tsCode = converter.convert(Elder.class);
        System.out.println(tsCode);
    }
}
