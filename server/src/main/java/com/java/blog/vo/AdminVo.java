package com.java.blog.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class AdminVo implements Serializable {
    private static final long serialVersionUID = 6930342838182098260L;

    private Integer id;

    private String name;

    private String salt;

    private String password;

    private Date createTime;

    private Date modifyTime;
}
