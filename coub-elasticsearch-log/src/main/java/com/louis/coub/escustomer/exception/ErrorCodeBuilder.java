package com.louis.coub.escustomer.exception;


/**
 * @author Louis
 */
public interface ErrorCodeBuilder {

    /**
     * 错误码构造
     *
     * @param subCode 模块内错误码 1-999
     * @return fullCode 八位 例: 70201001
     */
    String build(String subCode);
}
