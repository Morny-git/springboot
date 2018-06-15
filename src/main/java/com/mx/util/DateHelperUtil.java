package com.mx.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 日期处理类
 */
public class DateHelperUtil {
	private static final String DEFAULT_DATETIME_FORMAT = "yyyy/MM/dd";
	private static final String SECOND_DATETIME_FORMATE = "yyyyMMddHHmmss";

	/**
	 * 获得指定时间(specTime)n天的时间组成的数组
	 *
	 * @param n
	 *            N天
	 * @param interval
	 *            隔interval天
	 * @param specTime
	 *            指定时间(为null则取当前时间)
	 * @param includeSpecTime
	 *            是否包含指定时间(true:包括)
	 */
	public static List<String> getListStrs(int n, int interval, String specTime, boolean includeSpecTime) throws Exception {
		Calendar calendar = Calendar.getInstance();
		if (!(StringUtils.isEmpty(specTime))) {
			Date date = formatToDate(specTime, null);
			calendar.setTime(date);
		}
		calendar.add(Calendar.DAY_OF_MONTH, -n);
		List<String> resultStrs = new ArrayList<String>();
		for (int i = n - 1; i > -1; i--) {
			String str = formatToString(calendar.getTime(), null);
			if (includeSpecTime) {
				calendar.add(Calendar.DAY_OF_MONTH, interval);
				str = formatToString(calendar.getTime(), null);
				resultStrs.add(str);
			} else {
				resultStrs.add(str);
				calendar.add(Calendar.DAY_OF_MONTH, interval);
			}
		}
		return resultStrs;
	}

	/**
	 * 将日期对象转换为指定格式的字符串
	 *
	 * @param date
	 *            日期，如果为空，则取当前时间
	 * @param format
	 *            格式化串，如果为空，则取默认格式串
	 */
	public static String formatToString(Date date, String format) {
		if (null == date) {
			date = new Date();
		}
		if (StringUtils.isEmpty(format)) {
			format = DEFAULT_DATETIME_FORMAT;
		}
		SimpleDateFormat sf = new SimpleDateFormat(format);
		String time = sf.format(date);
		return time;
	}

	/**
	 * 将日期String转换为Date
	 *
	 * @param str
	 *            时间串
	 * @param format
	 *            格式化串，如果为空，则取默认格式串
	 */
	public static Date formatToDate(String str, String format) throws Exception {
		if (StringUtils.isEmpty(format)) {
			format = DEFAULT_DATETIME_FORMAT;
		}
		SimpleDateFormat sf = new SimpleDateFormat(format);
		Date date = null;
		try {
			date = sf.parse(str);
		} catch (ParseException e) {
			throw new RuntimeException("日期解析错误,请检查日期格式");
		}
		return date;
	}

	/**
	 * 获得指定时间范围内specTime及其它周内的同一时间
	 *
	 * @param startTime
	 *            开始时间
	 * @param endTime
	 *            结束时间
	 * @param specTime
	 *            指定时间
	 * @return set specTime及其它周内的同一时间
	 */
	public static Set<String> getWeeksDate(String startTime, String endTime, String specTime) throws Exception {
		Set<String> set = new TreeSet<String>();
		Date spec = DateHelperUtil.formatToDate(specTime, null);
		Date end = DateHelperUtil.formatToDate(endTime, null);
		int distince = getDateCount(startTime, specTime,null);
		Calendar c = Calendar.getInstance();
		c.setTime(spec);
		c.add(Calendar.DAY_OF_MONTH, -7 * (int) (distince / 7));
		//查询的日期不在所要查找的区间内
		if (c.getTime().getTime() > end.getTime()) {
			set.add(specTime);
			return set;
		}
		Calendar c1 = Calendar.getInstance();
		c1.setTime(end);
		getAfterWeek(c1, c, set);
		return set;
	}

	private static void getAfterWeek(Calendar c_after, Calendar c, Set<String> set) {
		if (c_after.compareTo(c) >= 0) {
			set.add(formatToString(c.getTime(), null));
			c.add(Calendar.DAY_OF_MONTH, 7);
			getAfterWeek(c_after, c, set);
		}
	}

