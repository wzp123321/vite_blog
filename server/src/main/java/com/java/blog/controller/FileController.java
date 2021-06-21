package com.java.blog.controller;

import com.java.blog.service.FileManageService;
import com.java.blog.vo.FileVo;
import com.java.blog.vo.ReturnResultVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping(value = "/blogManage/file")
public class FileController {

    @Autowired
    private FileManageService fileManageService;

    @PostMapping("upload")
    public ReturnResultVo getFileVoUpload(
            @RequestParam(value = "file", required = true) MultipartFile file
    ) {
        FileVo fileVo = fileManageService.getFileVoInsert(file);
        return new ReturnResultVo(200, "SUCCESS", fileVo);
    }
}
