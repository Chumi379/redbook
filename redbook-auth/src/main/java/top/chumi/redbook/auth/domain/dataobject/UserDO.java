package top.chumi.redbook.auth.domain.dataobject;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * @author MingHu
 * @Date 2025/6/24 20:11
 * @Description: TODO
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDO {
    private String id;
    private String username;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}
