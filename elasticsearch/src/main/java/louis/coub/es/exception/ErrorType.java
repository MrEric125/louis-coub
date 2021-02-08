package louis.coub.es.exception;

/**
 *
 * 定义每个模块的错误码，模块内错误码需要实现
 *
 * @see CommonError
 *
 * @author Louis
 */
public enum ErrorType implements ErrorCodeBuilder {
    COMMON_ERROR("100"),

    STATISTICS_ERROR("101");

    private final String codeBase;

    public String getCodeBase() {
        return codeBase;
    }

    ErrorType(String codeBase) {
        this.codeBase = codeBase;
    }

    @Override
    public String build(String code) {
        return codeBase + code;
    }
}
