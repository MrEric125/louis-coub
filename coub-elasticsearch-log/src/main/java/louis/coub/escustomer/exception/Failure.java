package louis.coub.escustomer.exception;

import com.alibaba.fastjson.JSONObject;

import java.io.Serializable;

/**
 * @author Louis
 * @date 2020-06-07 20:14
 */
public final class Failure implements Serializable {

    private String code;
    private String msg;
    private JSONObject detail;
    private String requestId;

    public Failure(String code, String msg, JSONObject detail) {
        this.code = code;
        this.msg = msg;
        this.detail = detail;
    }

    public Failure(String code, String msg) {
        this.code = code;
        this.msg = msg;
        this.detail = new JSONObject();
    }

    public String getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public JSONObject getDetail() {
        return detail;
    }

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

}
