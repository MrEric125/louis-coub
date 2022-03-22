package com.louis.mybatis.generator;

import com.baomidou.mybatisplus.generator.config.rules.IColumnType;

/**
 * @author Louis
 * @date created on 2020/12/1
 * description:
 */
public class OrdinaryColumnType implements IColumnType {

    private String type;

    private String pkg;

    public OrdinaryColumnType(String type, String pkg) {
        this.type = type;
        this.pkg = pkg;
    }

    @Override
    public String getType() {
        return null;
    }

    @Override
    public String getPkg() {
        return null;
    }
}
