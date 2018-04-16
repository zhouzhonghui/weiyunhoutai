package io.renren.common.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * 日期类型转换类
 * 
 * @author LIXU
 * @editor yangming
 */
public class DateUtil {

	public static final String YYYYMMDD = "yy年MM月dd日";
	public static final String YYYYMMDDHHMMSS = "yyyy-MM-dd HH:mm:ss";
	public static final String YYYYMMDDHHMMSSSSS = "yyyy-MM-dd HH:mm:ss|SSS";
	public static final String YEAR_MONTH = "yyyy_MM";
	public static final String YYYY_MM_DD = "yyyy-MM-dd";
	public static final String YYYYMMDDHHMM = "yyyy-MM-dd HH:mm";
	public static final String HHMM = "HH:mm";
	public static final String MMDD = "MM-dd";
	public static final String YYYYMMDDHHMM2 = "yyyyMMddHHmm";

	public static final String YYYY_MM_DD2 = "yyyy/MM/dd";
	public static final String YYYY_MM_DD3 = "yyyy/MM/dd HH:mm";

	private static ThreadLocal<SimpleDateFormat> threadLocal = new ThreadLocal<SimpleDateFormat>();

	/**
	 * 从ThreadLocal中获取SimpleDateFormat.
	 * 
	 * @param pattern
	 * @return
	 */
	public static SimpleDateFormat getSimpleDateFormat(String pattern) {
		// if (threadLocal.get() == null) {
		// SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
		// threadLocal.set(simpleDateFormat);
		// return simpleDateFormat;
		// } else {
		// return threadLocal.get();
		// }
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
		return simpleDateFormat;
	}

	private DateUtil() {
	}

	/**
	 * 获取当前时间毫秒数
	 * 
	 * @return
	 */
	public static Long getCurrentTime() {
		Date date = new Date();
		return date.getTime();
	}

	/**
	 * 获得当前时间
	 * 
	 * @return Date
	 */
	public static Date getNow() {
		return new Date();
	}

	/**
	 * 获得昨天的日期
	 * 
	 * @return
	 */
	public static Date getYesterday() {
		return addDays(-1);
	}

	/**
	 * 获得当前小时
	 * 
	 * @return
	 */
	public static int getCurrHour() {
		GregorianCalendar gC = new GregorianCalendar();
		return gC.get(GregorianCalendar.HOUR_OF_DAY);
	}

	/**
	 * date日期在一年中的第几天
	 * 
	 * @param date
	 * @return
	 */
	public static int getDay_Of_Year(Date date) {
		GregorianCalendar gC = new GregorianCalendar();
		gC.setTime(date);
		return gC.get(GregorianCalendar.DAY_OF_YEAR);
	}

	/**
	 * date日期在当月中的第几天
	 * 
	 * @param date
	 * @return
	 */
	public static int getDay_Of_Month(Date date) {
		GregorianCalendar gC = new GregorianCalendar();
		gC.setTime(date);
		return gC.get(GregorianCalendar.DAY_OF_MONTH);
	}

	/**
	 * 获得date日期在这周是星期几
	 * 
	 * @param date
	 * @return
	 */
	public static int getDay_Of_Week(Date date) {
		GregorianCalendar gC = new GregorianCalendar();
		gC.setTime(date);
		return gC.get(GregorianCalendar.DAY_OF_WEEK) - 1;
	}

	/**
	 * 根据date日期计算星期几，今天显示“今天”
	 * 
	 * @param date
	 * @return
	 */
	public static String getDay_Of_WeekString(Date date) {
		int day = getDay_Of_Week(date);
		String dayOfWeek = "";
		switch (day) {
		case 1:
			dayOfWeek = "周一";
			break;
		case 2:
			dayOfWeek = "周二";
			break;
		case 3:
			dayOfWeek = "周三";
			break;
		case 4:
			dayOfWeek = "周四";
			break;
		case 5:
			dayOfWeek = "周五";
			break;
		case 6:
			dayOfWeek = "周六";
			break;
		case 0:
			dayOfWeek = "周日";
			break;
		default:
			dayOfWeek = "周转换错误";
		}
		return dayOfWeek;
	}

