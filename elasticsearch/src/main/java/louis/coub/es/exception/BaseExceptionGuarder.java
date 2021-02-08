package louis.coub.es.exception;


import java.io.IOException;

public interface BaseExceptionGuarder<S> {

    /**
     * 必须使用{@link Method},无法使用{@link java.util.function.Supplier},
     * 虽然都是同样的行为参数化,但是有点不同的是在{@link Method#call()}是会抛出异常的，
     * 我们需要一个抛出异常的方法调用
     * @param method
     * @return
     */
    default S guard(Method<S> method) {
        try {
            return check(method.call());
        } catch (Exception e) {
            throw getErrorCode().exception(e);
        }

    }

    /**
     * 返回错误码
     *
     * @return FailureBuilder
     */
    FailureBuilder getErrorCode();


    /**
     *
     * @param response 检查的response
     * @return 正常的response
     * @throws IOException 抛出IOException
     */
    default S check(S response) throws Exception {
        return response;
    }

    @FunctionalInterface
    interface Method<S> {

        /**
         * es调用fp封装
         *
         * @return response
         * @throws IOException 大部分es客户端接口都有抛IoException的可能
         */
        S call() throws IOException;
    }
}
