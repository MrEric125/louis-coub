package com.enums;


/**
 * @author Louis
 * @date created on 2020/11/30
 * description:
 */
public enum Fivarite implements IBaseEnum {

    MOOR_PHONE(1, "电话"),
    MOOR_ONLINE(2, "在线"),
    ;

    Fivarite(int value, String name) {
        this.value = value;
        this.name = name;
    }

    private int value;
    private String name;

    @Override
    public int getValue() {
        return value;
    }

    @Override
    public String getName() {
        return name;
    }
}
