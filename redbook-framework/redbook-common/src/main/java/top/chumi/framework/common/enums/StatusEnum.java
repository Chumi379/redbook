package top.chumi.framework.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 *  @author MingHu
 *  @Date 2026/3/10 16:27
 *  @Description: 状态枚举类
 */
@Getter
@AllArgsConstructor
public enum StatusEnum {
	// 启用
	ENABLE(0),
	// 禁用
	DISABLED(1);

	private final Integer value;
}
