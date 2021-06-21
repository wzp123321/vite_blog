package com.java.blog.vo;

import java.io.Serializable;

public class ReturnResultVo<T> implements Serializable {
    private static final long serialVersionUID = 4120794302785761654L;

    private int code;

    private String message;

    private T data;

    public ReturnResultVo() {
        this.code = 0;
        this.message = null;
        this.data = null;
    }

    public ReturnResultVo(int code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
