package top.chumi.redbook.auth.controller;

import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.chumi.framework.biz.operationlog.aspect.ApiOperationLog;
import top.chumi.framework.common.response.Response;
import top.chumi.redbook.auth.model.vo.user.UserLoginReqVO;
import top.chumi.redbook.auth.service.UserService;

/**
 *  @author MingHu
 *  @Date 2026/3/10 16:24
 *  @Description: TODO
 */
@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {
	@Resource
	private UserService userService;

	@PostMapping("/login")
	@ApiOperationLog(description = "用户登录")
	public Response<String> loginAndRegister(@Validated @RequestBody UserLoginReqVO userLoginReqVO) {
		return userService.loginAndRegister(userLoginReqVO);
	}

}

