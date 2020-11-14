

package com.louis.common.common;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;


/**
 * The class HttpResult.
 * 返回到首页的包装类
 * @param <T> the type parameter @author John·Louis
 */
@Data
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class HttpResult<T> implements Serializable {

	/**
	 * 序列化标识
	 */
	private static final long serialVersionUID = 4893280118017319089L;

	/**
	 * 成功码.
	 */
	@Deprecated
	public static final int SUCCESS_CODE = 200;

	/**
	 * 成功信息.
	 */
	@Deprecated
	public static final String SUCCESS_MESSAGE = "操作成功";

	/**
	 * 错误码.
	 */
	@Deprecated
	public static final int ERROR_CODE = 500;

	/**
	 * 错误信息.
	 */
	@Deprecated
	public static final String ERROR_MESSAGE = "内部异常";

	/**
	 * 错误码：参数非法
	 */
	@Deprecated
	public static final int ILLEGAL_ARGUMENT_CODE_ = 100;

	/**
	 * 错误信息：参数非法
	 */
	@Deprecated
	public static final String ILLEGAL_ARGUMENT_MESSAGE = "参数非法";

	/**
	 * 编号.
	 */
	private int code;

	/**
	 * 信息.
	 */
	private String message;

	/**
	 * 结果数据
	 */
	private T result;

	/**
	 * Instantiates a new HttpResult. default code=200
	 */
	HttpResult() {
		this(HttpResultMassage.SUCCESS_CODE, HttpResultMassage.SUCCESS_MESSAGE);
	}

	/**
	 * Instantiates a new HttpResult.
	 *
	 * @param code    the code
	 * @param message the message
	 */
	HttpResult(int code, String message) {
		this(code, message, null);
	}

	/**
	 * Instantiates a new HttpResult.
	 *
	 * @param code    the code
	 * @param message the message
	 * @param result  the result
	 */
	HttpResult
	(int code, String message, T result) {
		super();
		this.code(code).message(message).result(result);
	}

	/**
	 * Sets the 编号 , 返回自身的引用.
	 *
	 * @param code the new 编号
	 *
	 * @return the HttpResult
	 */
	private 	HttpResult<T> code(int code) {
		this.setCode(code);
		return this;
	}

	/**
	 * Sets the 信息 , 返回自身的引用.
	 *
	 * @param message the new 信息
	 *
	 * @return the HttpResult
	 */
	private HttpResult<T> message(String message) {
		this.setMessage(message);
		return this;
	}

	/**
	 * Sets the 结果数据 , 返回自身的引用.
	 *
	 * @param result the new 结果数据
	 *
	 * @return the HttpResult
	 */
	public HttpResult<T> result(T result) {
		this.setResult(result);
		return this;
	}

	/**
	 * 判断是否成功： 依据 HttpResult.SUCCESS_CODE == this.code
	 *
	 * @return code =200,true;否则 false.
	 */
	@JsonIgnore
	public boolean success() {
		return HttpResultMassage.SUCCESS_CODE == this.code;
	}

	/**
	 * 判断是否成功： 依据 HttpResult.SUCCESS_CODE != this.code
	 *
	 * @return code !=200,true;否则 false.
	 */
//	@JsonIgnore
//	public boolean error() {
//		return !success();
//	}



	/**
	 * Wrap.
	 *
	 * @param <E>     the element type
	 * @param code    the code
	 * @param message the message
	 * @param o       the o
	 *
	 * @return the HttpResult
	 */
	public static <E> HttpResult<E> wrap(int code, String message, E o) {
		return new HttpResult<>(code, message, o);
	}


	public static <E> HttpResult<E> wrap(E e) {
		return wrap(HttpResultMassage.SUCCESS_CODE, HttpResultMassage.SUCCESS_MESSAGE, e);
	}

	/**
	 * Wrap.
	 *
	 * @param <E>     the element type
	 * @param code    the code
	 * @param message the message
	 *
	 * @return the HttpResult
	 */
	public static <E> HttpResult<E> wrap(int code, String message) {
		return wrap(code, message, null);
	}

	/**
	 * Wrap.
	 *
	 * @param <E>  the element type
	 * @param code the code
	 *
	 * @return the HttpResult
	 */
	public static <E> HttpResult<E> wrap(int code) {
		return wrap(code, null);
	}

	/**
	 * Wrap.
	 *
	 * @param <E> the element type
	 * @param e   the e
	 *
	 * @return the HttpResult
	 */
	public static <E> HttpResult<E> wrap(Exception e) {
		return new HttpResult<>(HttpResultMassage.ERROR_CODE, e.getMessage());
	}

	/**
	 * Un HttpResult.仅仅只返回包装类中需要返回的对象
	 *
	 * @param <E>     the element type
	 * @param HttpResult the HttpResult
	 *
	 * @return the e
	 */
	public static <E> E unWrap(HttpResult<E> HttpResult) {
		return HttpResult.getResult();
	}

	/**
	 * Wrap ERROR. code=100
	 *
	 * @param <E> the element type
	 *
	 * @return the HttpResult
	 */
	public static <E> HttpResult<E> illegalArgument() {
		return wrap(HttpResultMassage.ILLEGAL_ARGUMENT_CODE_, HttpResultMassage.ILLEGAL_ARGUMENT_MESSAGE);
	}

	/**
	 * Wrap ERROR. code=500
	 *
	 * @param <E> the element type
	 *
	 * @return the HttpResult
	 */
	public static <E> HttpResult<E> error() {
		return wrap(HttpResultMassage.ERROR_CODE, HttpResultMassage.ERROR_MESSAGE);
	}


	/**
	 * Error HttpResult.
	 *
	 * @param <E>     the type parameter
	 * @param message the message
	 *
	 * @return the HttpResult
	 */
	public static <E> HttpResult<E> error(String message) {
		return wrap(HttpResultMassage.ERROR_CODE, StringUtils.isBlank(message) ? HttpResultMassage.ERROR_MESSAGE : message);
	}

	/**
	 * Wrap SUCCESS. code=200
	 *
	 * @param <E> the element type
	 *
	 * @return the HttpResult
	 */
	public static <E> HttpResult<E> ok() {
		return new HttpResult<>();
	}

	/**
	 * Ok HttpResult.
	 *
	 * @param <E> the type parameter
	 * @param o   the o
	 *
	 * @return the HttpResult
	 */
	public static <E> HttpResult<E> ok(E o) {
		return new HttpResult<>(HttpResultMassage.SUCCESS_CODE, HttpResultMassage.SUCCESS_MESSAGE, o);
	}

}
