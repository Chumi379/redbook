package top.chumi.redbook.auth.controller;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * @author MingHu
 * @Date 2025/6/24 16:35
 * @Description: TODO
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User {
    /**
     * 昵称
     */
    private String nickName;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;
}
