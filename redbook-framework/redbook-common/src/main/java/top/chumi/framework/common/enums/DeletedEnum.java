package top.chumi.framework.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 *  @author MingHu
 *  @Date 2026/3/10 16:26
 *  @Description: 逻辑删除枚举类
 */
@Getter
@AllArgsConstructor
public enum DeletedEnum {
	YES(true),
	NO(false);

	private final Boolean value;
}
