package top.chumi.redbook.auth.controller;

import cn.dev33.satoken.stp.StpUtil;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import top.chumi.framework.biz.operationlog.aspect.ApiOperationLog;
import top.chumi.framework.common.response.Response;

import java.time.LocalDateTime;

/**
 * @author MingHu
 * @Date 2025/6/23 17:27
 * @Description: TODO
 */
@RestController
public class TestController {
//    @GetMapping("/test")
//    @ApiOperationLog(description = "测试接口")
//    public Response<String> test() {
//        return Response.success("测试成功1");
//    }
//
//    @GetMapping("/test2")
//    @ApiOperationLog(description = "测试接口2")
//    public Response<User> test2() {
//        return Response.success(User.builder().nickName("MingHu").createTime(LocalDateTime.now()).build());
//    }
//
//    @PostMapping("/test3")
//    @ApiOperationLog(description = "测试接口3")
//    public Response<User> test3(@RequestBody @Validated User user) {
//        return Response.success(user);
//    }
//
//    @GetMapping("/test4")
//    @ApiOperationLog(description = "测试接口4")
//    public void test4() {
//        int i = 1 / 0;
//    }

    @RequestMapping("/user/doLogin")
    public String doLogin(String username, String password) {
        // 此处仅作模拟示例，真实项目需要从数据库中查询数据进行比对
        if("zhang".equals(username) && "123456".equals(password)) {
            StpUtil.login(10001);
            return "登录成功";
        }
        return "登录失败";
    }

    // 查询登录状态，浏览器访问： http://localhost:8080/user/isLogin
    @RequestMapping("/user/isLogin")
    public String isLogin() {
        return "当前会话是否登录：" + StpUtil.isLogin();
    }
}
