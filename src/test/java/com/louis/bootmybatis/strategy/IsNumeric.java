package com.louis.bootmybatis.strategy;

/**
 * @author louis
 * <p>
 * Date: 2019/7/1
 * Description:
 */
public class IsNumeric implements ValidationStrategy{
    @Override
    public boolean execute(String s) {
        return s.matches("\\d");
    }
}
