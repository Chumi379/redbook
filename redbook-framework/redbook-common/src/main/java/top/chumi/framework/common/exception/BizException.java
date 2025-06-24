package top.chumi.framework.common.exception;

import lombok.Getter;
import lombok.Setter;

/**
 * @author MingHu
 * @Date 2025/6/23 17:03
 */
@Getter
@Setter
public class BizException extends RuntimeException {
    private String errorCode;
    private String errorMessage;

    public BizException(BaseExceptionInterface baseExceptionInterface) {
        this.errorCode = baseExceptionInterface.getErrorCode();
        this.errorMessage = baseExceptionInterface.getErrorMessage();
    }
}
