package louis.coub.esCustom.exception;

/**
 * @author Louis
 * @date 2020-06-07 19:36
 */
public abstract class BaseBizException extends RuntimeException {

    protected BaseBizException(String msg) {
        super(msg);
    }

    protected BaseBizException(String msg, Throwable ex) {
        super(msg, ex);
    }

    /**
     * 返回失败的response.
     *
     * @return Failure
     */
    public abstract Failure getFailure();
}
