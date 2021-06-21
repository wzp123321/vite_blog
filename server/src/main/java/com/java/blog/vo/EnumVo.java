package com.java.blog.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class EnumVo implements Serializable {

    private static final long serialVersionUID = -2965174252684813169L;

    private Integer id;

    private Integer code;

    private String title;

    private Date createTime;

    private Date modifyTime;
}
