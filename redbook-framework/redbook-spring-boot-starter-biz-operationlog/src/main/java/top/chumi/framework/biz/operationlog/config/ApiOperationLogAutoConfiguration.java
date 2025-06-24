package top.chumi.framework.biz.operationlog.config;

import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.context.annotation.Bean;
import top.chumi.framework.biz.operationlog.aspect.ApiOperationLogAspect;

/**
 * @author MingHu
 * @Date 2025/6/24 14:59
 * @Description: TODO
 */
@AutoConfiguration
public class ApiOperationLogAutoConfiguration {
    @Bean
    public ApiOperationLogAspect apiOperationLogAspect() {
        return new ApiOperationLogAspect();
    }
}
