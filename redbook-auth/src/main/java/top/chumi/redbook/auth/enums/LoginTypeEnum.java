package top.chumi.redbook.auth.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Objects;

/**
 *  @author MingHu
 *  @Date 2026/3/10 16:12
 *  @Description: TODO
 */
@Getter
@AllArgsConstructor
public enum LoginTypeEnum {
	//验证码
	VERIFICATION_CODE(1),
	//密码
	PASSWORD(2);

	private final Integer value;

	public static LoginTypeEnum valueOf(Integer code) {
		for (LoginTypeEnum loginTypeEnum : LoginTypeEnum.values()) {
			if (Objects.equals(code, loginTypeEnum.getValue())) {
				return loginTypeEnum;
			}
		}
		return null;
	}
}
