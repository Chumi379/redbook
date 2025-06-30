package top.chumi.redbook.auth.constant;

/**
 * @author MingHu
 * @Date 2025/6/30 16:33
 * @Description: TODO
 */
public class RedisKeyConstants {
    /**
     * 验证码Key前缀
     */
    private static final String VERIFICATION_CODE_KEY_PREFIX = "verification_code:";

    /**
     * 构建验证码 Key
     *
     * @param phone
     * @return
     */
    public static String buildVerificationCodeKey(String phone) {
        return VERIFICATION_CODE_KEY_PREFIX + phone;
    }
}
