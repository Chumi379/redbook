package top.chumi.redbook.auth.alarm.impl;

import lombok.extern.slf4j.Slf4j;
import top.chumi.redbook.auth.alarm.AlarmInterface;

/**
 *  @author MingHu
 *  @Date 2026/3/14 18:06
 *  @Description: TODO
 */
@Slf4j
public class MailAlarmHelper implements AlarmInterface {
	/**
	 * 发送报警信息
	 * @param message
	 * @return
	 */
	@Override
	public boolean send(String message) {
		log.info("==> 【邮件告警】: {}", message);
		return true;
	}
}
