package top.chumi.redbook.auth.alarm;

/**
 *  @author MingHu
 *  @Date 2026/3/14 17:57
 *  @Description: TODO
 */
public interface AlarmInterface {

	/**
	 * 发送报警信息
	 * @param message
	 * @return
	 */
	boolean send(String message);
}