	/**
	 * 获得2个日期相差的天数
	 * 
	 * @param start
	 *            开始时间
	 * @param end
	 *            结束时间
	 * @return
	 */
	public static int getIntervalDays(Date start, Date end) {
		start = DateUtil.parse(DateUtil.format(start, "yyyy-MM-dd"), "yyyy-MM-dd");
		end = DateUtil.parse(DateUtil.format(end, "yyyy-MM-dd"), "yyyy-MM-dd");
		long mills_per_day = 24 * 3600 * 1000;
		long startMills = start.getTime();
		long endMills = end.getTime();
		int offset = 0;

		offset = (int) ((endMills - startMills) / mills_per_day);

		return offset;
	}

	/**
	 * 比较两个日期相差的分钟
	 * 
	 * @param start
	 * @param end
	 * @return
	 */
	public static int getIntervalminutes(Date start, Date end) {
		start = DateUtil.parse(DateUtil.format(start, "yyyy-MM-dd HH:mm:ss"), "yyyy-MM-dd HH:mm:ss");
		end = DateUtil.parse(DateUtil.format(end, "yyyy-MM-dd HH:mm:ss"), "yyyy-MM-dd HH:mm:ss");
		long mills_per_day = 1000 * 60;
		long startMills = start.getTime();
		long endMills = end.getTime();
		int offset = 0;

		offset = (int) ((endMills - startMills) / mills_per_day);

		return offset;
	}

	/**
	 * 获得2个日期相差的月份
	 * 
	 * @param start
	 *            开始时间
	 * @param end
	 *            结束时间
	 * @return
	 */
	public static int getIntervalMonths(Date start, Date end) {
		GregorianCalendar startGC = new GregorianCalendar();
		GregorianCalendar endGC = new GregorianCalendar();
		startGC.setTime(start);
		endGC.setTime(end);

		int endY = endGC.get(GregorianCalendar.YEAR);
		int endM = endGC.get(GregorianCalendar.MONTH);
		int endD = endGC.get(GregorianCalendar.DAY_OF_MONTH);

		int startY = startGC.get(GregorianCalendar.YEAR);
		int startM = startGC.get(GregorianCalendar.MONTH);
		int startD = startGC.get(GregorianCalendar.DAY_OF_MONTH);

		int offset = -1;

		if (endD == startD) {
			offset = endM - startM + (endY - startY) * 12;
		}

		return offset;
	}

	/**
	 * 将传入时间与当前时间相比较。
	 * 
	 * @param dest
	 * @return 当前时间大于传入时间返回1；当前时间小于传入时间返回-1；当前时间等于传入时间返回0
	 */
	public static int compareTo(Date dest) {
		return compareTo(new Date(), dest);
	}

	/**
	 * 比较两个日期
	 * 
	 * @param src
	 * @param dest
	 * @return src大于dest：1；src等于dest：0；src小于dest：-1
	 */
	public static int compareTo(Date src, Date dest) {
		Date src1 = parse(format(src, "yyyy-MM-dd"), "yyyy-MM-dd");
		Date src2 = parse(format(dest, "yyyy-MM-dd"), "yyyy-MM-dd");
		int i = src1.compareTo(src2);
		return i;
	}

	/**
	 * 在当前日期增加天数
	 * 
	 * @param days
	 *            增加的天数
	 * @return Date
	 */
	public static Date addDays(int days) {
		return addDays(new Date(), days);
	}

	/**
	 * 给日期增加分钟,返回string
	 * 
	 * @param addminutes
	 * @return
	 */
	public static String addMintus(int addminutes) {
		Date d = parse("2000:01:01 00:00", "yyyy:MM:dd HH:mm");
		d = modifyDate(d, 0, addminutes, 0);
		// System.out.println(d);
		return format(d, "yyyy:MM:dd HH:mm");
	}

