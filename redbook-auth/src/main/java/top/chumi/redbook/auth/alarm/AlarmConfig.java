package top.chumi.redbook.auth.alarm;

import com.alibaba.druid.util.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import top.chumi.redbook.auth.alarm.impl.MailAlarmHelper;
import top.chumi.redbook.auth.alarm.impl.SmsAlarmHelper;

/**
 *  @author MingHu
 *  @Date 2026/3/14 18:12
 *  @Description: TODO
 */
public class AlarmConfig {

	@Value("${alarm.type}")
	private String alarmType;

	@Bean
	public AlarmInterface alarmHelper() {
		if (StringUtils.equals("sms", alarmType)) {
			return new SmsAlarmHelper();
		} else if (StringUtils.equals("mail", alarmType)) {
			return new MailAlarmHelper();
		} else {
			throw new IllegalArgumentException("alarm.type is error");
		}
	}
}
