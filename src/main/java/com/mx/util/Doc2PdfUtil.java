package com.mx.util;

/**
 * Created by mx on 2017/11/9.
 */

import com.artofsolving.jodconverter.DocumentConverter;
import com.artofsolving.jodconverter.openoffice.connection.OpenOfficeConnection;
import com.artofsolving.jodconverter.openoffice.connection.SocketOpenOfficeConnection;
import com.artofsolving.jodconverter.openoffice.converter.StreamOpenOfficeDocumentConverter;

import java.io.*;

/**
 * 利用jodconverter(基于OpenOffice服务)将文件(*.doc、*.docx、*.xls、*.ppt)转化为html格式或者pdf格式，
 * 使用前请检查OpenOffice服务是否已经开启, OpenOffice进程名称：soffice.exe | soffice.bin
 */
public class Doc2PdfUtil {

    private static Doc2PdfUtil doc2PdfUtil;

    /**
     * 获取Doc2HtmlUtil实例
     */
    public static synchronized Doc2PdfUtil getDoc2HtmlUtilInstance() {
        if (doc2PdfUtil == null) {
            doc2PdfUtil = new Doc2PdfUtil();
        }
        return doc2PdfUtil;
    }


    public void convert(String input, String output){
        File inputFile = new File(input);
        File outputFile = new File(output);
        OpenOfficeConnection connection = new SocketOpenOfficeConnection("172.16.23.2",8100);
        try {
            connection.connect();
            DocumentConverter converter = new StreamOpenOfficeDocumentConverter(connection);
            converter.convert(inputFile, outputFile);
        } catch(Exception e) {
            e.printStackTrace();
        } finally {
            try{ if(connection != null){connection.disconnect(); connection = null;}}catch(Exception e){}
        }
    }



    public static void main(String[] args) throws IOException {
        Doc2PdfUtil coc2HtmlUtil = getDoc2HtmlUtilInstance();
        File file = null;
        FileInputStream fileInputStream = null;


//      coc2HtmlUtil.file2Html(fileInputStream, "D:/poi-test/openOffice/ppt","ppt");
        //coc2HtmlUtil.file2pdf("D:/本体服务接口.doc", "D:/test","doc");
        long start = System.currentTimeMillis();
        coc2HtmlUtil.convert("D:/安全门户单点部署mx.txt", "D:/test/e6.pdf");
        long end = System.currentTimeMillis();
        System.out.println("花费了"+(end-start)/1000);

    }

}
