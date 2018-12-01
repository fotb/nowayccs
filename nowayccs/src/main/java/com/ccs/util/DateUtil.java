package com.ccs.util;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Hashtable;
import java.util.Map;
import java.util.TimeZone;

import org.apache.log4j.Logger;

public class DateUtil {
	private static Map<String, SimpleDateFormat> sdfCache = new Hashtable<String, SimpleDateFormat>();

	public static final long ONE_DAY = 24 * 60 * 60 * 1000;

	private static final Logger LOG = Logger.getLogger(DateUtil.class);

	/**
	 * Convert String to date with a format
	 * 
	 * @param date
	 *            Input String to convert
	 * @param pattern
	 *            pattern
	 * @return Date
	 */
	public static Date parse(final String date, final String pattern) {
		try {
			final SimpleDateFormat sdf = getSimpleDateFormat(pattern);
			sdf.setTimeZone(TimeZone.getTimeZone("Asia/Shanghai")); // 设置北京时区
			synchronized (sdf) {
				return sdf.parse(date);
			}
		} catch (ParseException e) {
			return null;
		}
	}

	
	
    public static Date parseDt(String strDate, final String pattern) throws ParseException{
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        sdf.setTimeZone(TimeZone.getTimeZone("Asia/Shanghai")); // 设置北京时区
       return sdf.parse(strDate);
   }
	/**
	 * Check the date is correct
	 * 
	 * @param date
	 *            Input String to check
	 * @param pattern
	 *            pattern
	 * @return If check correct, return true; otherwise, return false flag:
	 *         false-not right date format true-right date format
	 */
	public static boolean checkIsDate(final String date, final String pattern) {
		boolean flag = false;
		if (date == null || "".equals(date)) {
			return flag;
		} else {
			final SimpleDateFormat sdf = getSimpleDateFormat(pattern);
			try {
				synchronized (sdf) {
					final Date tempDate = sdf.parse(date);
					final String tempStr = sdf.format(tempDate);
					if (tempStr.equalsIgnoreCase(date)) {
						flag = true;
					}
				}
			} catch (ParseException e) {
				LOG.error("Error parsing '" + date + "' using pattern '"
						+ pattern + "'");
			}
			return flag;
		}
	}

	/**
	 * Convert date to String with a format
	 * 
	 * @param date
	 *            Input date to convert
	 * @param pattern
	 *            pattern
	 * @return String
	 */
	public static String format(final Date date, final String pattern) {
		//LOG.info("-------------date is----------------:");
		//LOG.info(date);
		return date == null ? "" : getSimpleDateFormat(pattern).format(date);
	}

	/**
	 * Convert date to String
	 * 
	 * @param date
	 *            Input date to convert
	 * @return String
	 */
	public static String formatDOB(final Date date) {
		return format(date, "dd/MM/yyyy");
	}

	/**
	 * get today
	 * 
	 * @return Returns the today
	 */
	public static Date getToday() {
		final Date date = new Date();
		final String s = format(date, "dd/MM/yyyy");
		return parse(s, "dd/MM/yyyy");
	}

	/**
	 * Constructs a SimpleDateFormat using the given pattern and the default
	 * date format symbols for the default locale.
	 * 
	 * @param pattern
	 *            pattern
	 * @return SimpleDateFormat
	 */
	private static SimpleDateFormat getSimpleDateFormat(final String pattern) {
		SimpleDateFormat sdf = (SimpleDateFormat) sdfCache.get(pattern);
		if (sdf == null) {
			sdf = new SimpleDateFormat();
			sdf.setTimeZone(TimeZone.getTimeZone("Asia/Shanghai")); // 设置北京时区
			sdf.applyPattern(pattern);
			sdf.setLenient(false);
			sdfCache.put(pattern, sdf);
		}
		return sdf;
	}

	/**
	 * Start Modification For BCP By Angelo on 21 Nov 2007 Date Comparasion
	 * Method
	 * 
	 * @param aDate
	 * @param curDate
	 * @param bDate
	 * @return If the method is correct, return true; otherwise, return false
	 */
	public static boolean compareDate(final Date aDate, final Date curDate,
			final Date bDate) {
		return ((curDate.after(aDate) || curDate.equals(aDate)) && curDate
				.before(bDate));
	}

