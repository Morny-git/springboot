package com.mx.util;


import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.poi.hssf.usermodel.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ExportExcelUtil<T> {
    public static final Log LOG = LogFactory.getLog(ExportExcelUtil.class);

    //执行导出
    public static boolean exportData(ExportExcelUtil ex, HttpServletRequest request, HttpServletResponse response, String sheetTitle, String[] headers, List dataList, String fileName) throws IOException {
        if (ex != null) {
                OutputStream out = com.mx.util.FileUtils.formatFileName(request,response,fileName);
                sheetTitle = sheetTitle.isEmpty() ? "sheet" : sheetTitle;
                ex.exportExcel(sheetTitle, headers, dataList, out, "yyyy-MM-dd");
                out.close();
                LOG.info("导出成功");
                return true;
        } else {
            LOG.info("待导出的对象为 null");
            return false;
        }
    }

    //创建excel
    public void exportExcel(String title, String[] headers,
                            Collection<T> dataList, OutputStream out, String pattern) {
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet(title);
        // 产生表格标题行
        HSSFRow row = sheet.createRow(0);
        createHeader(row, headers);

        // 遍历集合数据，产生数据行
        if (dataList != null) {
            Iterator<T> it = dataList.iterator();
            int index = 0;
            int count = 1;
            while (it.hasNext()) {
                index++;
                if (index > 65000) {
                    sheet = workbook.createSheet(title + count);
                    // 产生表格标题行
                    row = sheet.createRow(0);
                    createHeader(row, headers);
                    index = 1;
                    count++;
                }
                row = sheet.createRow(index);
                T t = it.next();
                // 利用反射，根据javabean属性的先后顺序，动态调用getXxx()方法得到属性值
                Field[] fields = t.getClass().getDeclaredFields();
                for (int i = 0; i < fields.length; i++) {
                    HSSFCell cell = row.createCell(i);
                    Field field = fields[i];
                    String fieldName = field.getName();
                    String getMethodName = "get"
                            + fieldName.substring(0, 1).toUpperCase()
                            + fieldName.substring(1);
                    try {
                        Class tCls = t.getClass();
                        Method getMethod = tCls.getMethod(getMethodName, new Class[]{});
                        Object value = getMethod.invoke(t, new Object[]{});
                        // 判断值的类型后进行强制类型转换
                        String textValue;

                        if (value instanceof Date) {
                            Date date = (Date) value;
                            SimpleDateFormat sdf = new SimpleDateFormat(pattern);
                            textValue = sdf.format(date);
                        } else {
                            // 其它数据类型都当作字符串简单处理
                            textValue = value == null ? "" : value.toString();
                        }
                        // 利用正则表达式判断textValue是否全部由数字组成
                        if (textValue != null) {
                            Pattern p = Pattern.compile("^//d+(//.//d+)?$");
                            Matcher matcher = p.matcher(textValue);
                            if (matcher.matches()) {
                                // 是数字当作double处理
                                cell.setCellValue(Double.parseDouble(textValue));
                            } else {
                                HSSFRichTextString richString = new HSSFRichTextString(
                                        textValue);
                                cell.setCellValue(richString);
                            }
                        }
                    } catch (IllegalAccessException | NoSuchMethodException | SecurityException | IllegalArgumentException | InvocationTargetException e) {
                        e.printStackTrace();
                    }
                }

            }
        }

        try {
            workbook.write(out);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    //创建表头
    public static void createHeader(HSSFRow row, String[] headers) {
        if (headers != null){
            for (int i = 0; i < headers.length; i++) {
                HSSFCell cell = row.createCell(i);
                HSSFRichTextString text = new HSSFRichTextString(headers[i]);
                cell.setCellValue(text);
            }

        }
    }


}