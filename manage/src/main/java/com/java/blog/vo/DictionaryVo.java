package com.java.blog.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class DictionaryVo implements Serializable {

    private static final long serialVersionUID = -4994899932290751257L;

    private int id;

    /**
     *  0-tag 1-一级目录 2-二级目录 3-文章类别
     */
    private Integer type;

    private String title;

    private Integer parentId;

    private String parentTitle;

    private String description;

    private Date createTime;

    private Date modifyTime;
}
