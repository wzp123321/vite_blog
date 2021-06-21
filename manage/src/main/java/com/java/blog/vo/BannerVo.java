package com.java.blog.vo;

import lombok.Data;

import java.io.Serializable;

@Data
public class BannerVo implements Serializable {
    private static final long serialVersionUID = -2042391282625695536L;

    private Integer id;

    private String imageUrl;

    private String title;

    private String articleId;

    /**
     * 0-未上架 1-已上架'
     */
    private Integer state;
}
