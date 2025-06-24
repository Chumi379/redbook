package top.chumi.framework.common.response;

import lombok.Data;
import top.chumi.framework.common.exception.BaseExceptionInterface;
import top.chumi.framework.common.exception.BizException;

import java.io.Serializable;

/**
 * @author MingHu
 * @Date 2025/6/23 17:04
 * @Description 响应结果工具类
 */

@Data
public class Response<T> implements Serializable {
    //是否成功，默认为true
    private boolean success = true;
    //异常码
    private String errorCode;
    //响应消息
    private String message;
    //响应数据
    private T data;

    //响应成功
    public static <T> Response<T> success() {
        return new Response<>();
    }

    public static <T> Response<T> success(T data) {
        Response<T> response = new Response<>();
        response.setData(data);
        return response;
    }

    //响应失败
    public static <T> Response<T> fail() {
        Response<T> response = new Response<>();
        response.setSuccess(false);
        return response;
    }

    public static <T> Response<T> fail(String message) {
        Response<T> response = new Response<>();
        response.setSuccess(false);
        response.setMessage(message);
        return response;
    }

    public static <T> Response<T> fail(String errorCode, String message) {
        Response<T> response = new Response<>();
        response.setSuccess(false);
        response.setMessage(message);
        response.setErrorCode(errorCode);
        return response;
    }
    public static <T> Response<T> fail(BizException bizException) {
        Response<T> response = new Response<>();
        response.setSuccess(false);
        response.setMessage(bizException.getErrorMessage());
        response.setErrorCode(bizException.getErrorCode());
        return response;
    }
    public static <T> Response<T> fail(BaseExceptionInterface baseExceptionInterface) {
        Response<T> response = new Response<>();
        response.setSuccess(false);
        response.setMessage(baseExceptionInterface.getErrorMessage());
        response.setErrorCode(baseExceptionInterface.getErrorCode());
        return response;
    }
}
