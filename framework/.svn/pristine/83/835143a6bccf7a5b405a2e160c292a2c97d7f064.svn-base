package com.framework.comm.log.impl;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.framework.comm.log.LogWriter;
import com.framework.comm.util.StringUtil;

 

 
/**
 * 
 * 接口或类的说明:  写文件日志工具类接口
 *
 * <br>==========================
 * <br> 公司：南京壹号家信息科技有限公司
 * <br> 开发：yisheng.lu@yihaojiaju.com
 * <br> 版本：1.0
 * <br> 创建时间：2017-11-15 上午9:25:35
 * <br>==========================
 *
 */
public class FileLogWriter implements LogWriter {
	protected final Logger logger = LoggerFactory.getLogger(getClass());

	public static final String CFG_FILE_PATH = "filePath";

	/**
	 * 生成的日志文件的编码，默认为GBK
	 */
	private String charset = "GBK";
	/**
	 * 日志文件路径
	 */
	private String filePath;
	/**
	 * 是否追加
	 */
	private boolean append = true;

	public void setCharset(String charset) {
		this.charset = charset;
	}

	public void setFilePath(String filePath) {
		if (!filePath.endsWith("/"))
			filePath += "/";
		this.filePath = filePath;
	}

	public void setAppend(boolean append) {
		this.append = append;
	}

	public void write(List<String> logs, String logName) throws Exception {
		String fileName = getFileName(logName);
		PrintWriter writer = null;
		OutputStreamWriter osw = null;
		FileOutputStream fos = null;
		try {
			fos = new FileOutputStream(fileName, append);
			osw = new OutputStreamWriter(fos, charset);
			writer = new PrintWriter(osw);
			if (logs.isEmpty()) {
				writer.print("");
				return;
			}
			for (String log : logs) {
				if (StringUtil.isEmpty(log))
					continue;
				writer.println(log);
			}
		} catch (IOException e) {
			logger.error("日志写入失败，日志文件名：" + fileName + "\t日志内容是：" + logs, e);
			throw e;
		} finally {
			if (writer != null)
				writer.close();
			if (osw != null)
				osw.close();
			if (fos != null)
				fos.close();
		}
	}

	public void write(String log, String logName) throws Exception {
		List<String> logs = new ArrayList<String>();
		logs.add(log);
		this.write(logs, logName);
	}

	public void clear(String logName) throws Exception {
		String fileName = getFileName(logName);
		FileOutputStream erasor = new FileOutputStream(new File(fileName));
		erasor.write(("").getBytes(charset));
		erasor.close();
	}

	/**
	 * 得到文件名
	 * 
	 * @param logName
	 * @return
	 */
	protected String getFileName(String logName) {
		String fileName = null;
		if (logName.endsWith(".log"))
			fileName = filePath + logName;
		else
			fileName = filePath + logName + ".log";
		return fileName;
	}

}
