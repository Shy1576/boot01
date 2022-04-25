package com.hz.boot01.utils;

import lombok.Data;

@Data
public class JsonMassage<T> {
    private Integer code; // 错误代码  0代码成功
    private String msg;
    private Integer count;//总条数
    private T data; //数据

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
