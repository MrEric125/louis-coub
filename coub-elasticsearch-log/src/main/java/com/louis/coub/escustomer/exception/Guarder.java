package com.louis.coub.escustomer.exception;


/**
 * @author Louis
 */
public final class Guarder<T> implements BaseExceptionGuarder<T> {

    private final FailureBuilder errorCode;

    private Guarder(FailureBuilder errorCode) {
        this.errorCode = errorCode;
    }

    public static <T> Guarder<T> of(FailureBuilder errorCode) {
        return new Guarder<T>(errorCode);
    }

    @Override
    public FailureBuilder getErrorCode() {
        return errorCode;
    }

}
