package com.mx.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

/**
 * 文件工具类
 * Created by hadoop on 2016/5/30.
 */
public class FileUtils {
    public static List<File> list = new ArrayList<>();

    public static File configureEnvFile(String envKey) {
        String logFile = System.getenv(envKey);
        File logConfigFile = null;
        if (null != logFile) {
            logConfigFile = new File(logFile);
            if (!logConfigFile.exists()) logConfigFile = null;
        }
        return logConfigFile;
    }

    /**
     * 删除文件及文件夹
     * @param file
     */
    public static void deleteFile(File file){
        if (file.exists()){
            if (file.isDirectory()){
                File[] files = file.listFiles();
                for (File file1 : files) {
                    deleteFile(file1);
                }
                file.delete();
            }
            if (file.isFile()){
                file.delete();
            }
        }
    }
    /**
     * 获取所有文件
     */
    public static List<File> getFiles(String path){

        File file = new File(path);
        if (file.exists()){
            if (file.isFile()){
                list.add(file);
            }
            if (file.isDirectory()){
                File[] files = file.listFiles();
                for (File file1 : files) {
                    getFiles(file1.toString());
                }
            }
        }
        return list;
    }

    /**
     * 将生成的文件名置为中文
     *
     */
    public static OutputStream formatFileName(HttpServletRequest request, HttpServletResponse response, String fileName) throws IOException {
        String agent = request.getHeader("User-Agent");
        response.reset();

        //导出文件中文名称，火狐的处理方式和其他浏览器不同
        if (agent.indexOf("Firefox")>0){
            fileName = new String(fileName.getBytes("UTF-8"), "iso-8859-1");
        }else {
            fileName = URLEncoder.encode(fileName, "UTF-8");
        }
        response.setContentType("application/vnd.ms-excel");
        response.setCharacterEncoding("UTF-8");
        response.addHeader("Content-Disposition", "attachment;filename=" + fileName);
        return response.getOutputStream();
    }

    /**
     * 下载文件
     */
    public static void download(HttpServletRequest request, HttpServletResponse response, String filePath) throws IOException{
        String fileName = filePath.substring(filePath.lastIndexOf("/") + 1);
        String contentDisposition = "attachment; filename=" + fileName;
        response.setCharacterEncoding("UTF-8");
        response.setHeader("Content-Disposition", contentDisposition);
        InputStream is = null;
        OutputStream os = null;
        is = new FileInputStream(filePath);
        os = formatFileName(request,response,fileName);
        byte[] buffer = new byte[1024];
        int read ;
        while ((read = is.read(buffer))>0){
            os.write(buffer,0,read);
        }
        os.close();
        is.close();

    }

}
