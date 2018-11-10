package com.neuedu.utils;

import org.apache.commons.net.ftp.FTPClient;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

public class FtpUtils {

    public static final String FTPIP=PropertiesUtils.readByKey("ftp.server.ip");
    public static final String FTPUSER=PropertiesUtils.readByKey("ftp.server.user");
    public static final String FTPPASSWORD=PropertiesUtils.readByKey("ftp.server.password");

    private String ftpIp;
    private String ftpUser;
    private String ftpPassword;
    private Integer port;

    public FtpUtils(String ftpIp, String ftpUser, String ftpPassword, Integer port) {
        this.ftpIp = ftpIp;
        this.ftpUser = ftpUser;
        this.ftpPassword = ftpPassword;
        this.port = port;
    }

    /**
     * 图片上传到ftp
     * */
    public static boolean uploadFile(List<File> fileList) throws IOException {

        FtpUtils ftpUtils=new FtpUtils(FTPIP,FTPUSER,FTPPASSWORD,21);
        System.out.println("开始连接ftp服务器");
        ftpUtils.uploadFile("img",fileList);
        return false;
    }


    public boolean uploadFile(String remotePath,List<File> fileList) throws IOException {

        FileInputStream fileInputStream=null;
        //连接ftp服务器
        if (connectFTPServer(ftpIp,ftpUser,ftpPassword)){
            try {
                ftpClient.changeWorkingDirectory(remotePath);
                ftpClient.setBufferSize(1024);
                ftpClient.setControlEncoding("utf-8");
                ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE);
                ftpClient.enterLocalPassiveMode();//打开被动传输模式
                for (File file:fileList){
                    fileInputStream=new FileInputStream(file);
                    ftpClient.storeFile(file.getName(),fileInputStream);
                    System.out.println(file.length());
                }
                System.out.println("====文件上传成功====");
                return true;
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("文件上传出错...");
            }finally {
                fileInputStream.close();
                ftpClient.disconnect();
            }

        }
        return false;
    }

    /**
     * 连接ftp服务器
     * */
    FTPClient ftpClient=null;
    public  boolean connectFTPServer(String Ip,String user,String password){
        ftpClient = new FTPClient();

        try {
            ftpClient.connect(Ip);
            return ftpClient.login(user,password);

        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("连接ftp服务器异常");
        }
        return false;
    }

    public String getFtpIp() {
        return ftpIp;
    }

    public void setFtpIp(String ftpIp) {
        this.ftpIp = ftpIp;
    }

    public String getFtpUser() {
        return ftpUser;
    }

    public void setFtpUser(String ftpUser) {
        this.ftpUser = ftpUser;
    }

    public String getFtpPassword() {
        return ftpPassword;
    }

    public void setFtpPassword(String ftpPassword) {
        this.ftpPassword = ftpPassword;
    }

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }

    public FTPClient getFtpClient() {
        return ftpClient;
    }

    public void setFtpClient(FTPClient ftpClient) {
        this.ftpClient = ftpClient;
    }
}
