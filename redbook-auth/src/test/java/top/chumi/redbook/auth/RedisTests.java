package top.chumi.redbook.auth;

import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

/**
 * @author MingHu
 * @Date 2025/6/30 16:10
 * @Description: TODO
 */
@SpringBootTest
@Slf4j
public class RedisTests {
    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    @Test
    void testSetKeyValue() {
        redisTemplate.opsForValue().set("name", "MingHu");
    }

    /**
     * 获取某个 key 的 value
     */
    @Test
    void testGetValue() {
        log.info("value 值：{}", redisTemplate.opsForValue().get("name"));
    }

    /**
     * 删除某个 key
     */
    @Test
    void testDelete() {
        redisTemplate.delete("name");
    }
}
