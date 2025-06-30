package top.chumi.redbook.auth.service;

import top.chumi.framework.common.response.Response;
import top.chumi.redbook.auth.model.vo.verificationcode.SendVerificationCodeReqVO;

/**
 * @author MingHu
 * @Date 2025/6/30 16:37
 * @Description: TODO
 */
public interface VerificationCodeService {
    /**
     * 发送验证码
     *
     * @param sendVerificationCodeReqVO
     */
    Response<?> send(SendVerificationCodeReqVO sendVerificationCodeReqVO);
}
