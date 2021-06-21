package com.java.blog.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class ArticleVo implements Serializable {
    private static final long serialVersionUID = 4321359851934729792L;

    private String id;

    private String title;

    private String description;

    private String content;

    private String md;

    private String imgUrl;

    private String tags;

    private Integer firstCatalogId;

    private String firstCatalogName;

    private Integer secondCatalogId;

    private String secondCatalogName;

    /**
     * 0-被删除 1-未上架 2-已上架 3-推荐 4-置顶
     */
    private Integer status;

    /**
     * 点赞 评论 数 单独建表
     */
    private Integer visitCount;

    private Date createTime;

    private Date modifyTime;
}
