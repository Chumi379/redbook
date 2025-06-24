package top.chumi.framework.biz.operationlog.aspect;

import org.springframework.stereotype.Component;

import java.lang.annotation.*;

/**
 * @author MingHu
 * @Date 2025/6/23 17:46
 * @Description: TODO
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
@Documented
@Component
public @interface ApiOperationLog {
    /**
     * API功能描述
     * @return
     */
    String description() default "";
}
