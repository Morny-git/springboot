package com.mx.util;

import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFRichTextString;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * Created by hadoop on 2016/10/9.
 */
public class ExcelUtil {

    /**
     * 测试excel中行有多少空的单元格
     *
     * @param row
     * @return
     */
    public static int checkRowNull(Row row) {

        int num = 0;
        Cell cell;

        for (int i = 0; i < row.getLastCellNum(); i++) {
            cell = row.getCell(i, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
            if ( cell == null || "".equals(cell) || cell.getCellTypeEnum() == CellType.BLANK || StringUtils.isBlank(cell.toString())) {
                num++;
            }
        }
        return num;
    }

    /**
     * excel去除空行有多少行
     */
    public static int checkRowNum(Sheet sheet) {
        int num = 0;
        for (Row row : sheet) {
            if (checkRowNull(row) < row.getLastCellNum()) {
                num++;
            }
        }
        return num;
    }

    /**
     * 判断csv的行是否是空行
     */
    public static boolean isNull(String strLine) {
        if (strLine.equals("")) {
            return true;
        }

        String[] str = strLine.split(",");
        int len = str.length;
        if (len == 0) {
            return true;
        }
        int num = 0;
        for (String s : str) {
            if (StringUtils.isBlank(s)) {
                num++;
            }
        }
        if (num < len) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * 将excel转化为list
     *
     * @param workbook
     * @param colunm--和文件中的列顺序一样
     * @return
     */
    public static List<Map<String, Object>> ExcelToList(Workbook workbook, String[] colunm) {
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        for (int sheetIndex = workbook.getActiveSheetIndex(); sheetIndex <= workbook.getActiveSheetIndex(); sheetIndex++) {
            Sheet sheet = workbook.getSheetAt(sheetIndex);
            Row row = null;
            int rows = sheet.getLastRowNum() + 1;

            for (int i = 1; i < rows; i++) {
                Map<String, Object> map = new HashMap<String, Object>();
                //得到Excel工作表的行
                row = sheet.getRow(i);
                if (row == null) continue;
                if (ExcelUtil.checkRowNull(row) < row.getLastCellNum()) {
                    for (int j = 0; j < colunm.length; j++) {
                        map.put(colunm[j], row.getCell(j, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).toString());
                    }
                    list.add(map);
                }

            }
        }
        return list;
    }

    /**
     * 压缩文件
     * @param srcPath
     * @param zipPath
     * @return
     */
    public static boolean zipFiles(String srcPath, String zipPath) throws IOException{
        byte[] buff = new byte[1024];
        ZipOutputStream out = null;
        FileInputStream fis = null;
        try {
            out = new ZipOutputStream(new FileOutputStream(new File(zipPath)));
            File file = new File(srcPath);
            fis = new FileInputStream(file);
            out.putNextEntry(new ZipEntry(file.getName()));
            //String str = file.getName();
            int len;
            while ((len = fis.read(buff)) > 0) {
                out.write(buff, 0, len);
            }
            return true;
        } catch (IOException e) {
            return false;
        }finally {
        	if(out!=null){
        		out.close();
        	}
        	if(fis!=null){
        		fis.close();
        	}   
		}
    }

    public static boolean writeZip(String srcPath, String zipPath){
        File file = new File(srcPath);
        String parentPath = file.getParent().toString();
        if (file.exists()){
            if (file.isDirectory()){
                parentPath += file.getName()+File.separator;
                String[] files = file.list();
                for (String file1 : files) {
                    writeZip(file1,zipPath);
                }
            }else {
                FileInputStream fis = null;
                ZipOutputStream zos = null;
                try {
                    fis = new FileInputStream(file);
                    zos = new ZipOutputStream(new FileOutputStream(zipPath));
                    ZipEntry ze = new ZipEntry(parentPath+file.getName());
                    zos.putNextEntry(ze);
                    byte[] content = new byte[1024];
                    int len ;
                    while ((len = fis.read(content))!=-1){
                        zos.write(content,0,len);
                        zos.flush();
                    }
                    return true;
                } catch (Exception e) {
                    e.printStackTrace();
                    return false;
                }finally {
                    try {
                        if (fis!=null){
                            fis.close();
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return false;
    }

    /**
     * 生成模板
     */
    public static boolean template(String path, List<String> headers, String fileType,String fileName) throws IOException {
        Workbook workbook = null;
        RichTextString text = null;
        if (fileType.equals("xls")) {
            workbook = new XSSFWorkbook();
        } else if (fileType.equals("xlsx")) {
            workbook = new XSSFWorkbook();
        }
        if(workbook==null){
        	return false;
        }
        Sheet sheet = workbook.createSheet();
        Row row = sheet.createRow(0);
        for (int i = 0; i < headers.size(); i++) {
            Cell cell = row.createCell(i);
            if (fileType.equals("xlsx")) {
                text = new XSSFRichTextString(headers.get(i));
            } else if (fileType.equals("xls")) {
                text = new HSSFRichTextString(headers.get(i));
            }
            cell.setCellValue(text);
        }
        OutputStream out = null;
        fileName = StringUtils.isEmpty(fileName)?"Template":fileName;
        path = path+"\\"+fileName+"."+fileType;
        try {
            out = new FileOutputStream(path);
            workbook.write(out);
            
            return true;
        } catch (Exception e) {
            //e.printStackTrace();
            return false;
        } finally {
        	if(out!=null){
        		out.close();
        	}
        	workbook.close();
		}

    }

    public static void templateDoubleHeader(HttpServletRequest request, HttpServletResponse response, LinkedHashMap<String, String> linkedHashMap) throws Exception {
    	Workbook workbook = new HSSFWorkbook();
    	OutputStream out = null;
        try {
            String fileName = DateHelperUtil.formatToString(new Date(), "yyyyMMddHHmmss") + ".xls";
            out = FileUtils.formatFileName(request,response,fileName);
            Sheet sheet = workbook.createSheet();
            Row row0 = sheet.createRow(0);
            Row row1 = sheet.createRow(1);
            int i = 0;
            for (Map.Entry<String, String> entry : linkedHashMap.entrySet()) {
                Cell cell0 = row0.createCell(i);
                ExcelUtil.setCell(workbook,entry.getKey(),cell0);
                Cell cell1 = row1.createCell(i);
                ExcelUtil.setCell(workbook,entry.getValue(),cell1);
                i++;
            }
            //设置第一行隐藏
            row0.setZeroHeight(true);
            workbook.write(out);
		} finally {
			// TODO: handle finally clause
			workbook.close();
			if(out!=null){
				out.close();
			}    
		}
        
    }

    /**
     * 设置单元格内容与格式
     */
    public static void setCell(Workbook workbook,String cellVal, Cell cell) {
        CellStyle cellStyle = workbook.createCellStyle();
        DataFormat format = workbook.createDataFormat();
        cellStyle.setDataFormat(format.getFormat("@"));
        cell.setCellStyle(cellStyle);
        RichTextString text = new HSSFRichTextString(cellVal);
        //设置文本格式
        cell.setCellValue(text);
        cell.setCellType(CellType.STRING);
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
