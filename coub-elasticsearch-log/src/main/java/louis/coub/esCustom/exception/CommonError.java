package louis.coub.esCustom.exception;

/**
 * @author Louis
 * @date 2020-06-08 09:50
 */
public enum CommonError implements FailureBuilder {
    INTERNAL_ERROR("00000", "服务器内部异常"),
    ES_ERROR("00001", "es异常"),
    PARAM_ERROR("00002", "参数异常"),
    DB_ERROR("00003", "数据库异常");


    private final String subCode;

    private final String message;

    CommonError(String subCode, String message) {
        this.subCode = subCode;
        this.message = message;
    }

    @Override
    public String getSubCode() {
        return subCode;
    }

    @Override
    public String getMessage() {
        return message;
    }

    @Override
    public ErrorCodeBuilder getErrorType() {
        return ErrorType.COMMON_ERROR;
    }}
