package top.chumi.redbook.auth.controller;

import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import top.chumi.framework.common.response.Response;
import top.chumi.redbook.auth.model.vo.verificationcode.SendVerificationCodeReqVO;
import top.chumi.redbook.auth.service.VerificationCodeService;

/**
 * @author MingHu
 * @Date 2025/7/2 15:15
 * @Description: TODO
 */
@RestController
@Slf4j
public class VerificationCodeController {

    @Resource
    private VerificationCodeService verificationCodeService;

    @PostMapping("/verification/code/send")
    public Response<?> send(@Validated @RequestBody SendVerificationCodeReqVO sendVerificationCodeReqVO) {
        return verificationCodeService.send(sendVerificationCodeReqVO);
    }
}
