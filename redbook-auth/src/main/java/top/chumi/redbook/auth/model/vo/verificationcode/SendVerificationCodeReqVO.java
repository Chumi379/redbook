package top.chumi.redbook.auth.model.vo.verificationcode;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import top.chumi.framework.common.validator.PhoneNumber;

/**
 * @author MingHu
 * @Date 2025/6/30 16:27
 * @Description: TODO
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SendVerificationCodeReqVO {

    @NotBlank(message = "手机号不能为空")
	@PhoneNumber
    private String phone;
}