	/**
	 * Date Comparasion Method
	 * 
	 * @param aDate
	 * @param curDate
	 * @param bDate
	 * @return boolean
	 * @return If the method is correct, return true; otherwise, return false
	 */
	public static boolean compareDate(final String aDateStr,
			final String bDateStr, final String pattern) {
		final Date aDate = parse(aDateStr, pattern);
		final Date bDate = parse(bDateStr, pattern);
		if ((aDate != null && bDate != null)
				&& (aDate.before(bDate) || aDate.equals(bDate))) {
			return true;
		}
		return false;
	}

	/**
	 * date calculate
	 * 
	 * @param inputDate
	 * @param field
	 * @param amount
	 * @return Date
	 */
	public static Date add(final Date inputDate, final int field,
			final int amount) {
		final Calendar cal = Calendar.getInstance();
		cal.setTime(inputDate);
		cal.add(field, amount);

		return cal.getTime();
	}

	public static Date addDayOfYear(final Date inputDate, final int amount) {
		return add(inputDate, Calendar.DAY_OF_YEAR, amount);
	}

	public static Date addDate(final Date inputDate, final int amount) {
		return add(inputDate, Calendar.DATE, amount);
	}

	public static Date addMonth(final Date inputDate, final int amount) {
		return add(inputDate, Calendar.MONTH, amount);
	}

	public static Date addYear(final Date inputDate, final int amount) {
		return add(inputDate, Calendar.YEAR, amount);
	}

	public static java.sql.Date convertToSQLDate(final Date date) {
		if (date == null) {
			return null;
		}

		return new java.sql.Date(date.getTime());
	}

	public static Timestamp convertToSQLTimestamp(final Date date) {
		if (date == null) {
			return null;
		}

		return new Timestamp(date.getTime());
	}

	/**
	 * Method to truncte the input date's time to 00:00:00
	 * 
	 * @param inputDate
	 * @return
	 */
	public static Date truncateTime(final Date inputDate) {
		return truncateTime(inputDate, Calendar.getInstance());
	}

	/**
	 * Method to truncte the input date's time to 00:00:00
	 * 
	 * @param inputDate
	 * @param calendar
	 * @return
	 */
	public static Date truncateTime(final Date inputDate,
			final Calendar calendar) {
		Date date = null;
		if (inputDate != null) {
			calendar.setTime(inputDate);
			calendar.set(Calendar.HOUR_OF_DAY, 0);
			calendar.set(Calendar.MINUTE, 0);
			calendar.set(Calendar.SECOND, 0);
			calendar.set(Calendar.MILLISECOND, 0);
			date = calendar.getTime();
		}
		return date;

	}

	/**
	 * get first day of month
	 * 
	 * @param inputDate
	 * @param calendar
	 * @return Date
	 */
	public static Date getFirstDayOfMonth(final Date date) {
		final GregorianCalendar gc = (GregorianCalendar) Calendar.getInstance();
		gc.setTime(date);
		gc.set(Calendar.DAY_OF_MONTH, 1);
		return gc.getTime();
	}

	
	
	/**
	 * get first day of month
	 * 
	 * @param inputDate
	 * @param calendar
	 * @return Date
	 */
	public static Date getLastDayOfMonth(final Date date) {
		Date day = getFirstDayOfMonth(date);
		day = addMonth(day, 1);
		day = addDate(day, -1);
		return day;
	}
	
	public static Date getDayOfProvsMonth(final Date date) {
		final GregorianCalendar gc = (GregorianCalendar) Calendar.getInstance();
		gc.setTime(date);
		return addMonth(gc.getTime(), -1);
	}

	/**
	 * get now date
	 * 
	 * @returnDate
	 */
	public static Date getNowDate() {
		return new Date();
	}

	public static boolean compareDate(final Date firstDate,
			final Date secendDate) {
		final Calendar firstCalendar = Calendar.getInstance();
		firstCalendar.setTime(firstDate);
		firstCalendar.set(Calendar.DAY_OF_MONTH,
				firstCalendar.get(Calendar.DAY_OF_MONTH) - 1);
		final Calendar secondCalendar = Calendar.getInstance();
		secondCalendar.setTime(secendDate);
		return firstCalendar.after(secondCalendar);
	}
	
	
	
	public static int getIntervalMinutes(Date startDay, Date endDay){
		
		final Calendar startCalendar = Calendar.getInstance();
		startCalendar.setTime(startDay);
		
		final Calendar endCalendar = Calendar.getInstance();
		endCalendar.setTime(endDay);
		
		long sl=startCalendar.getTimeInMillis();
		long el=endCalendar.getTimeInMillis();

		long ei=el-sl;
		return (int)(ei/(1000*60));
		}
}