package top.chumi.redbook.auth.service;

import top.chumi.framework.common.response.Response;
import top.chumi.redbook.auth.model.vo.user.UserLoginReqVO;

/**
 *  @author MingHu
 *  @Date 2026/3/10 16:15
 *  @Description: TODO
 */
public interface UserService {

	/**
	 * 登录与注册
	 * @param userLoginReqVO
	 * @return
	 */
	Response<String> loginAndRegister(UserLoginReqVO userLoginReqVO);
}
