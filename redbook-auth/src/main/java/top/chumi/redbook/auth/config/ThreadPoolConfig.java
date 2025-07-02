package top.chumi.redbook.auth.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @author MingHu
 * @Date 2025/7/2 15:28
 * @Description: TODO 自定义线程池，异步线程
 */
@Configuration
public class ThreadPoolConfig {
    @Bean(name = "taskExecutor")
    public Executor taskExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        //核心线程数
        executor.setCorePoolSize(10);
        //最大线程数
        executor.setMaxPoolSize(50);
        //线程池所使用的缓冲队列容量
        executor.setQueueCapacity(200);
        //线程活跃时间（秒）
        executor.setKeepAliveSeconds(30);
        //线程名前缀
        executor.setThreadNamePrefix("AuthExecutor-");
        //拒绝策略：由调用线程处理（一般为主线程）
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        //等待所有任务结束后再关闭线程池
        executor.setWaitForTasksToCompleteOnShutdown(true);
        //设置等待时间，如果超时则强制销毁
        executor.setAwaitTerminationSeconds(60);
        executor.initialize();
        return executor;
    }

}
