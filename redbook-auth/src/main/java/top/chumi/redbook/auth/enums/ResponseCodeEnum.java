package top.chumi.redbook.auth.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import top.chumi.framework.common.exception.BaseExceptionInterface;

/**
 * @author MingHu
 * @Date 2025/6/30 14:37
 * @Description: TODO
 */
@Getter
@AllArgsConstructor
public enum ResponseCodeEnum implements BaseExceptionInterface {
    SYSTEM_ERROR("AUTH-10000","系统异常"),

    PARAM_NOT_VALID("AUTH-10001","参数不合法"),

    //---------业务异常状态码----------
    VERIFICATION_CODE_SEND_FREQUENTLY("AUTH-20000", "请求太频繁，请3分站后再试");


    private final String errorCode;
    private final String errorMessage;
}
