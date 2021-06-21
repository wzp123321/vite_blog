package com.java.blog.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class FileVo implements Serializable {

    private String id;

    private String fileName;

    private String url;

    private String md5;

    private Long length;

    private String extension;

    private Date createTime;
}
