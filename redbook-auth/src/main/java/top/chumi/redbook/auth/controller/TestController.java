package top.chumi.redbook.auth.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
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
    @GetMapping("/test")
    @ApiOperationLog(description = "测试接口")
    public Response<String> test() {
        return Response.success("测试成功1");
    }

    @GetMapping("/test2")
    @ApiOperationLog(description = "测试接口2")
    public Response<User> test2() {
        return Response.success(User.builder().nickName("MingHu").createTime(LocalDateTime.now()).build());
    }
    @PostMapping("/test3")
    @ApiOperationLog(description = "测试接口3")
    public Response<User> test3(@RequestBody User user) {
        return Response.success(user);
    }
}
