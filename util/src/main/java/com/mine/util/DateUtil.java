//package com.mine.util;
//
//import java.sql.Timestamp;
//import java.text.ParseException;
//import java.text.SimpleDateFormat;
//import java.util.Calendar;
//import java.util.Date;
//import java.util.GregorianCalendar;
//import java.util.concurrent.TimeUnit;
//
//public class DateUtil{
//	public static enum CompareType{BEFORE,AFTER,EQ;}
//	//private static DateFormat yyyy_MM_ddFormat = new SimpleDateFormat("yyyy-MM-dd");
//	private static String patern_yyyy_MM_dd="yyyy-MM-dd";
//	private static String patern_yyyy_MM_dd_HH_mm_ss="yyyy-MM-dd HH:mm:ss";
//	private static final long oneDayMillSeconds = 24 * 60 * 60 * 1000;/** 1天对应的毫秒数 */
//
//	public static String getYyyyMMdd(Date date){
//		return new SimpleDateFormat("yyyyMMdd").format(date);
//	}
//
//	/**
//	 * 获取日期 yyyyMMdd格式
//	 * @return
//	 */
//	public static String getYyyyMMdd(){
//		return new SimpleDateFormat("yyyyMMdd").format(new Date());
//	}
//	/**
//	 * 获取时间 HHmmss格式
//	 * @return
//	 */
//	public static String getHHmmss(){
//		return new SimpleDateFormat("HHmmss").format(new Date());
//	}
//
//	//获取给定日期所在年的第一天的开始时间
//	//对应2016-04-21 为2016-01-01 00：00：00
//	public static Date getStartYear(Date date){
//		date = DateUtil.getStartDate(date);
//		Calendar c = Calendar.getInstance();
//		c.setTime(date);
//		c.set(Calendar.DAY_OF_YEAR, 1);
//		return c.getTime();
//	}
//
//	//获取给定日期所在年的最后一天的截止时间
//	//对应2016-04-21 为2016-12-31 23：59：59
//	public static Date getEndYear(Date date){
//		date = DateUtil.getStartDate(date);
//		Calendar c = Calendar.getInstance();
//		c.setTime(date);
//		c.set(Calendar.DAY_OF_YEAR, 1);
//		c.add(Calendar.YEAR, 1);
//		c.add(Calendar.SECOND, -1);
//		return c.getTime();
//	}
//
//	public static Date getStartMonth(Date date){
//		date = DateUtil.getStartDate(date);
//		Calendar c = Calendar.getInstance();
//		c.setTime(date);
//		c.set(Calendar.DAY_OF_MONTH, 1);
//		return c.getTime();
//	}
//
//	public static Date getEndMonth(Date date){
//		date = DateUtil.getStartDate(date);
//		Calendar c = Calendar.getInstance();
//		c.setTime(date);
//		c.set(Calendar.DAY_OF_MONTH, 1);
//		c.add(Calendar.MONTH, 1);
//		c.add(Calendar.SECOND, -1);
//		return c.getTime();
//	}
//
//	/**
//	 * @param year 2009
//	 * @param month 1-12
//	 * @param day 1-31
//	 * @return
//	 */
//	public static Timestamp getTimestampOfStartDay(int year, int month, int day){
//		Calendar cal = Calendar.getInstance();
//		cal.set(Calendar.YEAR, year);
//		cal.set(Calendar.MONTH, month-1);
//		cal.set(Calendar.DAY_OF_MONTH, day);
//		cal.set(Calendar.HOUR_OF_DAY, 0);
//		cal.set(Calendar.MINUTE, 0);
//		cal.set(Calendar.SECOND, 0);
//		return new Timestamp(cal.getTimeInMillis());
//	}
//
//	/**
//	 * @param year 2009
//	 * @param month 1-12
//	 * @param day 1-31
//	 * @return
//	 */
//	public static java.sql.Date getSqlDateOfStartDay(int year, int month, int day){
//		Calendar cal = Calendar.getInstance();
//		cal.set(Calendar.YEAR, year);
//		cal.set(Calendar.MONTH, month-1);
//		cal.set(Calendar.DAY_OF_MONTH, day);
//		cal.set(Calendar.HOUR_OF_DAY, 0);
//		cal.set(Calendar.MINUTE, 0);
//		cal.set(Calendar.SECOND, 0);
//		return new java.sql.Date(cal.getTimeInMillis());
//	}
//
//	/**
//	 * @return current time like 20090709122806
//	 */
//	public static String getDateSuffix(){
//		return getDateSuffix(new Date(), "yyyyMMddHHmmss");
//	}
//
//	/**
//	 * @param  date
//	 * @param  pattern like "yyyyMMddHHmmss"
//	 * @return
//	 */
//	public static String getDateSuffix(Date date, String pattern){
//		return DateFormatUtils.format(date, pattern);
//	}
//
//	/**
//	 * @param year 2009
//	 * @param month 0-11
//	 * @param day 1-31
//	 * @return
//	 */
//	public static Date getUtilDateOfStartDay(int year, int month, int day){
//		Calendar cal = Calendar.getInstance();
//		cal.set(Calendar.YEAR, year);
//		cal.set(Calendar.MONTH, month);
//		cal.set(Calendar.DAY_OF_MONTH, day);
//		cal.set(Calendar.HOUR_OF_DAY, 0);
//		cal.set(Calendar.MINUTE, 0);
//		cal.set(Calendar.SECOND, 0);
//		cal.set(Calendar.MILLISECOND, 0);
//		return new Date(cal.getTimeInMillis());
//	}
//
//	public static Calendar getEpoc() {
//	   Calendar cal = Calendar.getInstance();
//	   cal.setTimeInMillis(0);
//	   return cal;
//	}
//
//	/**
//	 * compare date,doesn't care the time.
//	 * example:
//	 *   before date :2010-02-03
//	 *   last datetime:2010-02-03 00:00:35
//	 *
//	 * call compareForDate(before,Date,compareType.before)
//	 *  return false;
//	 */
//	public static boolean compareForDate(Date source,Date target, CompareType comparetype){
//		Date source_date = getDateWithoutTime(source);
//		Date target_date = getDateWithoutTime(target);
//
//		if(CompareType.BEFORE.equals(comparetype)){
//			return source_date.before(target_date);
//		}else if(CompareType.AFTER.equals(comparetype)){
//			return source_date.after(target_date);
//		}else{
//			return source_date.equals(target_date);
//		}
//	}
//
//	public static boolean compareForTime(Date source,Date target, CompareType comparetype){
//		if(CompareType.BEFORE.equals(comparetype)){
//			return source.before(target);
//		}else if(CompareType.AFTER.equals(comparetype)){
//			return source.after(target);
//		}else{
//			return source.equals(target);
//		}
//	}
//
//	public static int compareTwoDateWithoutTime(Date source, Date target){
//		Date source_date = getDateWithoutTime(source);
//		Date target_date = getDateWithoutTime(target);
//
//		return source_date.compareTo(target_date);
//	}
//
//	public static Date getDateWithoutTime(Date date){
//		Calendar ca = Calendar.getInstance();
//		ca.setTime(date);
//		return getUtilDateOfStartDay(ca.get(Calendar.YEAR), ca.get(Calendar.MONTH), ca.get(Calendar.DAY_OF_MONTH));
//	}
//
//	/**
//	 * just get tomorrow date.
//	 */
//	public static Timestamp getTimestampOfTomorrow(){
//		Calendar ca = Calendar.getInstance();
//		ca.add(Calendar.DAY_OF_MONTH, 1);
//		return DateUtil.getTimestampOfStartDay(ca.get(Calendar.YEAR), ca.get(Calendar.MONTH) + 1, ca.get(Calendar.DAY_OF_MONTH));
//	}
//
//	/**
//	 * compare two date without millis time.
//	 * Calendar.setTime(date) will throw NullpointerException bc of the timezone is null.
//	 * <strong> return -1 if sourceTime before anotherTime, return 0 if sourceTime equals anotherTime, return 1 if sourceTime after anotherTime.</strong>
//	 */
//
//	public static int compareTwoDateWithoutTime(long sourceTime, long anotherTime){
//		long stime = cleanTime(sourceTime);
//		long atime = cleanTime(anotherTime);
//		return (stime<atime ? -1 : (stime==atime ? 0 : 1));
//	}
//
//	/**
//	 * clean the hour, minute, second, millisecond.
//	 * @param time long
//	 * @return the millisecond of time.
//	 */
//	public static long cleanTime(long time){
//		Calendar c=Calendar.getInstance();
//		c.setTimeInMillis(time);
//		c.set(Calendar.HOUR_OF_DAY, 0);
//		c.set(Calendar.MINUTE, 0);
//		c.set(Calendar.SECOND, 0);
//		c.set(Calendar.MILLISECOND, 0);
//		return c.getTimeInMillis();
//	}
//
//    public static Date getOtherDate(Date now, int offset) {
//        Calendar calendar = Calendar.getInstance();
//        calendar.setTimeInMillis(now.getTime());
//        calendar.add(Calendar.DATE, offset);
//        return calendar.getTime();
//    }
//
//    public static Date getOtherMonth(Date now, int offset) {
//        Calendar calendar = Calendar.getInstance();
//        calendar.setTime(now);
//        calendar.add(Calendar.MONTH, offset);
//        return calendar.getTime();
//    }
//
//    public static Date getOtherHour(Date now, int hour) {
//        Calendar calendar = Calendar.getInstance();
//        calendar.setTime(now);
//        calendar.add(Calendar.HOUR_OF_DAY, hour);
//        return calendar.getTime();
//    }
//
//    public static Date getOtherMinute(Date now, int minute) {
//        Calendar calendar = Calendar.getInstance();
//        calendar.setTime(now);
//        calendar.add(Calendar.MINUTE, minute);
//        return calendar.getTime();
//    }
//
//    public static Date getOtherSecond(Date now, int second) {
//        Calendar calendar = Calendar.getInstance();
//        calendar.setTime(now);
//        calendar.add(Calendar.SECOND, second);
//        return calendar.getTime();
//    }
//
//    public static Date getOtherYear(Date now, int year) {
//        Calendar calendar = Calendar.getInstance();
//        calendar.setTime(now);
//        calendar.add(Calendar.YEAR, year);
//        return calendar.getTime();
//    }
//
//    public static int getHourOfDay(Date now) {
//        Calendar calendar = Calendar.getInstance();
//        calendar.setTime(now);
//        return calendar.get(Calendar.HOUR_OF_DAY);
//    }
//
//	public static int getDayOfDate(Date now) {
//		Calendar calendar = Calendar.getInstance();
//		calendar.setTime(now);
//		return calendar.get(Calendar.DAY_OF_MONTH);
//	}
//
//	public static int getMonthOfDate(Date now) {
//		Calendar calendar = Calendar.getInstance();
//		calendar.setTime(now);
//		return calendar.get(Calendar.MONTH);
//	}
//
//    public static String getDateYYYY_MM_DD(Date date) {
//        if (date != null) {
//        	return DateFormatUtils.format(date, patern_yyyy_MM_dd);
//        }
//        return "";
//    }
//    public static String getDateYYYY_MM_dd_HH_mm_ss(Date date) {
//        if (date != null) {
//        	return DateFormatUtils.format(date, patern_yyyy_MM_dd_HH_mm_ss);
//        }
//        return "";
//    }
//    /**
//     * @param day
//     * @return 获取当月的第一天
//     */
//    public static Calendar starCalOfMonth(Calendar day) {
//    	Calendar c = (Calendar) day.clone();
//    	c.set(Calendar.DAY_OF_MONTH, c.getActualMinimum(Calendar.DAY_OF_MONTH));
//        return c;
//    }
//
//    /**
//     * @param day
//     * @return 获取当月的最后一天
//     */
//    public static Calendar endCalOfMonth(Calendar day) {
//    	Calendar c = (Calendar) day.clone();
//    	c.set(Calendar.DAY_OF_MONTH, c.getActualMaximum(Calendar.DAY_OF_MONTH));
//        return c;
//    }
//
//    public static Date[] getTimeIntervalForDate(Date targetDate)
//	{
//		Date beginDateT = getStartDate(targetDate);
//		Date endDateT = getEndDate(targetDate);
//       	Date[] dates = new Date[]{beginDateT,endDateT};
//		return dates;
//	}
//
//	public static Date[] getTimeIntervalForDate(Date startDate,Date endDate)
//	{
//		Date beginDateT = getStartDate(startDate);
//		Date endDateT = getEndDate(endDate);
//       	Date[] dates = new Date[]{beginDateT,endDateT};
//		return dates;
//	}
//
//	public static Date getStartDate(Date date)
//	{
//		Calendar c = Calendar.getInstance();
//		if(null == date)
//			c.set(1970, 1, 1);
//		else
//			c.setTime(date);
//
//	    c.set(Calendar.HOUR_OF_DAY, 0);
//	    c.set(Calendar.MINUTE, 0);
//	    c.set(Calendar.SECOND, 0);
//	    c.set(Calendar.MILLISECOND, 0);
//
//	    return c.getTime();
//	}
//
//	public static Date getEndDate(Date date)
//	{
//	    Calendar c = Calendar.getInstance();
//	    if(null == date)
//			c.set(2100, 1, 1);
//		else
//			c.setTime(date);
//        c.set(Calendar.HOUR_OF_DAY, 23);
//        c.set(Calendar.MINUTE, 59);
//        c.set(Calendar.SECOND, 59);
//        c.set(Calendar.MILLISECOND, 0);
//
//        return c.getTime();
//	}
//
//	public static Date getDateTime(Date date,Date dateTime){
//		Calendar cdate = Calendar.getInstance();
//		Calendar ctime = Calendar.getInstance();
//	    if(null == date)
//			cdate.set(2100, 1, 1);
//		else
//			cdate.setTimeInMillis( date.getTime() );
//	    if(null == dateTime)
//			ctime.set(2100, 1, 1);
//		else
//			ctime.setTimeInMillis(dateTime.getTime());
//	    cdate.set( Calendar.HOUR_OF_DAY , ctime.get( Calendar.HOUR_OF_DAY ) );
//	    cdate.set( Calendar.MINUTE , ctime.get( Calendar.MINUTE ) );
//	    cdate.set( Calendar.SECOND , ctime.get( Calendar.SECOND ) );
//		return cdate.getTime();
//	}
//
//	public static Date formatDateToParse(Date date,String parse)
//	{
//		if(date == null ||StringUtils.isEmpty(parse)){
//			throw new RuntimeException("日期或日期格式表达式为空。");
//		}
//		Date dd = null;
//		try {
//			SimpleDateFormat format = new SimpleDateFormat(parse);
//			dd = format.parse(format.format(date));
//		} catch (ParseException e) {
//			throw new RuntimeException("日期格式转换错误。",e);
//		}
//		return dd;
//	}
//	/**
//	 * 计算间隔天数
//	 * @param beginDate, excluded
//	 * @param endDate, included
//	 * @return
//	 */
//	public static int getIntervalDayNumber(Date beginDate,Date endDate){
//		Calendar d1 = Calendar.getInstance();
//		Calendar d2 = Calendar.getInstance();
//
//		d1.setTime(beginDate);
//		d2.setTime(endDate);
//
//	    if (d1.after(d2)) {  // swap dates so that d1 is start and d2 is end
//            Calendar swap = d1;
//            d1 = d2;
//            d2 = swap;
//        }
//       int days = d2.get(Calendar.DAY_OF_YEAR) - d1.get(Calendar.DAY_OF_YEAR);
//       int y2 = d2.get(Calendar.YEAR);
//       if (d1.get(Calendar.YEAR) != y2) {
//            d1 = (Calendar) d1.clone();
//           do {
//                days += d1.getActualMaximum(Calendar.DAY_OF_YEAR);//得到当年的实际天数
//                d1.add(Calendar.YEAR, 1);
//            } while (d1.get(Calendar.YEAR) != y2);
//        }
//       return days;
//
//	}
//
//	public static long getIntervalHourNumber(Date beginDate,Date endDate){
//	    long diff=Math.abs(beginDate.getTime() - endDate.getTime());
//	    long hour = diff/(1000*60*60);
//	    return hour;
//	}
//
//
//	public static long getIntervalSeconds(Date beginDate, Date endDate){
//	    long duration = endDate.getTime() - beginDate.getTime();
//	    long seconds = TimeUnit.MILLISECONDS.toSeconds(duration);
//	    return seconds;
//	}
//
//	public static long getIntervalMonthNumber(Date beginDate,Date endDate){
//	    Calendar begin = Calendar.getInstance();
//	    Calendar end = Calendar.getInstance();
//	    begin.setTime(beginDate);
//	    end.setTime(endDate);
//	    long result = Math.abs(end.get(Calendar.YEAR) * 12 + end.get(Calendar.MONTH) - begin.get(Calendar.YEAR) * 12 - begin.get(Calendar.MONTH));
//	    return result;
//	}
//
//	public static long cleanDate(long time){
//		Calendar c=Calendar.getInstance();
//		c.setTimeInMillis(time);
//		c.set(Calendar.YEAR, 0);
//		c.set(Calendar.MONTH, 0);
//		c.set(Calendar.DAY_OF_MONTH, 0);
//		return c.getTimeInMillis();
//	}
//
//	public static int compareTwoTimeWithoutDate(Date start, Date end){
//		long stime = cleanDate(start.getTime());
//		long etime = cleanDate(end.getTime());
//		return (stime<etime ? -1 : (stime==etime ? 0 : 1));
//	}
//
//	public static void main(String[] args) throws ParseException {
////	    Calendar calendar = Calendar.getInstance();
////	    calendar.set(2014, 1, 15);
////	    System.out.println(calendar.get(Calendar.DAY_OF_YEAR));
////	    Date startDate = calendar.getTime();
////	    calendar.set(2014, 2, 15);
////	    System.out.println(calendar.get(Calendar.DAY_OF_YEAR));
////	    Date endDate = calendar.getTime();
////	    System.out.println(DateUtil.getIntervalDayNumber(startDate,endDate));
////		Date startYearDate = getStartYear(new Date());
////		Date endYearDate = getEndYear(new Date());
////		System.out.println("startYearDate:"+getDateSuffix(startYearDate, patern_yyyy_MM_dd_HH_mm_ss) );
////		System.out.println("endYearDate:"+getDateSuffix(endYearDate, patern_yyyy_MM_dd_HH_mm_ss) );
////		System.out.print(getDateSuffix(getThisMondayStartTime(new Date()),"yyyyMMddHHmmss"));
////		Date thisWeekStartTime = DateUtil.getThisMondayStartTime(DateUtil.getOtherDate(new Date(),-7));
////		Date nextWeekStartTime = DateUtil.getOtherDate(thisWeekStartTime, 7);
////		System.out.print(getDateYYYY_MM_dd_HH_mm_ss(thisWeekStartTime) + "||" + getDateYYYY_MM_dd_HH_mm_ss(nextWeekStartTime));
//		Date now = new Date();
////		Date date1 = DateUtil.getStartDate(now);//小
////		Date date2 = DateUtil.getEndDate(now);//大
//		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//		Date today = format.parse("2016-10-26 13:00:00");
////		Date now = new Date();
//		if (!DateUtil.getStartDate(today).after(DateUtil.getOtherDate(DateUtil.getStartDate(new Date()), -3))){
//			System.out.print("true");
//		} else {
//			System.out.print("false");
//		}
////		DateUtil.getStartDate(DateUtil.getOtherDate(DateUtil.getStartDate(new Date(1,2,3)))).after(DateUtil.getOtherDate(DateUtil.getStartDate(new Date()), -3)
////		System.out.print(date1.after(date2));
//	}
//
//	/**
//	 * string 转Date类型
//	 * @param dateString
//	 * @return
//	 */
//	public  static Date strToDate(String dateString,String pattern){
//		SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
//		Date date = null;
//		try {
//			date = dateFormat.parse(dateString);
//		} catch (ParseException e) {
//			throw new RuntimeException(dateString+"转换"+pattern+"异常");
//		}
//		return date;
//	}
//	public static Date getDayBegin() {
//		Calendar cal = Calendar.getInstance();
//		cal.set(Calendar.HOUR_OF_DAY, 0);
//		cal.set(Calendar.SECOND, 0);
//		cal.set(Calendar.MINUTE, 0);
//		cal.set(Calendar.MILLISECOND, 000);
//		return new Date(cal.getTimeInMillis());
//	}
//
//	/**
//	 * 判定时间是否是周末
//	 *
//	 * @param date
//	 * @return
//     */
//	public static boolean isWeekEnd(Date date){
//		if (date == null){
//			throw new RuntimeException("date不能为空!");
//		}
//		Calendar cal = Calendar.getInstance();
//		cal.setTime(date);
//		if(cal.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY || cal.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
//			return true;
//		} else {
//			return false;
//		}
//	}
//
//	/**
//	 * 判定时间是否是周六
//	 *
//	 * @param date
//	 * @return
//	 */
//	public static boolean isSaturday(Date date){
//		if (date == null){
//			throw new RuntimeException("date不能为空!");
//		}
//		Calendar cal = Calendar.getInstance();
//		cal.setTime(date);
//		if(cal.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY) {
//			return true;
//		} else {
//			return false;
//		}
//	}
//
//	/**
//	 * 判定时间是否是周日
//	 *
//	 * @param date
//	 * @return
//	 */
//	public static boolean isSunday(Date date){
//		if (date == null){
//			throw new RuntimeException("date不能为空!");
//		}
//		Calendar cal = Calendar.getInstance();
//		cal.setTime(date);
//		if(cal.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
//			return true;
//		} else {
//			return false;
//		}
//	}
//
//	/**
//	 * 判定时间是否是周五
//	 *
//	 * @param date
//	 * @return
//	 */
//	public static boolean isFriday(Date date){
//		if (date == null){
//			throw new RuntimeException("date不能为空!");
//		}
//		Calendar cal = Calendar.getInstance();
//		cal.setTime(date);
//		if(cal.get(Calendar.DAY_OF_WEEK) == Calendar.FRIDAY) {
//			return true;
//		} else {
//			return false;
//		}
//	}
//	/**
//	 * 获取当前系统时间
//	 *
//	 * @return Date 当前系统时间
//	 */
//	public static Date getDate() {
//		return Calendar.getInstance().getTime();
//	}
//	/**
//	 * 将毫秒的日期数值转化为Date对象
//	 *
//	 * @param  time 给定毫秒值
//	 * @return Date 把给定的time转换成日期类型
//	 */
//	public static Date fromLong(long time) {
//		Date date = getDate();
//		date.setTime(time);
//
//		return date;
//	}
//	/**
//	 * 由某个日期，前推若干毫秒
//	 *
//	 * @param date        给定的日期
//	 * @param millSeconds 给定前推的秒数
//	 * @return Date       将给定的日期前推给定的秒数后的日期
//	 */
//	public static Date before(Date date, long millSeconds) {
//		return fromLong(date.getTime() - millSeconds);
//	}
//
//
//	/**
//	 * 由某个日期，后推若干毫秒
//	 *
//	 * @param date        给定的日期
//	 * @param millSeconds 给定后推的秒数
//	 * @return Date       将给定的日期后推给定的秒数后的日期
//	 */
//	public static Date after(Date date, long millSeconds) {
//		return fromLong(date.getTime() + millSeconds);
//	}
//
//	/**
//	 * 得到某个日期之后n天后的日期
//	 *
//	 * @param date  给定日期
//	 * @param nday  给定天数
//	 * @return Date 给定日期n天后的日期
//	 */
//	public static Date after(Date date, int nday) {
//		return fromLong(date.getTime() + nday * oneDayMillSeconds);
//	}
//
//
//	/**
//	 * 得到当前日期之后n天后的日期
//	 *
//	 * @param n     给定天数
//	 * @return Date 当前日期n天后的日期
//	 */
//	public static Date afterNDays(int n) {
//		return after(getDate(), n * oneDayMillSeconds);
//	}
//
//	/**
//	 * 得到当前日期n天前的日期
//	 *
//	 * @param n     给定天数
//	 * @return Date 当前日期n天数的日期
//	 */
//	public static Date beforeNDays(int n) {
//		return beforeNDays(getDate(), n );
//
//	}
//
//	/**
//	 * 得到某个日期n天前的日期
//	 *
//	 * @param date  给定日期
//	 * @param n     给定天数
//	 * @return Date 给定日期n天前的日期
//	 */
//	public static Date beforeNDays(Date date, int n) {
//		return fromLong(date.getTime() - n * oneDayMillSeconds);
//	}
//
//
//	/**
//	 * 昨天
//	 *
//	 * @return Date 昨天
//	 */
//	public static Date yesterday() {
//		return before(getDate(), oneDayMillSeconds);
//	}
//
//	/**
//	 * 明天
//	 *
//	 * @return Date 明天
//	 */
//	public static Date tomorrow() {
//		return after(getDate(), oneDayMillSeconds);
//	}
//
//	/**
//	 * 当前时间距离
//	 *
//	 * @param time
//	 * @return
//     */
//	private static int getMondayPlus(Date time) {
//		Calendar cd = Calendar.getInstance();
//		cd.setTime(time);
//		int dayOfWeek = cd.get(Calendar.DAY_OF_WEEK) - 1;
//		if (dayOfWeek == 1) {
//			return 0;
//		} else {
//			return 1 - dayOfWeek;
//		}
//	}
//
//	/**
//	 * time所属周,周一的0点
//	 *
//	 * @param time
//	 * @return
//     */
//	public static Date getThisMondayStartTime(Date time) {
//		int mondayPlus = getMondayPlus(time);
//		GregorianCalendar currentDate = new GregorianCalendar();
//		currentDate.setTime(time);
//		currentDate.add(GregorianCalendar.DATE, mondayPlus);
//		Date monday = currentDate.getTime();
//		return getStartDate(monday);
//	}
//}
