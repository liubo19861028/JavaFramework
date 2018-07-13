package com.framework.comm.log.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.framework.comm.log.LogWriter;
import com.framework.comm.util.StringUtil;

 

 
/**
 * 
 * 接口或类的说明:  日志异步输出器
 *
 * <br>==========================
 * <br> 公司：南京壹号家信息科技有限公司
 * <br> 开发：yisheng.lu@yihaojiaju.com
 * <br> 版本：1.0
 * <br> 创建时间：2017-11-15 上午9:25:35
 * <br>==========================
 *
 */
public class LogAsyncWriter implements LogWriter {

    protected final Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * 缓存日志的队列集合
     */
    private Map<String, BlockingQueue<String>> queues = new HashMap<String, BlockingQueue<String>>();

    /**
     * 添加日志到队列中的线程池
     */
    private ThreadPoolExecutor putToQueueThreadPool;
    /**
     * 线程池维护线程的最少数量
     */
    private int putToQueueCorePoolSize = 2;
    /**
     * 线程池维护线程的最大数量
     */
    private int putToQueueMaxPoolSize = 20;
    /**
     * 线程池维护线程所允许的空闲时间
     */
    private int putToQueueKeepAliveTime = 0;
    /**
     * 线程池所使用的缓冲队列数量
     */
    private int putToQueueWorkQueueSize = 10;
    /**
     * 写文件调度器
     **/
    private ScheduledThreadPoolExecutor asyncWriterScheduler;
    /**
     * 写文件调度器的线程池大小，默认为5
     **/
    private int asyncWriterPoolSize = 5;
    /**
     * 写文件调度器的间隔时间，默认为6秒
     **/
    private int asyncWriterPeriod = 6;

    /**
     * 日志输出器接口
     */
    private LogWriter logWriter;

    public void setPutToQueueThreadPool(ThreadPoolExecutor putToQueueThreadPool) {
        this.putToQueueThreadPool = putToQueueThreadPool;
    }

    public void setPutToQueueCorePoolSize(int putToQueueCorePoolSize) {
        this.putToQueueCorePoolSize = putToQueueCorePoolSize;
    }

    public void setPutToQueueMaxPoolSize(int putToQueueMaxPoolSize) {
        this.putToQueueMaxPoolSize = putToQueueMaxPoolSize;
    }

    public void setPutToQueueKeepAliveTime(int putToQueueKeepAliveTime) {
        this.putToQueueKeepAliveTime = putToQueueKeepAliveTime;
    }

    public void setPutToQueueWorkQueueSize(int putToQueueWorkQueueSize) {
        this.putToQueueWorkQueueSize = putToQueueWorkQueueSize;
    }

    public void setAsyncWriterPoolSize(int asyncWriterPoolSize) {
        this.asyncWriterPoolSize = asyncWriterPoolSize;
    }

    public void setAsyncWriterPeriod(int asyncWriterPeriod) {
        this.asyncWriterPeriod = asyncWriterPeriod;
    }

    public LogAsyncWriter(LogWriter logWriter) {
        super();
        this.logWriter = logWriter;
    }

    /**
     * 初始化
     */
    public void init() {
        if (putToQueueThreadPool == null) {
            putToQueueThreadPool = new ThreadPoolExecutor(putToQueueCorePoolSize, putToQueueMaxPoolSize, putToQueueKeepAliveTime, TimeUnit.SECONDS,
                    new ArrayBlockingQueue<Runnable>(putToQueueWorkQueueSize), new ThreadPoolExecutor.CallerRunsPolicy());
        } else {
            putToQueueThreadPool.setCorePoolSize(putToQueueCorePoolSize);
            putToQueueThreadPool.setKeepAliveTime(putToQueueKeepAliveTime, TimeUnit.SECONDS);
            putToQueueThreadPool.setMaximumPoolSize(putToQueueMaxPoolSize);
        }

        asyncWriterScheduler = new ScheduledThreadPoolExecutor(asyncWriterPoolSize);
        // 当关闭容器时继续执行现有定期任务
        asyncWriterScheduler.setContinueExistingPeriodicTasksAfterShutdownPolicy(true);
        asyncWriterScheduler.scheduleWithFixedDelay(new Runnable() {
            public void run() {
                doExecuteToWriter();
            }
        }, 30, asyncWriterPeriod, TimeUnit.SECONDS);
    }

    /**
     * 从队列中取出所有的日志来执行写入操作
     */
    private void doExecuteToWriter() {
        Set<String> keys = queues.keySet();
        for (String logName : keys) {
            List<String> contents = drainTo(logName);
            doExecuteToWriter(contents, logName);
        }
    }

    /**
     * 执行写操作
     * @param logs
     * @param logName
     */
    protected void doExecuteToWriter(List<String> logs, String logName) {
        try {
            logWriter.write(logs, logName);
        } catch (Throwable e) {
            logger.error("输出日志失败，logName[{}]", logName);
            try {
                this.write(logs, logName);
            } catch (Exception e1) {
                logger.error("将输出失败的日志再次放入队列时出现异常！", e1);
            }
        }
    }

    /**
     * 服务关闭，flush线程池任务，令其执行完，并关闭线程池（spring配置）
     */
    public void destroy() {
        if (putToQueueThreadPool != null && !putToQueueThreadPool.isShutdown()) {
            putToQueueThreadPool.shutdown();
        }
        if (asyncWriterScheduler != null && !asyncWriterScheduler.isShutdown()) {
            asyncWriterScheduler.shutdown();
        }
    }

    /**
     * 将日志放入消息队列中
     */
    public void write(final String log, final String logName) throws Exception {
        if (StringUtil.isEmpty(log)) {
            logger.debug("日志内容为空！logName: {}", logName);
            return;
        }
        putToQueueThreadPool.execute(new Runnable() {
            public void run() {
                putToQueues(logName, log);
            }
        });
    }

    public void write(List<String> logs, String logName) throws Exception {
        if (logs == null || logs.isEmpty())
            return;
        for (String log : logs) {
            this.write(log, logName);
        }
    }

    public void clear(String logName) throws Exception {
        logWriter.clear(logName);
    }

    /**
     * 将日志信息放到队列中
     * @param logName
     * @param contents
     * @throws InterruptedException
     */
    protected void putToQueues(final String logName, final String log) {
        BlockingQueue<String> logQueue = getLogQueue(logName);
        try {
            if (!logQueue.offer(log, asyncWriterPeriod * 2, TimeUnit.SECONDS)) {
                logger.info("添加失败！{}", log);
            }
        } catch (InterruptedException e) {
            logger.error("将日志放入队列时出错！日志文件名［" + logName + "］日志内容［" + log + "］", e);
        }
    }

    /**
     * 从队列中取出日志信息
     * @param logName
     * @return
     */
    protected List<String> drainTo(String logName) {
        BlockingQueue<String> logQueue = getLogQueue(logName);
        List<String> logs = new ArrayList<String>();
        logQueue.drainTo(logs);
        return logs;
    }

    /**
     * 返回队列
     * @param logName
     * @return
     */
    protected synchronized BlockingQueue<String> getLogQueue(String logName) {
        BlockingQueue<String> queue = queues.get(logName);
        if (queue == null) {
            logger.debug("创建［{}］的队列", logName);
            queue = new LinkedBlockingQueue<String>();
            queues.put(logName, queue);
        }
        return queue;
    }

    protected void finalize() throws Throwable {
        this.destroy();
        super.finalize();
    }

}