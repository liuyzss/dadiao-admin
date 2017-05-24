package com.dadiao.wang.constant;

/**
 * Created by liuyang on 2017/5/24.
 */
public enum InventoryLogEnum {

    ADD_INVENTORY(0,"添加库存"),
    ADD_USER(1,"开户"),
    BATCH_ADD_USER(2,"批量开户"),
    ;

    public final int value;
    public final String desc;

    InventoryLogEnum(int value, String desc) {
        this.value = value;
        this.desc = desc;
    }
}
