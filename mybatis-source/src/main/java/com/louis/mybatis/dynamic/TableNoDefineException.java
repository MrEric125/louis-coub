package com.louis.mybatis.dynamic;

/**
 * @author John·Louis
 * @date create in 2019/9/13
 * description:
 */
public class TableNoDefineException extends RuntimeException {

    public TableNoDefineException(String message) {
        super(message);
    }
}
