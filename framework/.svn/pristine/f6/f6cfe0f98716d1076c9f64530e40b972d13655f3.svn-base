package com.framework.comm.log;

import java.util.List;
/**
 * 
 * 接口或类的说明: 写日志工具类接口
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
public interface LogWriter {

	public static final String CFG_CHARSET = "charset";

    /**
     *
     * 描述：批量写入日志
     * @param logStrs
     * @param logName
     * @throws Exception
     */
    public void write(List<String> logStrs, String logName) throws Exception;

   /**
    *
    * 描述：写入单条日志
    * @param logStr
    * @param logName
    * @throws Exception
    */
    public void write(String logStr, String logName) throws Exception;

   /**
    *
    * 描述：清空日志文件中的内容
    * @param logName
    * @throws Exception
    */
    void clear(String logName) throws Exception;

}
