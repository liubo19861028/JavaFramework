package com.framework.comm.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

 

/**
 * 
 * 接口或类的说明: 日期处理工具类
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
public class DateUtil {
	/*
	 * mode(模式) (受次影响的是getStringOfDate()方法) 第一种,返回正常的date的字符串(默认的)
	 * 第二种,返回date的当天的开始时间 第三种,返回date的当天的结束时间
	 */
	public final static String defaultMode = "normalOfDate";

	public final static String beginOfDateMode = "beginOfDate";

	public final static String endOfDateMode = "endOfDate";

	/**
	 * 一天的秒数
	 */
	public final static int DAY_SECOND = 24 * 60 * 60;
	/**
	 * 一天的毫秒数
	 */
	public final static long DAY_MILLISECOND = DAY_SECOND * 1000;

	/**
	 * 默认的日期格式字符串 yyyy-MM-dd
	 */
	public final static String exportXlsDateCreateTimeFormat = "yyyy-MM-dd HH:mm";// 创建时间，由于后台几乎所有的创建时间都是用这种形式展示，故设一常量
	public final static String exportXlsDateFormat = "yyyy.MM.dd";// 导出xls文件的文件名中时间部分格式
	public final static String defaultDatePatternStr = "yyyy-MM-dd";
	public final static String chineseDatePatternStr = "yyyy年MM月dd日";
	/**
	 * 默认的日期时间格式字符串 yyyy-MM-dd HH:mm:ss
	 */
	public final static String defaultDateTimePatternStr = "yyyy-MM-dd HH:mm:ss";
	/**
	 * 默认的长日期时间格式字符串 yyyy-MM-dd HH:mm:ss:SSS
	 */
	public final static String defaultLongDateTimePatternStr = "yyyy-MM-dd HH:mm:ss:SSS";

	/**
	 *
	 * 接口或类的说明:
	 *
	 * <br>
	 * ========================== <br>
	 * 公司：南京壹号家信息科技有限公司 <br>
	 * 开发：yisheng.lu@yihaojiaju.com <br>
	 * 版本：1.0 <br>
	 * 创建时间：2012-11-7 上午9:40:09 <br>
	 * ==========================
	 *
	 */
	public enum DatePart {
		/**
		 * 年
		 */
		yy,
		/**
		 * 月
		 */
		MM,
		/**
		 * 日
		 */
		dd,
		/**
		 * 时
		 */
		HH,
		/**
		 * 分
		 */
		mm,
		/**
		 * 秒
		 */
		ss,
		/**
		 * 毫秒
		 */
		SSS
	}

	/**
	 * 把Date.gettime()的值还原回Date
	 * 
	 * @param timeString
	 * @return
	 */
	public static Date getByTime(String timeString) {
		Date date = null;
		if (!StringUtil.isEmpty(timeString))
			date = new Date(Long.valueOf(timeString));
		else
			date = new Date();
		return date;
	}

	/**
	 * 把字符串格式化成日期类型("yyyy-MM-dd HH:mm:ss")
	 * 
	 * @param dateStr
	 * @return
	 * @throws ParseException
	 */
	public static Date convertStr2Date(String dateStr) throws ParseException {
		return parse(dateStr, defaultDateTimePatternStr);
	}

	/**
	 * 把字符串格式化成日期类型
	 * 
	 * @param dateStr
	 * @param pattern
	 *            日期格式，为空时默认为"yyyy-MM-dd HH:mm:ss"格式
	 * @return
	 * @throws ParseException
	 */
	public static Date parse(String dateStr, String pattern) throws ParseException {
		if (pattern == null)
			pattern = defaultDateTimePatternStr;
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
		Date date = simpleDateFormat.parse(dateStr);
		return date;
	}

	/**
	 * 将Date类型的日期转换成"yyyy-MM-dd HH:mm:ss"类型的字符串
	 * 
	 * @param date
	 * @return
	 */
	public static String convertDate2Str(Date date) {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(defaultDateTimePatternStr);
		return simpleDateFormat.format(date);
	}

	/**
	 * 将Date类型的日期转换成指定格式的字符串
	 * 
	 * @param date
	 * @param pattern
	 * @return
	 */
	public static String format(Date date, String pattern) {
		if (pattern == null)
			pattern = defaultDateTimePatternStr;
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
		return simpleDateFormat.format(date);
	}

	/**
	 * 获取指定日期的开始时间
	 * 
	 * @param date
	 * @return
	 */
	public static Date getBeginOfDay(Date date) {
		Calendar cld = Calendar.getInstance();
		cld.setTime(date);
		cld.set(Calendar.HOUR_OF_DAY, 0);
		cld.set(Calendar.MINUTE, 0);
		cld.set(Calendar.SECOND, 0);
		cld.set(Calendar.MILLISECOND, 0);
		return cld.getTime();
	}

	/**
	 * 获取指定日期减天数
	 * 
	 * @param date
	 *            日期
	 * @param day
	 *            减天数
	 * @return
	 */
	public static Date getBeforOfDay(Date date, Integer day) {
		Date previousDate = null;
		Calendar cal = Calendar.getInstance();
		try {
			cal.setTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(convertDate2Str(date)));
			cal.add(Calendar.DATE, day);
			previousDate = (Date) cal.getTime();// 前day天

		} catch (ParseException e) {
			previousDate = new Date();
		}

		return previousDate;
	}

	/**
	 * 获取指定日期的最后时间
	 * 
	 * @param date
	 * @return
	 */
	public static Date getEndOfDay(Date date) {
		Calendar cld = Calendar.getInstance();
		cld.setTime(date);
		cld.set(Calendar.HOUR_OF_DAY, 23);
		cld.set(Calendar.MINUTE, 59);
		cld.set(Calendar.SECOND, 59);
		cld.set(Calendar.MILLISECOND, 999);
		return cld.getTime();
	}

	/**
	 * 方法用途和描述: 取得当前时间的字符串 例如：20080221050416
	 * 
	 * @return
	 */
	public static String getCurrentTimeStr() {
		return format(new Date(), "yyyyMMddHHmmss");
	}

	/**
	 * 方法用途和描述: 取得当前时间的字符串精确到毫秒 例如：20080221050416111
	 * 
	 * @return
	 */
	public static String getCurrentTimeSecdStr() {
		return format(new Date(), "yyyyMMddHHmmssSSS");
	}

	/**
	 * 比较两个日期，忽略时间，如果date1 > date 2，返回1，date1 = date2，返回0，date1 < date2，返回-1
	 * 
	 * @param date1
	 * @param date2
	 * @return
	 */
	public static int compareDateIgnoreTime(Date date1, Date date2) {
		date1 = ignoreTime(date1);
		date2 = ignoreTime(date2);
		return compareDateTime(date1, date2);
	}

	/**
	 * 比较两个时间，忽略分，如果date1 > date 2，返回1，date1 = date2，返回0，date1 < date2，返回-1
	 * 
	 * @param date1
	 * @param date2
	 * @return
	 */
	public static int compareDateTimeIgnoreMinute(Date date1, Date date2) {
		date1 = ignoreMinute(date1);
		date2 = ignoreMinute(date2);
		return compareDateTime(date1, date2);
	}

	/**
	 * 比较两个时间，忽略秒，如果date1 > date 2，返回1，date1 = date2，返回0，date1 < date2，返回-1
	 * 
	 * @param date1
	 * @param date2
	 * @return
	 */
	public static int compareDateTimeIgnoreSecond(Date date1, Date date2) {
		date1 = ignoreSecond(date1);
		date2 = ignoreSecond(date2);
		return compareDateTime(date1, date2);
	}

	/**
	 * 比较两个时间，忽略毫秒，如果date1 > date 2，返回1，date1 = date2，返回0，date1 < date2，返回-1
	 * 
	 * @param date1
	 * @param date2
	 * @return
	 */
	public static int compareDateTimeIgnoreMillisecond(Date date1, Date date2) {
		date1 = ignoreMillisecond(date1);
		date2 = ignoreMillisecond(date2);
		return compareDateTime(date1, date2);
	}

	/**
	 * 比较两个时间，如果date1 > date 2，返回1，date1 = date2，返回0，date1 < date2，返回-1
	 * 
	 * @param date1
	 * @param date2
	 * @return
	 */
	public static int compareDateTime(Date date1, Date date2) {
		if (date1.after(date2))
			return 1;
		if (date1.before(date2))
			return -1;
		return 0;
	}

	/**
	 * 忽略时间，把时、分、秒、毫秒都变为0
	 * 
	 * @param date
	 * @return
	 */
	public static Date ignoreTime(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		return cal.getTime();
	}

	/**
	 * 方法用途和描述: 忽略分，分、秒、毫秒都变为0
	 * 
	 * @param date
	 * @return
	 */
	public static Date ignoreMinute(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		return cal.getTime();
	}

	/**
	 * 方法用途和描述: 忽略秒，秒、毫秒都变为0
	 * 
	 * @param date
	 * @return
	 */
	public static Date ignoreSecond(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		return cal.getTime();
	}

	/**
	 * 方法用途和描述: 忽略毫秒，毫秒变为0
	 * 
	 * @param date
	 * @return
	 */
	public static Date ignoreMillisecond(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.set(Calendar.MILLISECOND, 0);
		return cal.getTime();
	}

	/**
	 * 方法用途和描述: 获取本周的开始时间
	 * 
	 * @param weekFirstDateForm
	 *            一周的第一天由星期几开始，如果为空则取默认的星期一开始
	 * @return
	 */
	public static Date getCurrentWeekBeginDate(Integer weekFirstDateForm) {
		Calendar calendar = Calendar.getInstance();
		boolean isSunday = isSunday(calendar);
		if (weekFirstDateForm == null || weekFirstDateForm < Calendar.SUNDAY || weekFirstDateForm > Calendar.SATURDAY)
			calendar.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);// 默认设置从周一开始
		else
			calendar.set(Calendar.DAY_OF_WEEK, weekFirstDateForm);
		calendar.setTime(ignoreTime(calendar.getTime()));
		if (isSunday)
			calendar.set(Calendar.DAY_OF_MONTH, calendar.get(Calendar.DAY_OF_MONTH) - Calendar.DAY_OF_WEEK);
		return calendar.getTime();
	}

	/**
	 * 方法用途和描述: 获取本周的结束时间
	 * 
	 * @param weekFirstDateForm
	 *            一周的第一天由星期几开始，如果为空则取默认的星期一开始
	 * @return
	 */
	public static Date getCurrentWeekEndDate(Integer weekFirstDateForm) {
		Calendar calendar = Calendar.getInstance();
		boolean isSunday = isSunday(calendar);
		if (weekFirstDateForm == null || weekFirstDateForm < Calendar.SUNDAY || weekFirstDateForm > Calendar.SATURDAY)
			calendar.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);// 设置从周一开始
		else
			calendar.set(Calendar.DAY_OF_WEEK, weekFirstDateForm);
		calendar.setTime(ignoreTime(calendar.getTime()));
		if (!isSunday)
			calendar.set(Calendar.DAY_OF_MONTH, calendar.get(Calendar.DAY_OF_MONTH) + Calendar.DAY_OF_WEEK);
		calendar.set(Calendar.MILLISECOND, -1);
		return calendar.getTime();
	}

	/**
	 * 方法用途和描述: 是否是星期天
	 * 
	 * @param date
	 * @return
	 */
	public static boolean isSunday(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY;
	}

	/**
	 * 方法用途和描述: 是否是星期天
	 * 
	 * @param calendar
	 * @return
	 */
	public static boolean isSunday(Calendar calendar) {
		return calendar.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY;
	}

	/**
	 * 将yyyy-MM-dd HH:mm:ss:SSS类型的日期转换成yyyy-MM-dd类型的日期对象
	 * 
	 * @param date
	 * @return
	 * @throws ParseException
	 */
	public static Date convertLongDateTimeToDate(Date date) {
		try {
			return parse(format(date, defaultLongDateTimePatternStr), defaultDatePatternStr);
		} catch (ParseException e) {
			// will never happen
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 在指定的日期上添加一天
	 * 
	 * @param date
	 * @return
	 */
	public static Date addOneDay(Date date) {
		return addDate(DatePart.dd, 1, date);
	}

	/**
	 * 在指定的日期上添加指定天数
	 * 
	 * @param date
	 *            需要加减年、月、日的日期对象
	 * @param number
	 *            加减因子
	 * @return
	 */
	public static Date addDays(Date date, int number) {
		return addDate(DatePart.dd, number, date);
	}

	/**
	 * 把日期对象加减年、月、日、小时、分钟后得到新的日期对象
	 * 
	 * @param depart
	 *            年、月、日、小时、分钟
	 * @param number
	 *            加减因子
	 * @param date
	 *            需要加减的日期对象
	 * @return 新的日期对象
	 */
	public static Date addDate(DatePart datepart, int number, Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		if (DatePart.yy == datepart) {
			cal.add(Calendar.YEAR, number);
		} else if (DatePart.MM == datepart) {
			cal.add(Calendar.MONTH, number);
		} else if (DatePart.dd == datepart) {
			cal.add(Calendar.DATE, number);
		} else if (DatePart.HH == datepart) {
			cal.add(Calendar.HOUR, number);
		} else if (DatePart.mm == datepart) {
			cal.add(Calendar.MINUTE, number);
		} else if (DatePart.ss == datepart) {
			cal.add(Calendar.SECOND, number);
		} else if (DatePart.SSS == datepart) {
			cal.add(Calendar.MILLISECOND, number);
		} else {
			throw new IllegalArgumentException("addDate(DatePart, int, Date)方法参数非法: " + datepart);
		}
		return cal.getTime();
	}

	/**
	 *
	 * @param dateStr
	 *            格式为yy-MM
	 * @return
	 * @throws ParseException
	 */
	public static Date getEndDateOfMonth(String dateStr) throws ParseException {

		String dateString = dateStr + "-01";
		Date date = DateUtil.parse(dateString, DateUtil.defaultDatePatternStr);
		date = addMonth(date, 1);
		Date endDateOfDay = DateUtil.getEndOfDay(date);
		Date endDateOfMonth = DateUtil.addDays(endDateOfDay, -1);
		return endDateOfMonth;
	}

	public static Date addMonth(Date date, int i) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH) + i);
		return calendar.getTime();

	}

	/**
	 *
	 * @param dateStr
	 *            格式为yyyy-MM
	 * @return
	 * @throws ParseException
	 */
	public static Date getBeginDateOfMonth(String dateStr) throws ParseException {

		String dateString = dateStr + "-01";
		Date date = DateUtil.parse(dateString, DateUtil.defaultDatePatternStr);
		Date beginDateOfMonth = DateUtil.getBeginOfDay(date);
		return beginDateOfMonth;
	}

	public static String getStringOfDate(Date date) {
		return getStringOfDate(date, null, null);
	}

	/**
	 *
	 * 描述：TODO
	 * 
	 * @param date
	 * @param pattern
	 * @param mode
	 * @return
	 */
	public static String getStringOfDate(Date date, String pattern, String mode) {
		Date modeDate = null;
		if (pattern == null)
			pattern = DateUtil.defaultDateTimePatternStr;
		if (mode == null)
			mode = DateUtil.defaultMode;
		if (mode.equals(DateUtil.defaultMode))
			modeDate = date;
		else if (mode.equals(DateUtil.beginOfDateMode))
			modeDate = DateUtil.getBeginOfDay(date);
		else if (mode.equals(DateUtil.endOfDateMode))
			modeDate = DateUtil.getEndOfDay(date);
		else
			throw new IllegalArgumentException("getStringOfDate(Date date,String pattern,String mode)方法参数非法: " + mode);

		Long dateTime = modeDate.getTime();
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		return sdf.format(new Date(dateTime));
	}

	/**
	 * 次方法是传入任意的date,任意的格式,输出的是pattern模式的date
	 * 
	 * @param date
	 * @param pattern
	 * @param mode
	 * @return
	 * @throws ParseException
	 */
	public static Date getDate(Date date, String pattern, String mode) throws ParseException {
		Date modeDate = null;
		if (pattern == null)
			pattern = DateUtil.defaultDateTimePatternStr;
		if (mode == null)
			mode = DateUtil.defaultMode;
		if (mode.equals(DateUtil.defaultMode))
			modeDate = date;
		else if (mode.equals(DateUtil.beginOfDateMode))
			modeDate = DateUtil.getBeginOfDay(date);
		else if (mode.equals(DateUtil.endOfDateMode))
			modeDate = DateUtil.getEndOfDay(date);
		else
			throw new IllegalArgumentException("getStringOfDate(Date date,String pattern,String mode)方法参数非法: " + mode);

		Long dateTime = modeDate.getTime();
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		String dateString = sdf.format(new Date(dateTime));
		return parse(dateString, pattern);
	}

	/**
	 * 判断当前时间是否在指定时间闭区间之内
	 * 
	 * @param validFrom
	 * @param validTo
	 * @return
	 */
	public static boolean isDuring(Date specifiedDate, Date validFrom, Date validTo) {
		if (validFrom == null || validTo == null)
			return false;
		if (DateUtil.compareDateTime(specifiedDate, validFrom) >= 0
				&& DateUtil.compareDateTime(specifiedDate, validTo) <= 0)
			return true;
		return false;
	}

	/**
	 * 判断某个指定时间是否在指定时间闭区间之内
	 * 
	 * @param validFrom
	 * @param validTo
	 * @return
	 */
	public static boolean isDuringIgnoreTime(Date specifiedDate, Date validFrom, Date validTo) {
		if (specifiedDate == null || validFrom == null || validTo == null)
			return false;
		return isDuring(ignoreTime(specifiedDate), ignoreTime(validFrom), ignoreTime(validTo));
	}

	/**
	 * 判断当前时间是否在指定时间闭区间之内
	 * 
	 * @param validFrom
	 * @param validTo
	 * @return
	 */
	public static boolean isDuring(Date validFrom, Date validTo) {
		return isDuring(new Date(), validFrom, validTo);
	}

	/**
	 * 判断当前时间是否在指定时间闭区间之内
	 * 
	 * @param validFrom
	 * @param validTo
	 * @return
	 */
	public static boolean isDuringIgnoreTime(Date validFrom, Date validTo) {
		return isDuringIgnoreTime(new Date(), validFrom, validTo);
	}

	/**
	 * 获取两个日期相差的天数
	 * 
	 * @param date1
	 * @param date2
	 * @return
	 * @throws ParseException
	 */
	public static int getTwoDatesInterval(Date date1, Date date2) throws ParseException {
		Date begin = null, end = null;
		if (date1 instanceof java.sql.Date) {
			begin = DateUtil.convertLongDateTimeToDate(date1);
		} else {
			begin = DateUtil.ignoreTime(date1);
		}

		if (date2 instanceof java.sql.Date) {
			end = DateUtil.convertLongDateTimeToDate(date2);
		} else {
			end = DateUtil.ignoreTime(date2);
		}

		long days = (end.getTime() - begin.getTime()) / DateUtil.DAY_MILLISECOND;
		return (int) days;
	}

	/**
	 * 获取两个日期相差的天数
	 * 
	 * @param date1
	 * @param date2
	 * @return
	 */
	public static int getInterval(Date date1, Date date2) {
		try {
			return getTwoDatesInterval(date1, date2);
		} catch (ParseException e) {
			return 0;
		}
	}

	/**
	 * 获取当前时间离当前月底还有多少天
	 * 
	 * @return
	 */
	public static int getDaysToEndOfMonth() {
		Calendar cal = Calendar.getInstance();
		int currentDayOfMonth = cal.get(Calendar.DAY_OF_MONTH);
		cal.set(Calendar.DAY_OF_MONTH, 1);
		cal.add(Calendar.MONTH, 1);
		cal.add(Calendar.DAY_OF_MONTH, -1);
		return cal.get(Calendar.DAY_OF_MONTH) - currentDayOfMonth;
	}

	/**
	 * 获取某个时间的月初时间点
	 * 
	 * @param date
	 * @return
	 */
	public static Date getBomTimePoint(Date date) {
		Calendar cal = Calendar.getInstance();
		if (date != null)
			cal.setTime(date);
		cal.set(Calendar.DAY_OF_MONTH, 1);
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		return cal.getTime();
	}

	/**
	 * 获取某个时间的所在的月底时间点
	 * 
	 * @param date
	 * @return
	 */
	public static Date getEomTimePoint(Date date) {
		Calendar cal = Calendar.getInstance();
		if (date != null)
			cal.setTime(date);
		cal.set(Calendar.DAY_OF_MONTH, 1);
		cal.add(Calendar.MONTH, 1);
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		cal.add(Calendar.MILLISECOND, -1);
		return cal.getTime();
	}

	/**
	 *
	 * 描述：将时间的时，分，秒转成秒的整数
	 * 
	 * @param date
	 * @return
	 */
	public static int time2Second(Date date) {
		if (date == null) {
			return 0;
		}
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		return cal.get(Calendar.HOUR_OF_DAY) * 3600 + cal.get(Calendar.MINUTE) * 60 + cal.get(Calendar.SECOND);
	}

	/**
	 * 将秒转换成时分秒格式(10：10：10)
	 * 
	 * @param time
	 *            秒数
	 * @return
	 */
	public static String secToTime(int time) {
		String timeStr = null;
		int hour = 0;
		int minute = 0;
		int second = 0;
		if (time <= 0)
			return "00:00:00";
		else {
			minute = time / 60;
			if (minute < 60) {
				second = time % 60;
				timeStr = "00:" + unitFormat(minute) + ":" + unitFormat(second);
			} else {
				hour = minute / 60;
				if (hour > 99)
					return "99:59:59";
				minute = minute % 60;
				second = time - hour * 3600 - minute * 60;
				timeStr = unitFormat(hour) + ":" + unitFormat(minute) + ":" + unitFormat(second);
			}
		}
		return timeStr;
	}

	public static String unitFormat(int i) {
		String retStr = null;
		if (i >= 0 && i < 10)
			retStr = "0" + Integer.toString(i);
		else
			retStr = "" + i;
		return retStr;
	}

	public static void main(String arg[]) throws ParseException {

	}
}
