package top.chumi.redbook.auth.sms;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author MingHu
 * @Date 2025/7/7 14:51
 * @Description: TODO
 */
@ConfigurationProperties(prefix = "aliyun")
@Data
@Component
public class AliyunAccessKeyProperties {
    private String accessKeyId;
    private String accessKeySecret;
}
