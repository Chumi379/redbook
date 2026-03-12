package top.chumi.redbook.auth.service.impl;

import cn.dev33.satoken.stp.SaTokenInfo;
import cn.dev33.satoken.stp.StpUtil;
import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionTemplate;
import top.chumi.framework.common.enums.DeletedEnum;
import top.chumi.framework.common.enums.StatusEnum;
import top.chumi.framework.common.exception.BizException;
import top.chumi.framework.common.response.Response;
import top.chumi.framework.common.util.JsonUtils;
import top.chumi.redbook.auth.constant.RedisKeyConstants;
import top.chumi.redbook.auth.constant.RoleConstants;
import top.chumi.redbook.auth.domain.dataobject.UserDO;
import top.chumi.redbook.auth.domain.dataobject.UserRoleDO;
import top.chumi.redbook.auth.domain.mapper.UserDOMapper;
import top.chumi.redbook.auth.domain.mapper.UserRoleDOMapper;
import top.chumi.redbook.auth.enums.LoginTypeEnum;
import top.chumi.redbook.auth.enums.ResponseCodeEnum;
import top.chumi.redbook.auth.model.vo.user.UserLoginReqVO;
import top.chumi.redbook.auth.service.UserService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

/**
 *  @author MingHu
 *  @Date 2026/3/10 16:17
 *  @Description: TODO
 */
@Service
@Slf4j
public class UserServiceImpl implements UserService {
	@Resource
	private RedisTemplate<String, Object> redisTemplate;

	@Resource
	private TransactionTemplate transactionTemplate;

	@Resource
	private UserDOMapper userDOMapper;

	@Resource
	private UserRoleDOMapper userRoleDOMapper;

	@Override
	public Response<String> loginAndRegister(UserLoginReqVO userLoginReqVO) {

		String phone = userLoginReqVO.getPhone();
		Integer type = userLoginReqVO.getType();
		LoginTypeEnum loginTypeEnum = LoginTypeEnum.valueOf(type);

		Long userId = null;
		switch (loginTypeEnum) {
			case VERIFICATION_CODE:
				String verificationCode = userLoginReqVO.getCode();
				//使用Guava
				Preconditions.checkArgument(StringUtils.isNotBlank(verificationCode),"验证码不能为空");
				String key = RedisKeyConstants.buildVerificationCodeKey(phone);
				String sentCode = (String) redisTemplate.opsForValue().get(key);
				if (!StringUtils.equals(sentCode, verificationCode)) {
					throw new BizException(ResponseCodeEnum.VERIFICATION_CODE_ERROR);
				}
				UserDO userDO = userDOMapper.selectByPhone(phone);
				log.info("==> 用户是否注册,phone: {},userDO: {}", phone, JsonUtils.toJsonString(userDO));
				if (Objects.isNull(userDO)) {
					userId = registerUser(phone);
				} else {
					userId = userDO.getId();
				}
				break;
			case PASSWORD:
				//@TODO 密码登录
				break;
			default:
				break;
		}
		StpUtil.login(userId);
		SaTokenInfo saTokenInfo = StpUtil.getTokenInfo();
		return Response.success(saTokenInfo.tokenValue);
	}

	/**
	 * 系统自动注册用户
	 * @param phone
	 * @return
	 */
	@Transactional(rollbackFor = Exception.class)
	public Long registerUser(String phone) {
		return transactionTemplate.execute(status -> {
			try {
				Long redbookId = redisTemplate.opsForValue().increment(RedisKeyConstants.REDBOOK_ID_GENERATOR_KEY);
				UserDO userDO = UserDO.builder().phone(phone).redbookId(String.valueOf(redbookId))
						.nickname("小红书" + redbookId).status(StatusEnum.ENABLE.getValue())
						.createTime(LocalDateTime.now()).updateTime(LocalDateTime.now())
						.isDeleted(DeletedEnum.NO.getValue()).build();
				userDOMapper.insert(userDO);

				Long userId = userDO.getId();

				UserRoleDO userRoleDO = UserRoleDO.builder().userId(userId).roleId(RoleConstants.COMMON_USER_ROLE_ID)
						.createTime(LocalDateTime.now()).updateTime(LocalDateTime.now())
						.isDeleted(DeletedEnum.NO.getValue()).build();
				userRoleDOMapper.insert(userRoleDO);

				//存入redis
				List<Long> roles = Lists.newArrayList();
				roles.add(RoleConstants.COMMON_USER_ROLE_ID);
				String userRolesKey = RedisKeyConstants.buildUserRoleKey(phone);
				redisTemplate.opsForValue().set(userRolesKey, roles);

				return userId;
			} catch (Exception e) {
				status.setRollbackOnly();
				log.error("==> 注册用户失败,phone: {}", phone, e);
				return null;
			}
		});
	}
}