	/**
	 * 若specTime为空,则获得当天时间agoDays天前的日期
	 * 
	 * @param specTime
	 *            时间串
	 * @agoDays 正数为前agoDays天,负数为后agoDays天
	 * @param format
	 *            格式化串，如果为空，则取默认格式串
	 */
	public static String getTimeStringBefore(String specTime, int agoDays, String format) throws Exception {
		if (StringUtils.isEmpty(format)) {
			format = DEFAULT_DATETIME_FORMAT;
		}
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		Calendar cd = Calendar.getInstance();
		if (!StringUtils.isEmpty(specTime)) {
			cd.setTime(formatToDate(specTime, format));
		} else {
			cd.add(Calendar.DAY_OF_MONTH, -agoDays);
		}
		String dateStr = sdf.format(cd.getTime());
		return dateStr;
	}

	/**
	 * 计算时间差
	 *
	 * @param startTime
	 *            开始时间
	 * @param endTime
	 *            结束时间
	 */
	public static int getDateCount(String startTime, String endTime,String formate) throws Exception {
		Date start = DateHelperUtil.formatToDate(startTime, formate);
		Date spec = DateHelperUtil.formatToDate(endTime, formate);
		Long distince = (spec.getTime() - start.getTime()) / (3600 * 24 * 1000);
		return distince.intValue();
	}

	/**
	 * 相隔时间的秒数
	 * @param startTime
	 * @param endTime
	 * @param formate
	 * @return
	 * @throws Exception
	 */
	public static long getSeconds(String startTime, String endTime) throws Exception {
		Date start = formatToDate(startTime,SECOND_DATETIME_FORMATE);
		Date end = formatToDate(endTime,SECOND_DATETIME_FORMATE);
		Long seconds = end.getTime() - start.getTime();
		return seconds.longValue();
	}
	/**
	 * 获取指定时间的前几天
	 */
	public static String getBeforeDays(String date,int day) throws Exception {
		Calendar now =Calendar.getInstance();
		now.setTime(formatToDate(date,""));
		now.set(Calendar.DATE,now.get(Calendar.DATE)-day);
		return formatToString(now.getTime(),"");
	}

	/**
	 * get Calendar of given year
	 * @param year
	 * @return
	 */
	public static Calendar getCalendarFormYear(int year){
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
		cal.set(Calendar.YEAR, year);
		return cal;
	}

	/**
	 * 获取指定时间的前几周的开始时间
	 */
	public static String getBeforeweeks(String date,int week) throws Exception {
		Calendar c = Calendar.getInstance();
		c.setTime(formatToDate(date,""));
		c.add(Calendar.WEEK_OF_YEAR,-week);
		Date startTime = c.getTime();
		return formatToString(startTime,"");
	}
	/**
	 * 获取指定时间的前几月时间
	 */
	public static String getBeforeMonth(String date,int month) throws Exception {
		Calendar c = Calendar.getInstance();
		c.setTime(formatToDate(date,""));
		c.add(Calendar.MONTH,-month);
		c.set(Calendar.DAY_OF_MONTH,1);
		Date startTime = c.getTime();
		return formatToString(startTime,"");
	}
	/**
	 * get the end day of given week no of a year.
	 * @return
	 */
	public static String getEndDayOfWeek(String date) throws Exception {
		Calendar c = Calendar.getInstance();
		c.setTime(formatToDate("2017/09/01",""));
		int d = 0;
		if (c.get(Calendar.DAY_OF_WEEK) == 1) {
			d = 0;
		} else {
			d = 8 - c.get(Calendar.DAY_OF_WEEK);
		}
		c.add(Calendar.DAY_OF_WEEK, d);
		Date startTime = c.getTime();
		return formatToString(startTime,"");
	}
	/**
	 * 获取当前月的最后一天
	 */
	public static String getEndDayOfMonth(String date) throws Exception {
		Calendar c = Calendar.getInstance();
		c.set(Calendar.DATE,c.getActualMaximum(Calendar.DAY_OF_MONTH));
		Date startTime = c.getTime();
		return formatToString(startTime,"");
	}





}
