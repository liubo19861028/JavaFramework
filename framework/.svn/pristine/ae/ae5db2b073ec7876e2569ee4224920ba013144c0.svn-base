package com.framework.comm.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.ResourceBundleMessageSource;

/**
 * 
 * 接口或类的说明: 配置文件信息持有器
 * 
 *
 * <br>
 * ========================== <br>
 * 公司：南京壹号家信息科技有限公司 <br>
 * 开发：yisheng.lu@yihaojiaju.com <br>
 * 版本：1.0 <br>
 * 创建时间：2017-11-15 上午9:25:35 <br>
 * ==========================
 *
 */
public class MessageHolder {
	private static Logger logger = LoggerFactory.getLogger(MessageHolder.class);

	// 读入配置文件资源
	private static ResourceBundleMessageSource messageSource;

	public MessageHolder() {

	}

	public MessageHolder(ResourceBundleMessageSource messageSource) {
		init(messageSource);
	}

	public ResourceBundleMessageSource getMessageSource() {
		return messageSource;
	}

	public void setMessageSource(ResourceBundleMessageSource messageSource) {
		init(messageSource);
	}

	private static void init(ResourceBundleMessageSource messageSource) {
		MessageHolder.messageSource = messageSource;
	}

	/**
	 * 读出配置文件megKey对应的值
	 * 
	 * @param megKey
	 * @return
	 */
	public static String getMessage(String megKey) {
		return getMessage(megKey, null);
	}

	/**
	 * 读取消息，将args中的内容替换掉消息中的占位符
	 * 
	 * @param megKey
	 * @param args
	 * @return
	 */
	public static String getMessage(String megKey, Object[] args) {
		if (megKey == null)
			return null;
		if (messageSource == null) {
			logger.error("MessageHolder未正确实例化！");
			return null;
		}
		return messageSource.getMessage(megKey, args, null);
	}
}
