package top.chumi.redbook.auth;

import com.alibaba.druid.filter.config.ConfigTools;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author MingHu
 * @Date 2025/6/28 14:26
 * @Description: TODO
 */
@SpringBootTest
@Slf4j
public class DruidTests {
    /**
     * Druid密码加密
     */
    @Test
    @SneakyThrows
    void testEncodePassword() {
        String password = "dsz1314521";
        String[] arr = ConfigTools.genKeyPair(512);
        //私钥
        log.info("私钥：{}", arr[0]);
        //公钥
        log.info("公钥：{}", arr[1]);

        //通过私钥加密密码
        String encodePassword = ConfigTools.encrypt(arr[0], password);
        log.info("加密后的密码：{}", encodePassword);
    }
}