	/**
	 * 给日期增加秒,返回Date
	 * 
	 * @param
	 * @return
	 */
	public static Date addSecond(Date date, int addsecond) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.add(Calendar.SECOND, addsecond);
		// System.out.println(format(c.getTime(), "yyyy-MM-dd HH:mm:ss"));
		return c.getTime();
	}

	/**
	 * 给传过来的日期增加天数
	 * 
	 * @param srcDate
	 *            起始日期
	 * @param days
	 *            增加天数
	 * @return Date
	 */
	public static Date addDays(Date srcDate, int days) {
		GregorianCalendar gCanlendar = new GregorianCalendar();
		gCanlendar.setTime(srcDate);
		gCanlendar.add(gCanlendar.DAY_OF_MONTH, days);
		return gCanlendar.getTime();
	}

	/**
	 * 给传入的日期增加月份
	 * 
	 * @param srcDate
	 * @param months
	 * @return
	 */
	public static Date addMonths(Date srcDate, int months) {
		GregorianCalendar gCanlendar = new GregorianCalendar();
		gCanlendar.setTime(srcDate);
		gCanlendar.add(gCanlendar.MONTH, months);
		return gCanlendar.getTime();
	}

	/**
	 * 给传入的日期增加年份
	 * 
	 * @param srcDate
	 *            传入的日期
	 * @param year
	 *            增加的年
	 * @return Date
	 */
	public static Date addYears(Date srcDate, int year) {
		GregorianCalendar gCanlendar = new GregorianCalendar();
		gCanlendar.setTime(srcDate);
		gCanlendar.add(gCanlendar.YEAR, year);
		return gCanlendar.getTime();
	}

	/**
	 * 修改日期(小时，分钟，秒)
	 * 
	 * @param date
	 *            传入日期
	 * @param hour
	 *            小时
	 * @param minute
	 *            分钟
	 * @param second
	 *            秒
	 * @return
	 */
	public static Date modifyDate(Date date, int hour, int minute, int second) {
		GregorianCalendar gC = new GregorianCalendar();
		gC.setTime(date);
		gC.set(gC.HOUR_OF_DAY, hour);
		gC.set(gC.MINUTE, minute);
		gC.set(gC.SECOND, second);
		return gC.getTime();
	}

	/**
	 * 合并date 和time
	 * 
	 * @param date
	 * @param time
	 * @return
	 */
	public static Date merge(Date date, Date time) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(time);
		int hour = calendar.get(Calendar.HOUR_OF_DAY);
		int minute = calendar.get(Calendar.MINUTE);
		int second = calendar.get(Calendar.SECOND);
		return modifyDate(date, hour, minute, second);
	}

	/**
	 * 将传入String日期转换为 yyyy-MM-dd HH:mm:ss 格式
	 * 
	 * @param date
	 * @return
	 */
	public static Date parse(String date) {
		return parse(date, DateUtil.YYYYMMDDHHMMSS);
	}

	/**
	 * 将date转换为string格式
	 * 
	 * @param d
	 *            传入日期
	 * @param pattern
	 *            目标格式
	 * @return String
	 */
	public static String format(Date d, String pattern) {
		// SimpleDateFormat format = new SimpleDateFormat(pattern);
		SimpleDateFormat format = getSimpleDateFormat(pattern);
		return format.format(d);
	}

	/**
	 * 将String转换为目标格式
	 * 
	 * @param date
	 * @param srcPattern
	 *            原日期格式
	 * @param destPattern
	 *            目标格式
	 * @return String
	 */
	public static String format(String date, String srcPattern, String destPattern) {
		Date d = parse(date, srcPattern);
		if (d == null) {
			return null;
		}

		return format(d, destPattern);
	}

	/**
	 * 将String转换Date格式
	 * 
	 * @param date
	 *            传入日期
	 * @param pattern
	 *            传入日期格式
	 * @return Date
	 */
	public static Date parse(String date, String pattern) {
		try {
			// SimpleDateFormat format = new SimpleDateFormat(pattern);
			SimpleDateFormat format = getSimpleDateFormat(pattern);
			return format.parse(date);
		} catch (java.text.ParseException e) {
			return null;
		}
	}

	/**
	 * 获得当月有多少天
	 * 
	 * @return
	 */
	public static int getDayOfMonth() {
		Calendar c = Calendar.getInstance();
		c.set(Calendar.YEAR, (new Date()).getYear());
		c.set(Calendar.MONTH, (new Date()).getMonth());
		return c.getActualMaximum(Calendar.DAY_OF_MONTH);
	}

	/**
	 * 获得上个月有多少天
	 * 
	 * @return
	 */
	public static int getDayOfLastMonth() {
		Calendar c = Calendar.getInstance();
		c.set(Calendar.YEAR, (new Date()).getYear());
		c.set(Calendar.MONTH, (addMonths(new Date(), -1)).getMonth());
		return c.getActualMaximum(Calendar.DAY_OF_MONTH);
	}

	/**
	 * 获得7天前的日期
	 * 
	 * @return
	 */
	public static Date getDayOfMonday() {
		Calendar c = Calendar.getInstance();
		c.set(Calendar.YEAR, (new Date()).getYear());
		c.set(Calendar.MONTH, (new Date()).getMonth());
		c.set(Calendar.DATE, (new Date()).getDate() - 7);
		// c.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
		// if(c.getTime().)
		return c.getTime();
	}

	/**
	 * 判断时间的开始结束被包含 d1,d2 在 d3,d4的区间内
	 */
	public static boolean contain(Date d1, Date d2, Date d3, Date d4) {
		if (d1.compareTo(d3) >= 0 && d2.compareTo(d4) < 1)
			return true;
		else
			return false;
	}

	/**
	 * 判断时间的开始结束被交叉或包含 d1，d2与d3,d4的范围存在交集
	 */
	public static boolean cross(Date d1, Date d2, Date d3, Date d4) {
		if (d2.compareTo(d4) <= 0 && d2.compareTo(d3) > 0)
			return true;
		else if (d1.compareTo(d3) >= 0 && d1.compareTo(d4) < 0)
			return true;
		else if (contain(d3, d4, d1, d2)) {
			return true;
		}
		return false;
	}

	/**
	 * 获取随机日期
	 * 
	 * @param beginDate
	 * @return
	 */
	public static Date randomDate(String beginDate) {
		// SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat df = getSimpleDateFormat("yyyy-MM-dd");
		return randomDate(beginDate, df.format(new Date()));
	}

	private static long random(long begin, long end) {
		long rtnn = begin + (long) (Math.random() * (end - begin));
		if (rtnn == begin || rtnn == end) {
			return random(begin, end);
		}
		return rtnn;
	}

	/**
	 * 获取某天前的日期
	 * 
	 * @return
	 */
	public static Date getDayOfMonday(int days) {
		Calendar c = Calendar.getInstance();
		c.add(Calendar.DAY_OF_MONTH, -days);
		return c.getTime();
		// //System.out.println("增加一天后日期 ：  "+sf.format(c.getTime()));
	}

	/**
	 * 根据某个已知时间，获取某天前的时间
	 * 
	 * @param date
	 * @param days
	 * @return
	 */
	public static Date getDayOfMonday(String date, int days) {
		Calendar c = Calendar.getInstance();
		c.set(Calendar.YEAR, Integer.parseInt(date.substring(0, 4)));// 年
		c.set(Calendar.MONTH, Integer.parseInt(date.substring(4, 6)));// 月
		c.set(Calendar.DAY_OF_MONTH, 9);// 日
		c.add(Calendar.DAY_OF_MONTH, -days);
		return c.getTime();
		// //System.out.println("增加一天后日期 ：  "+sf.format(c.getTime()));
	}

	/**
	 * 获取某一时间段的随机事件
	 * 
	 * @param beginDate
	 *            开始时间
	 * @param endDate
	 *            结束时间
	 * @return
	 */
	public static Date randomDate(String beginDate, String endDate) {
		try {
			SimpleDateFormat format = getSimpleDateFormat("yyyy-MM-dd");
			Date start = format.parse(beginDate);// 开始日期
			Date end = format.parse(endDate);// 结束日期
			if (start.getTime() >= end.getTime()) {
				return null;
			}
			long date = random(start.getTime(), end.getTime());

			return new Date(date);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 获取某一时间段的随机时间
	 * 
	 * @param beginDate
	 *            开始时间
	 * @param endDate
	 *            结束时间
	 * @param pattern
	 *            日期格式
	 * @return
	 */
	public static Date randomDate(String beginDate, String endDate, String pattern) {
		try {
			SimpleDateFormat format = getSimpleDateFormat(pattern);
			Date start = format.parse(beginDate);// 开始日期
			Date end = format.parse(endDate);// 结束日期
			if (start.getTime() >= end.getTime()) {
				return null;
			}
			long date = random(start.getTime(), end.getTime());

			return new Date(date);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 得到几天前的时间
	 */
	public static String getDateBefore(Date d, int day) {
		Calendar now = Calendar.getInstance();
		DateFormat df = getSimpleDateFormat("yyyy-MM-dd");
		now.setTime(d);
		now.set(Calendar.DATE, now.get(Calendar.DATE) - day);
		Date d2 = now.getTime();
		String s = df.format(d2);
		return s;
	}

	/**
	 * 得到几天后的时间
	 */

	public static String getDateAfter(Date d, int day) {
		Calendar now = Calendar.getInstance();
		DateFormat df = getSimpleDateFormat("yyyy-MM-dd");
		now.setTime(d);
		now.set(Calendar.DATE, now.get(Calendar.DATE) + day);
		Date d2 = now.getTime();
		String s = df.format(d2);
		return s;
	}

	public static String recogniseDate(String dateStr) {
		if (dateStr != null && dateStr.length() > 10) {
			String a = dateStr.substring(0, 10);
			return DateUtil.format(a, DateUtil.YYYYMMDDHHMMSS, DateUtil.YYYY_MM_DD);// 注册日期(如：2014-08-07
		} else {
			return dateStr;
		}
	}

	public static String getOrderNo() {
		SimpleDateFormat dateFormat=new SimpleDateFormat("yyMMddhhmmssSSS");
		return dateFormat.format(new Date());
	}

	public static void main(String[] args) {
		System.out.println(getOrderNo());
		SimpleDateFormat dateFormat=new SimpleDateFormat("yyMMddhhmmssSSS");
		System.out.println(System.nanoTime());
		System.out.println(new Date().getTime());
		System.out.println(new Date(System.nanoTime()));

		System.out.println(dateFormat.format(new Date(System.nanoTime())));
		// String d1string = "01:00";
		// Date d1 = DateUtil.parse(d1string, "HH:mm");
		// String d2string = "12:00";
		// Date d2 = DateUtil.parse(d2string, "HH:mm");
		// String d3string = "09:00";
		// Date d3 = DateUtil.parse(d3string, "HH:mm");
		// String d4string = "12:00";
		// Date d4 = DateUtil.parse(d4string, "HH:mm");
		// //System.out.println(cross(d1, d2, d3, d4));
		// Date d5 = new Date(1458156022000L);
		// //System.out.println("d5:" + DateUtil.format(d5,
		// DateUtil.YYYYMMDDHHMMSS));
		// Date d6 = new Date(1458166022000L);
		// //System.out.println("d6:" + DateUtil.format(d6,
		// DateUtil.YYYYMMDDHHMMSS));
		// //System.out.println(DateUtil.getIntervalminutes(d5,d6));
		// String dateString1 = "2009-05-05 08:00:00";
		// String dateString = "2015-10-15";
		// //System.out.println(dateString.substring(0,10));
		// String aa = DateUtil.format(dateString1.substring(0, 10),
		// DateUtil.YYYYMMDDHHMMSS, DateUtil.YYYY_MM_DD);
		// //System.out.println(aa);
		// //System.out.println(StringUtils.substringBefore("1.0", "."));

		System.out.println(getDay_Of_Week(DateUtil.parse("2016-07-31", DateUtil.YYYY_MM_DD)));

		System.out.println(recogniseDate("2010-10-26"));
	}

}