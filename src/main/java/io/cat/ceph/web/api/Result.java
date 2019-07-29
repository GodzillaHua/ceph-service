package io.cat.ceph.web.api;

/**
 * @author GodzillaHua
 **/
public class Result<T> {

    private static final Integer DEFAULT_SUCCESS_CODE = 200;
    private static final Integer DEFAULT_ERROR_CODE = 500;

    private Integer code;
    private String message;
    private T data;

    public Result() {
    }

    public Result(Integer code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public static <T> Result<T> ok() {
        return ok(null, null);
    }

    public static <T> Result<T> ok(String message) {
        return ok(message, null);
    }

    public static <T> Result<T> ok(String message, T data) {
        return result(DEFAULT_SUCCESS_CODE, message, data);
    }

    public static <T> Result<T> error(String message) {
        return result(DEFAULT_ERROR_CODE, message, null);
    }

    static <T> Result<T> result(Integer code, String message, T data) {
        Result<T> result = new Result<>();
        result.setCode(DEFAULT_SUCCESS_CODE);
        result.setMessage(message);
        result.setData(data);
        return result;
    }
}
