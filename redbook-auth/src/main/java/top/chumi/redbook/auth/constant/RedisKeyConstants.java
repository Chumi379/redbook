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
	 * 用户角色数据 KEY 前缀
	 */
	private static final String USER_ROLES_KEY_PREFIX = "user:roles:";

	private static final String ROLE_PERMISSIONS_KEY_PREFIX = "role:permissions:";

	public static final String REDBOOK_ID_GENERATOR_KEY = "redbook:id:generator";


    /**
     * 构建验证码 Key
     *
     * @param phone
     * @return
     */
    public static String buildVerificationCodeKey(String phone) {
        return VERIFICATION_CODE_KEY_PREFIX + phone;
    }

	/**
	 * 构建用户-角色 Key
	 * @param phone
	 * @return
	 */
	public static String buildUserRoleKey(String phone) {
		return USER_ROLES_KEY_PREFIX + phone;
	}

	/**
	 * 构建角色对应的权限集合 Key
	 * @param roleId
	 * @return
	 */
	public static String buildRolePermissionKey(Long roleId) {
		return ROLE_PERMISSIONS_KEY_PREFIX + roleId;
	}


}
