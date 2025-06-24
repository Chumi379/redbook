package top.chumi.redbook.auth;

import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import top.chumi.framework.common.util.JsonUtils;
import top.chumi.redbook.auth.domain.dataobject.UserDO;
import top.chumi.redbook.auth.domain.mapper.UserDOMapper;

import java.time.LocalDateTime;

@SpringBootTest
@Slf4j
class RedookAuthApplicationTests {
    @Resource
    private UserDOMapper userDOMapper;

    @Test
    void testInsert() {
        UserDO userDO = UserDO.builder()
                .username("dongshizhao")
                .createTime(LocalDateTime.now())
                .updateTime(LocalDateTime.now())
                .build();
        userDOMapper.insert(userDO);
    }

    @Test
    void testSelect() {
        UserDO userDO = userDOMapper.selectByPrimaryKey(1L);
        log.info("userDO: {}", JsonUtils.toJsonString(userDO));
    }

    @Test
    void testUpdate() {
        UserDO userDO = userDOMapper.selectByPrimaryKey(1L);
        userDO.setUsername("Dongshizhao");
        userDO.setUpdateTime(LocalDateTime.now());
        userDOMapper.updateByPrimaryKey(userDO);
    }

    @Test
    void testDelete() {
        userDOMapper.deleteByPrimaryKey(1L);
    }
}
