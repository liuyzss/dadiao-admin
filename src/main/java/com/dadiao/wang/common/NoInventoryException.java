package com.dadiao.wang.common;

/**
 * Created by liuyang on 2017/5/24.
 */
public class NoInventoryException extends RuntimeException {
    private Integer value;

    public NoInventoryException(Integer value) {
        super();
        this.value = value;
    }
}
