package com.louis.longagocode.louis.bootmybatis;

import lombok.Getter;
import lombok.Setter;

/**
 * @author louis
 * <p>
 * Date: 2019/7/5
 * Description:
 */
@Setter
@Getter
public class ResponseData<T> {


    private String message;
    private int code;

    private T result;

    public ResponseData(String message, int code, T result) {
        this.message = message;
        this.code = code;
        this.result = result;
    }

    public ResponseData(T result) {
        this("success", 200, result);
    }


}
