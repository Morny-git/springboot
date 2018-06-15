package com.mx.util;

import org.apache.poi.hssf.usermodel.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by mx on 2017/7/5.
 */
public class ExportMapExcelUtil {
    //执行导出
    public static boolean exportData(HttpServletRequest request, HttpServletResponse response, String sheetTitle, String[] headers, List<LinkedHashMap<String,Object>> dataList, String fileName) throws Exception {
            OutputStream out = FileUtils.formatFileName(request,response,fileName);
            sheetTitle = sheetTitle.isEmpty() ? "sheet" : sheetTitle;
            exportExcel(sheetTitle, headers, dataList, out, "yyyy-MM-dd");
            out.close();
            return true;

    }

    //创建excel
    public static void exportExcel(String title, String[] headers,
                            List<LinkedHashMap<String,Object>> dataList, OutputStream out, String pattern) throws Exception {
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet(title);
        // 产生表格标题行
        HSSFRow row = sheet.createRow(0);
        ExcelUtil.createHeader(row, headers);
        // 遍历集合数据，产生数据行
        if (dataList != null) {
            int index = 0;
            int count = 1;
            for (LinkedHashMap<String, Object> map : dataList) {
                index++;
                if (index > 65000){
                    sheet = workbook.createSheet(title + count);
                    // 产生表格标题行
                    row = sheet.createRow(0);
                    ExcelUtil.createHeader(row, headers);
                    index = 1;
                    count++;
                }
                row = sheet.createRow(index);
                int cellIndex = 0;
                for (Object value : map.values()) {
                    HSSFCell cell = row.createCell(cellIndex);
                    setCellVal(cell,value,pattern);
                    cellIndex++;
                }
            }
        }
        workbook.write(out);
    }

    /**
     * 设置单元格的值,判断值的类型后进行强制类型转换
     */
    public static void setCellVal( HSSFCell cell, Object value, String pattern) throws Exception{
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
    }
}
