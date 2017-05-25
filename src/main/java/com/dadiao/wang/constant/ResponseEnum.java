package com.dadiao.wang.constant;

/**
 * Created by liuyang on 2017/5/25.
 */
public enum ResponseEnum {
    SYSTEM_ERROR("0001", "系统错误"),


    API_ERROR("1001", "接口请求错误"),

    NO_INVENTROY("2001", "库存不足"),;

    public final String value;
    public final String desc;

    ResponseEnum(String value, String desc) {
        this.value = value;
        this.desc = desc;
    }
}
