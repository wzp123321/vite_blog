package com.java.blog.utils;

import com.java.blog.common.exception.FileUploadException;
import com.java.blog.dao.FileResMapper;
import com.java.blog.vo.FileVo;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.math.BigInteger;
import java.net.MalformedURLException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class FileUploadUtils {
    private static Logger logger = LoggerFactory.getLogger(FileUploadUtils.class);
    // ftp连接客户端
    public static FTPClient ftpClient = null;
    //  用户名
    private static final String ftpUserName = "ftpadmin";
    //  密码
    private static final String ftpPassword = "adminroot123";
    //  主机
    private static final String ftpHost = "132.232.66.140";
    //  端口
    private static final int ftpPort = 21;
    //  文件存储位置
    private static final String ftpPath = "/home/upload";


    //    获取File的md5值
    public String getFileMD5(File file) {
        try {
            BigInteger bigInt = null;
            try {
                FileInputStream fis = new FileInputStream(file);
                MessageDigest md = MessageDigest.getInstance("MD5");
                byte[] buffer = new byte[1024];
                int length = -1;
                while ((length = fis.read(buffer, 0, 1024)) != -1) {
                    md.update(buffer, 0, length);
                }
                bigInt = new BigInteger(1, md.digest());
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return bigInt.toString(16);
        } catch (Exception e) {
            logger.error(e.toString(), e);
        }
        return null;
    }


    public String getMultipartFileMd5(MultipartFile multipartFile) {
        try {
            //获取文件的byte信息
            byte[] uploadBytes = multipartFile.getBytes();
            // 拿到一个MD5转换器
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            byte[] digest = md5.digest(uploadBytes);
            //转换为16进制
            return new BigInteger(1, digest).toString(16);
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        return null;
    }

    /**
     * 初始化ftp服务器
     */
    public void initFtpClient(String ftpHost, String ftpUserName, String ftpPassword, Integer ftpPort) {
        ftpClient = new FTPClient();
        ftpClient.setControlEncoding("utf-8");
        try {
            //连接ftp服务器
            ftpClient.connect(ftpHost, ftpPort);
            //登录ftp服务器
            ftpClient.login(ftpUserName, ftpPassword);
            //登录服务器响应码
            int replyCode = ftpClient.getReplyCode();
            if (!FTPReply.isPositiveCompletion(replyCode)) {
                logger.error("connect failed...ftp服务器:" + ftpHost + ":" + ftpPort);
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 上传文件
     *
     * @param fileName    上传到ftp的文件名
     * @param inputStream 输入文件流
     * @return
     */
    public boolean uploadFile(String fileName, InputStream inputStream) {
        // 设置开关
        boolean flag = true;
        try {
            initFtpClient(ftpHost, ftpUserName, ftpPassword, ftpPort);
            ftpClient.setFileType(ftpClient.BINARY_FILE_TYPE);
            ftpClient.enterLocalPassiveMode();
            ftpClient.makeDirectory(ftpPath);
            ftpClient.setControlEncoding("utf-8");
            ftpClient.storeFile(fileName, inputStream);
            inputStream.close();
            ftpClient.logout();
            logger.info("上传文件成功");
        } catch (Exception e) {
            flag = false;
            logger.error("上传文件失败");
            e.printStackTrace();
        } finally {
            if (ftpClient.isConnected()) {
                try {
                    ftpClient.disconnect();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (null != inputStream) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return flag;
    }

    public FileVo getFileUploadLocal(MultipartFile file) {
//        文件本地存储位置
        String filepath = "E:\\images";
//        文件后缀名
        String extension = file.getOriginalFilename().substring(file.getOriginalFilename().indexOf('.') + 1);
//        文件前缀名
        String suffix = UUIDUtils.md5(file.getOriginalFilename());
//        拼接成新的文件名
        String fileName = suffix + "." + extension;
//         文件存储到数据库的地址
        String url = "http://127.0.0.1:9999/" + fileName;
        File dest = new File(filepath + "/" + fileName);
        System.out.println(dest);
        try {
            File fileDir = new File(filepath);
            if (!fileDir.exists()) {
                fileDir.mkdir();
            }
            System.out.println(file);
            file.transferTo(dest);
        } catch (IOException e) {
            logger.error("文件上传失败" + fileName + "---" + e);
            throw new FileUploadException(e.getMessage());
        }
        String md5 = getFileMD5(dest);

        FileVo FileVo = new FileVo();
        FileVo.setExtension(extension);
        FileVo.setFileName(fileName);
        FileVo.setLength(file.getSize());
        FileVo.setUrl(url);
        FileVo.setMd5(md5);
        logger.info("文件上传成功！" + FileVo);
        return FileVo;
    }

}
