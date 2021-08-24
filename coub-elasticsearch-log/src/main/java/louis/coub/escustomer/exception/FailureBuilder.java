package louis.coub.escustomer.exception;

import com.alibaba.fastjson.JSONObject;

import java.util.concurrent.CompletableFuture;

/**
 * @author Louis
 */
public interface FailureBuilder {

    /**
     * 模块内错误码
     *
     * @return 模块内五位错误码
     */
    String getSubCode();

    /**
     * 模块内错误异常信息
     * @return
     */
    String getMessage();

    /**
     * 错误码类型
     *
     * @return 错误码类型 用于构造完成错误码
     */
    ErrorCodeBuilder getErrorType();

    /**
     * 计算完整错误码
     *
     * @return 完整错误码
     */
    default String fullCode() {
        return getErrorType().build(getSubCode());
    }

    /**
     *
     * @return BizException
     */
    default BizException exception() {
        Failure failure = new Failure(fullCode(), getMessage());
        return new BizException(failure);
    }

    /**
     *
     * @param msg 自定义异常信息
     * @return BizException
     */
    default BizException exception(String msg) {
        Failure failure = new Failure(fullCode(), msg);
        return new BizException(failure);
    }

    /**
     *
     * @param msg    自定义异常信息
     * @param detail 自定义异常详情
     * @return BizException
     */
    default BizException exception(String msg, JSONObject detail) {
        Failure failure = new Failure(fullCode(), msg, detail);
        return new BizException(failure);
    }

    /**
     *
     * @param ex 异常原因
     * @return BizException
     */
    default BizException exception(Throwable ex) {
        Failure failure = new Failure(fullCode(), this.getMessage());
        return new BizException(failure, ex);
    }

    /**
     *
     * @param msg 自定义异常信息
     * @param ex  异常原因
     * @return BizException
     */
    default BizException exception(String msg, Throwable ex) {
        Failure failure = new Failure(fullCode(), msg);
        return new BizException(failure, ex);
    }

    /**
     *
     * @param msg    自定义异常信息
     * @param detail 自定义异常详情
     * @param ex     异常原因
     * @return BizException
     */
    default BizException exception(String msg, JSONObject detail, Throwable ex) {
        Failure failure = new Failure(fullCode(), msg, detail);
        return new BizException(failure, ex);
    }

    /**
     * 异常封装
     *
     * @param <T>    T
     * @param future CompletableFuture<T>
     * @return CompletableFuture<T>
     */
    default <T> CompletableFuture<T> exceptionally(CompletableFuture<T> future) {
        return future.handle((t, throwable) -> {
            if (throwable != null) {
                throw this.exception(throwable);
            } else {
                return t;
            }
        });
    }
}
