package com.java.blog.service;

import com.java.blog.common.exception.FileUploadException;
import com.java.blog.dao.FileResMapper;
import com.java.blog.utils.FileUploadUtils;
import com.java.blog.vo.FileVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

@Service
public class FileManageService {

    @Autowired
    private FileResMapper fileResMapper;

    /**
     * 文件上传
     *
     * @param multipartFile
     * @return
     */
    public FileVo getFileVoInsert(MultipartFile multipartFile) {
        String id = UUID.randomUUID().toString();
        FileVo fileVo = new FileVo();

        FileUploadUtils fileUploadUtils = new FileUploadUtils();
        String md5 = fileUploadUtils.getMultipartFileMd5(multipartFile);
        fileVo = fileResMapper.getFileVoByMd5(md5);


        if (fileVo == null) {
            try {
                fileVo = fileUploadUtils.getFileUploadLocal(multipartFile);
            } catch (Exception e) {
                throw new FileUploadException("文件上传失败！" + e);
            }
            boolean flag = fileResMapper.getFileVoInsert(id, fileVo.getFileName(), fileVo.getMd5(), fileVo.getUrl(), fileVo.getLength(), fileVo.getExtension()) > 0;
            if (!flag) {
                throw new FileUploadException("文件上传失败！" + fileVo);
            }
        }
        return fileVo;
    }

    /**
     * 分页查询文件列表
     *
     * @param page
     * @param limit
     * @return
     */
    public ModelMap getFileVoList(int page, int limit) {
        int skip = page == 1 ? 0 : (page - 1) * limit;
        List<FileVo> fileVos = fileResMapper.getFileVoList(skip, limit);
        int total = fileResMapper.getFileVoTotal();
        ModelMap modelMap = new ModelMap();
        modelMap.put("list", fileVos);
        modelMap.put("total", total);
        return modelMap;
    }
}
