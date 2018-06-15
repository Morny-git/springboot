package com.mx.util;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;

/**
 * Created by mx on 2017/12/11.
 */
@Component
public class FtpFileUtils {
    private static final Logger logger = Logger.getLogger(FtpFileUtils.class);
    @Value("${ftp.host}")
    private String host;
    @Value("${ftp.port}")
    private int port;
    @Value("${ftp.userName}")
    private String userName;
    @Value("${ftp.userPass}")
    private String userPass;
    @Value("${ftp.basePath}")
    private String basePath;


    //连接
    public FTPClient connect() throws Exception{
        FTPClient ftp = new FTPClient();
        int reply;
        ftp.connect(host,port);
        logger.info("连接到：" + host + ":" + port);
        reply = ftp.getReplyCode();
        if (!FTPReply.isPositiveCompletion(reply)) {
            ftp.disconnect();
            logger.error("FTP目标服务器积极拒绝.");
            System.exit(1);
            return null;
        }else{
            ftp.login(userName, userPass);
            ftp.setBufferSize(1024);
            ftp.setControlEncoding("UTF-8");
            ftp.setFileType(FTPClient.BINARY_FILE_TYPE);
            ftp.changeWorkingDirectory(basePath);
            logger.info("已连接：" + host + ":" + port);
            return ftp;
        }

    }

    //断开连接
    public void disconnect(FTPClient ftp) throws Exception{
      ftp.logout();
      ftp.disconnect();
    }

    //建文件夹
    public void mkDir(FTPClient ftp,String path) throws IOException {
        ftp.setFileTransferMode(FTP.STREAM_TRANSFER_MODE);
        if (!ftp.changeWorkingDirectory(basePath)){
            ftp.makeDirectory(basePath);
            ftp.changeWorkingDirectory(basePath);
        }
        if (!ftp.changeWorkingDirectory(path)){
            ftp.makeDirectory(path);
            ftp.changeWorkingDirectory(path);
        }
    }

    //上传文件
    public void upload(MultipartFile file) throws Exception {
        String path = basePath + DateHelperUtil.formatToString(null,"yyyyMMdd");
        InputStream inputStream = null;
        FTPClient ftpClient = null;
        try {
            inputStream = file.getInputStream();
            ftpClient = connect();
            mkDir(ftpClient,path);
            ftpClient.storeFile(file.getName(), inputStream);
        }finally {
            inputStream.close();
            disconnect(ftpClient);
        }
    }
    //下载流
    public InputStream down(String filePath)throws Exception{
        FTPClient ftp = connect();
        return ftp.retrieveFileStream(filePath);
    }


}
