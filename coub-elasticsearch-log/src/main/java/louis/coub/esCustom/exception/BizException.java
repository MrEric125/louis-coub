package louis.coub.esCustom.exception;

/**
 * @author Louis
 */
public class BizException extends BaseBizException {

    private final Failure failure;

    public BizException(Failure failure) {
        super(failure.getMsg());
        this.failure = failure;
    }

    public BizException(Failure failure, Throwable ex) {
        super(failure.getMsg(), ex);
        this.failure = failure;
    }

    @Override
    public Failure getFailure() {
        return failure;
    }
}
