package top.chumi.redbook.auth;

import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.nio.charset.StandardCharsets;
import java.util.Properties;
import java.util.Set;

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

	@Resource
	private RedisConnectionFactory redisConnectionFactory;

	@Resource
	private StringRedisTemplate stringRedisTemplate;

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


	@Test
	void testRedisConnection() {
		try {
			// 1. 测试连接
			RedisConnection connection = redisConnectionFactory.getConnection();
			String pingResult = connection.ping();
			log.info("Redis Ping 结果: {}", pingResult);

			// 2. 获取 Redis 服务器信息
			Properties info = connection.info();
			log.info("Redis版本: {}", info.getProperty("redis_version"));
			log.info("已用内存: {}", info.getProperty("used_memory_human"));
			log.info("当前数据库键数量: {}", connection.dbSize());

			// 3. 查看所有键（包括序列化后的键）
			Set<byte[]> allKeys = connection.keys("*".getBytes(StandardCharsets.UTF_8));
			log.info("当前数据库中的键数量: {}", allKeys.size());
			for (byte[] key : allKeys) {
				log.info("键(原始字节): {}", new String(key, StandardCharsets.UTF_8));
			}

			connection.close();
		} catch (Exception e) {
			log.error("Redis连接测试失败: {}", e.getMessage(), e);
		}
	}

	@Test
	void testDataOperations() {
		// 测试字符串操作
		stringRedisTemplate.opsForValue().set("test:key1", "value1");
		stringRedisTemplate.opsForValue().set("test:key2", "value2");

		// 读取
		String val1 = stringRedisTemplate.opsForValue().get("test:key1");
		String val2 = stringRedisTemplate.opsForValue().get("test:key2");

		log.info("读取 test:key1 = {}", val1);
		log.info("读取 test:key2 = {}", val2);

		// 查看所有以 test: 开头的键
		Set<String> keys = stringRedisTemplate.keys("test:*");
		log.info("所有以 test: 开头的键: {}", keys);
	}
}
