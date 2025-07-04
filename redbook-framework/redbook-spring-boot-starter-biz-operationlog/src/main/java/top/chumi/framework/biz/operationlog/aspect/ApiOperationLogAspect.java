package top.chumi.framework.biz.operationlog.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import top.chumi.framework.common.util.JsonUtils;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author MingHu
 * @Date 2025/6/23 17:47
 * @Description: TODO
 */
@Aspect
@Slf4j
public class ApiOperationLogAspect {
    @Pointcut("@annotation(top.chumi.framework.biz.operationlog.aspect.ApiOperationLog)")
    public void apiOperationLog() {
    }

    @Around("apiOperationLog()")
    public Object doAround(ProceedingJoinPoint joinPoint) throws Throwable {
        //请求开始的时间
        long startTime = System.currentTimeMillis();

        //获取被请求的类和方法
        String className = joinPoint.getTarget().getClass().getSimpleName();
        String methodName = joinPoint.getSignature().getName();
        //获取参数
        Object[] args = joinPoint.getArgs();
        //入参转json
        String argsJsonStr = Arrays.stream(args).map(toJsonStr()).collect(Collectors.joining(", "));
        String description = getApiOperationLogDescription(joinPoint);
        log.info("====== 请求开始: [{}], 入参: {}, 请求类: {}, 请求方法: {} =================================== ",
                description, argsJsonStr, className, methodName);
        // 执行切点方法
        Object result = joinPoint.proceed();
        // 执行耗时
        long executionTime = System.currentTimeMillis() - startTime;
        // 打印出参等相关信息
        log.info("====== 请求结束: [{}], 耗时: {}ms, 出参: {} =================================== ",
                description, executionTime, JsonUtils.toJsonString(result));
        return joinPoint.proceed();
    }

    /**
     * 获取注解的描述信息
     * @param joinPoint
     * @return
     */
    private String getApiOperationLogDescription(ProceedingJoinPoint joinPoint) {
        // 1. 从 ProceedingJoinPoint 获取 MethodSignature
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();

        // 2. 使用 MethodSignature 获取当前被注解的 Method
        Method method = signature.getMethod();

        // 3. 从 Method 中提取 LogExecution 注解
        ApiOperationLog apiOperationLog = method.getAnnotation(ApiOperationLog.class);

        // 4. 从 LogExecution 注解中获取 description 属性
        return apiOperationLog.description();
    }

    /**
     * 转 JSON 字符串
     * @return
     */
    private Function<Object, String> toJsonStr() {
        return JsonUtils::toJsonString;
    }
}
