package com.louis.lessifelse;

public enum NameEnum {

    NONE("A");

    private final String name;


    public final String getName() {
        return name;
    }

    NameEnum(String name) {
        this.name = name;
    }
}
