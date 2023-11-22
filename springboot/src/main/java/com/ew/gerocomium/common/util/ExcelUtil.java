package com.ew.gerocomium.common.util;

import cn.hutool.core.util.ObjUtil;
import com.ew.gerocomium.common.config.file.FileUploadConfigPropertity;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFColor;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;

@Component
public class ExcelUtil {
    @Resource
    private FileUploadConfigPropertity fileUploadConfigPropertity;

    /**
     * @param dataList 导出数据列表
     * @param aClass   反射对象
     * @return
     * @throws IOException
     */
    public String exportExcel(List<?> dataList, Class<?> aClass) throws IOException {
        // 生成文件名
        UUID fileName = UUID.randomUUID();
        String filePath = fileUploadConfigPropertity.getWindows() + "/download/" + fileName + ".xlsx";
        String requestPath = fileUploadConfigPropertity.getLocalHead() + "/download/" + fileName + ".xlsx";

        // 获取表头以及getter名称
        List<Test> headerNameList = new ExcelUtil().getHeaderNameList(aClass);

        // 生成excel
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Sheet1");

        // 表头样式
        XSSFCellStyle headerStyle = (XSSFCellStyle) workbook.createCellStyle();
        // 设置背景颜色
        headerStyle.setFillForegroundColor(new XSSFColor(new java.awt.Color(177, 179, 184)));
        headerStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        // 设置水平居中
        headerStyle.setAlignment(HorizontalAlignment.CENTER);
        // 设置垂直居中
        headerStyle.setVerticalAlignment(VerticalAlignment.CENTER);
        // 设置背景颜色为灰色
        headerStyle.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());

        // 表头
        AtomicInteger rowNum = new AtomicInteger();
        Row headerRow = sheet.createRow(rowNum.getAndIncrement());
        sheet.setColumnWidth(0, 10 * 256);
        setCell(headerRow, 0, "序号", headerStyle, true);
        int headI = 1;
        for (Test test : headerNameList) {
            setCell(headerRow, headI, test.getHeadName(), headerStyle, true);
            headI++;
        }

        // 内容
        int contentI = 1;
        for (Object item : dataList) {
            Row row = sheet.createRow(rowNum.getAndIncrement());
            // 序号
            setCell(row, 0, String.valueOf(contentI), headerStyle, false);
            contentI++;
            try {
//                直接通过该方法获取整个对象的值
//                Field[] declaredFields = item.getClass().getDeclaredFields();
//                for (Field declaredField : declaredFields) {
//                    declaredField.setAccessible(true);
//                    declaredField.getName();
//                    Object o = declaredField.get(item);
//                    System.out.println(o);
//                }
                int contentJ = 1;
                for (Test test : headerNameList) {
                    // 通过反射对象获取一般方法
                    Method method = aClass.getMethod(test.getGetterName());
                    // 利用反射的invoke方法激活通过反射调取的方法,里面的参数是实例对象和方法参数
                    Object getValue = method.invoke(item);
                    String value = ObjUtil.isNotEmpty(getValue) ? getValue.toString() : "";
                    // 某一行某一格内容
                    setCell(row, contentJ, value, headerStyle, false);
                    if (test.getMaxLength() < value.length()) {
                        test.setMaxLength(value.length() + 10);
                    }
                    contentJ++;
                }
            } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
                e.printStackTrace();
            }
        }

        // 统一设置列宽
        int widthI = 1;
        for (Test test : headerNameList) {
            sheet.setColumnWidth(widthI, test.getMaxLength() * 256);
            widthI++;
        }

        // 生成本地文件流
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        workbook.write(byteArrayOutputStream);
        // 保存到本地
        FileOutputStream fos = new FileOutputStream(filePath);
        byteArrayOutputStream.writeTo(fos);
        fos.close();
        byteArrayOutputStream.close();

        // 完成
        workbook.close();

        return requestPath;
    }

    // 表格设值
    public void setCell(Row row, Integer i, String value, XSSFCellStyle headerStyle, Boolean headFlag) {
        Cell cell = row.createCell(i);
        cell.setCellValue(value);
        cell.getCellStyle().setWrapText(true);
        if (headFlag) {
            cell.setCellStyle(headerStyle);
        }
    }

    // 获取表头
    public List<Test> getHeaderNameList(Class<?> aClass) {
        List<Test> testList = new ArrayList<>();

        Field[] fields = aClass.getDeclaredFields();
        for (Field field : fields) {
            ApiModelProperty apiModelProperty = field.getAnnotation(ApiModelProperty.class);
            if (apiModelProperty != null && !Objects.equals(field.getName(), "id")) {
                String fieldName = field.getName();
                String apiModelPropertyName = apiModelProperty.value();

                String getMethodName = "get" + Character.toUpperCase(fieldName.charAt(0)) + fieldName.substring(1);
                Test test = new Test(apiModelPropertyName, getMethodName, 0);
                testList.add(test);
            }
        }

        return testList;
    }

    @Data
    @AllArgsConstructor
    private static class Test {
        /**
         * 表头名称
         */
        private String headName;
        /**
         * get方法
         */
        private String getterName;
        /**
         * 该列值最大长度
         */
        private Integer maxLength;
    }
} 
