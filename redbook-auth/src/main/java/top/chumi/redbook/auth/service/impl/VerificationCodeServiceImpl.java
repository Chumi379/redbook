package top.chumi.redbook.auth.service.impl;

//import cn.hutool.core.util.RandomUtil;
import jakarta.annotation.Resource;
import org.springframework.data.redis.core.RedisTemplate;
import top.chumi.framework.common.exception.BizException;
import top.chumi.framework.common.response.Response;
import top.chumi.redbook.auth.constant.RedisKeyConstants;
import top.chumi.redbook.auth.enums.ResponseCodeEnum;
import top.chumi.redbook.auth.model.vo.verificationcode.SendVerificationCodeReqVO;
import top.chumi.redbook.auth.service.VerificationCodeService;

/**
 * @author MingHu
 * @Date 2025/6/30 16:38
 * @Description: TODO
 */
public class VerificationCodeServiceImpl implements VerificationCodeService {

    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    /**
     * 发送短信验证码
     *
     * @param sendVerificationCodeReqVO
     * @return
     */
    @Override
    public Response<?> send(SendVerificationCodeReqVO sendVerificationCodeReqVO) {
        String phone = sendVerificationCodeReqVO.getPhone();

        //构建验证码redis key
        String key = RedisKeyConstants.buildVerificationCodeKey(phone);
        boolean isSent = redisTemplate.hasKey(key);
        if (isSent) {
            throw new BizException(ResponseCodeEnum.VERIFICATION_CODE_SEND_FREQUENTLY);
        }
//        String verificationCode =
        return null;
    }
}
