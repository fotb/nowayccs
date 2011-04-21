package com.ccs.util;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.StringTokenizer;


/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2005</p>
 * <p>Company: </p>
 * @author not attributable
 * @version 1.0
 */

public class Utils {
    /**
     * 取得日期的周几
     *
     * @param dateStr String 日期
     * @return int 周几值
     *
     * 周日 1
     * 周一 2
     * |
     * 周六 7
     */
    public static int getWeek(String dateStr) {
        /*拆分yyyy-MM-dd形式的日期字符串*/
        StringTokenizer strTok = new StringTokenizer(dateStr, " _-./");
        String[] strAry = new String[strTok.countTokens()];
        /*组成数组*/
        int i = 0;
        while (strTok.hasMoreTokens()) {
            strAry[i++] = strTok.nextToken();
        }
        /*从数组中获得年月日*/
        int year = Integer.parseInt(strAry[0]);
        int month = Integer.parseInt(strAry[1]);
        int date = Integer.parseInt(strAry[2]);

        /**
         * 获得该日期的星期
         * GregorianCalendar(int year, int month, int date)中的月份标记是从0开始的,
         * 如1月为0,所以月份要减1
         */
        GregorianCalendar gc = new GregorianCalendar(year, month - 1, date);
        int week = gc.get(Calendar.DAY_OF_WEEK);
        return week;
    }

    /**
     * 根据传入的日期和week标记,获得该week的日期
     *
     * @param date String 日期
     * @param weekFlag int week标记
     * @return String yyyy-MM-dd形式的日期字符串
     */
    public static String getWeekDate(String dateStr, int weekFlag) {
        /*拆分yyyy-MM-dd形式的日期字符串*/
        StringTokenizer strTok = new StringTokenizer(dateStr, " _-./");
        String[] strAry = new String[strTok.countTokens()];
        /*组成数组*/
        int i = 0;
        while (strTok.hasMoreTokens()) {
            strAry[i++] = strTok.nextToken();
        }
        /*从数组中获得年月日*/
        int year = Integer.parseInt(strAry[0]);
        int month = Integer.parseInt(strAry[1]);
        int date = Integer.parseInt(strAry[2]);

        /**
         * 获得该日期的星期
         * GregorianCalendar(int year, int month, int date)中的月份标记是从0开始的,
         * 如1月为0,所以月份要减1
         */
        GregorianCalendar gc = new GregorianCalendar(year, month - 1, date);
        int week = gc.get(Calendar.DAY_OF_WEEK);

        /*比较当前星期和希望获得的星期,增减天数*/
        switch (week) {
        case 1: //sunday
            gc.add(gc.DAY_OF_MONTH, weekFlag - 7);
            break;
        case 2:
            gc.add(gc.DAY_OF_MONTH, weekFlag - week + 1);
            break;
        case 3:
            gc.add(gc.DAY_OF_MONTH, weekFlag - week + 1);
            break;
        case 4:
            gc.add(gc.DAY_OF_MONTH, weekFlag - week + 1);
            break;
        case 5:
            gc.add(gc.DAY_OF_MONTH, weekFlag - week + 1);
            break;
        case 6:
            gc.add(gc.DAY_OF_MONTH, weekFlag - week + 1);
            break;
        case 7: //FRIDAY
            gc.add(gc.DAY_OF_MONTH, weekFlag - week + 1);
            break;
        }
        /*组成"yyyy-MM-dd格式的日期字符串*/
        return gc.get(gc.YEAR) + "-" + (gc.get(gc.MONTH) + 1) + "-" +
                gc.get(gc.DATE);
    }

    /**
     * 根据给定日期返回星期天日期
     * @param String
     */
    public static String getSunDate(String monDate) {
        StringTokenizer monTok = new StringTokenizer(monDate, " _-./");
        String[] monStr = new String[monTok.countTokens()];
        int e = 0; //用于获取字符串
        while (monTok.hasMoreTokens()) {
            monStr[e++] = monTok.nextToken();
        }
        int y = (new Integer(monStr[0])).intValue(); //year
        int m = (new Integer(monStr[1])).intValue(); //month
        int d = (new Integer(monStr[2])).intValue(); //date
        //创建日历
        GregorianCalendar calender = new GregorianCalendar(y, (m - 1), d);
        java.util.Date dat = calender.getTime();
        //int weekDay = dat.getDay(); //取得星期
        int weekDay = calender.get(Calendar.DAY_OF_WEEK);
        //int date = dat.getDate();
        //int month = dat.getMonth();
        //int year = dat.getYear();
        switch (weekDay) {
        case 1:
            calender.add(calender.DAY_OF_MONTH, 0);
            break;
        case 2:
            calender.add(calender.DAY_OF_MONTH, 6);
            break;
        case 3:
            calender.add(calender.DAY_OF_MONTH, 5);
            break;
        case 4:
            calender.add(calender.DAY_OF_MONTH, 4);
            break;
        case 5:
            calender.add(calender.DAY_OF_MONTH, 3);
            break;
        case 6:
            calender.add(calender.DAY_OF_MONTH, 2);
            break;
        case 7:
            calender.add(calender.DAY_OF_MONTH, 1);
            break;
        }
        dat = calender.getTime();
        String ymd = "";
        int year = calender.get(calender.YEAR); //年
        int month = calender.get(calender.MONTH) + 1; //月
        int date = calender.get(Calendar.DATE); //日
        int weekday = calender.get(Calendar.DAY_OF_WEEK); //周

        ymd = (new Integer(year).toString()) + "-" +
              (new Integer(month).toString()) +
              "-" + (new Integer(date).toString());
        return ymd;
    }

    /**
     * 获取上一月
     *
     * @param date Date 传入日期
     * @return String
     */
    public static String getPreviousMonth(Date date) {
        SimpleDateFormat spf = new SimpleDateFormat("yyyy-MM-dd");
        String dateStr = spf.format(date);
        /*拆分yyyy-MM-dd形式的日期字符串*/
        StringTokenizer strTok = new StringTokenizer(dateStr, " _-.:/");
        String[] strAry = new String[strTok.countTokens()];
        /*组成数组*/
        int i = 0;
        while (strTok.hasMoreTokens()) {
            strAry[i++] = strTok.nextToken();
        }
        /*从数组中获得年月日*/
        int y = Integer.parseInt(strAry[0]);
        int m = Integer.parseInt(strAry[1]);
        int d = Integer.parseInt(strAry[2]);
        String year = String.valueOf(y);
        String month = String.valueOf(m - 1);
        if ((m - 1) <= 0) {
            year = String.valueOf(y - 1);
        } else if ((m - 1) < 10) {
            month = "0" + (m - 1);
        }
        /*返回年月(yyyy-MM)*/
        return year + "-" + month;
    }

    /**
     * 获取上一月的第一天的日期
     *
     * @param date Date 传入日期
     * @return String
     */
    public static String getPreviousMonthFirstDay(Date date) {
        SimpleDateFormat spf = new SimpleDateFormat("yyyy-MM-dd");
        String dateStr = spf.format(date);
        /*拆分yyyy-MM-dd形式的日期字符串*/
        StringTokenizer strTok = new StringTokenizer(dateStr, " _-./");
        String[] strAry = new String[strTok.countTokens()];
        /*组成数组*/
        int i = 0;
        while (strTok.hasMoreTokens()) {
            strAry[i++] = strTok.nextToken();
        }
        /*从数组中获得年月日*/
        int y = Integer.parseInt(strAry[0]);
        int m = Integer.parseInt(strAry[1]);
        int d = Integer.parseInt(strAry[2]);
        //创建日历
        GregorianCalendar calender = new GregorianCalendar(y, (m - 2), d);
        int firstDate = calender.getActualMinimum(Calendar.DAY_OF_MONTH);
        return String.valueOf(y) + "-" + String.valueOf(m - 1) + "-" +
                String.valueOf(firstDate);
    }

    /**
     * 获取当月的第一天的日期
     *
     * @param date Date 传入日期
     * @return String
     */
    public static String getCurrentMonthFirstDay(Date date) {
        SimpleDateFormat spf = new SimpleDateFormat("yyyy-MM-dd");
        String dateStr = spf.format(date);
        /*拆分yyyy-MM-dd形式的日期字符串*/
        StringTokenizer strTok = new StringTokenizer(dateStr, " _-./");
        String[] strAry = new String[strTok.countTokens()];
        /*组成数组*/
        int i = 0;
        while (strTok.hasMoreTokens()) {
            strAry[i++] = strTok.nextToken();
        }
        /*从数组中获得年月日*/
        int y = Integer.parseInt(strAry[0]);
        int m = Integer.parseInt(strAry[1]);
        int d = Integer.parseInt(strAry[2]);
        //创建日历
        GregorianCalendar calender = new GregorianCalendar(y, (m - 1), d);
        int firstDate = calender.getActualMinimum(Calendar.DAY_OF_MONTH);
        return String.valueOf(y) + "-" + String.valueOf(m) + "-" +
                String.valueOf(firstDate);
    }

    /**
     * 获取当月的最后一天
     *
     * @param date Date 传入日期
     * @return String
     */
    public static String getCurrentMonthLastDay(Date date) {
        SimpleDateFormat spf = new SimpleDateFormat("yyyy-MM-dd");
        String dateStr = spf.format(date);
        /*拆分yyyy-MM-dd形式的日期字符串*/
        StringTokenizer strTok = new StringTokenizer(dateStr, " _-./");
        String[] strAry = new String[strTok.countTokens()];
        /*组成数组*/
        int i = 0;
        while (strTok.hasMoreTokens()) {
            strAry[i++] = strTok.nextToken();
        }
        /*从数组中获得年月日*/
        int y = Integer.parseInt(strAry[0]);
        int m = Integer.parseInt(strAry[1]);
        int d = Integer.parseInt(strAry[2]);
        //创建日历
        GregorianCalendar calender = new GregorianCalendar(y, (m - 1), d);
        int lastDate = calender.getActualMaximum(Calendar.DAY_OF_MONTH);
        return String.valueOf(y) + "-" + String.valueOf(m) + "-" +
                String.valueOf(lastDate);
    }

    /**
     * 获取上一月的最后一天
     *
     * @param date Date 传入日期
     * @return String
     */
    public static String getPreviousMonthLastDay(Date date) {
        SimpleDateFormat spf = new SimpleDateFormat("yyyy-MM-dd");
        String dateStr = spf.format(date);
        /*拆分yyyy-MM-dd形式的日期字符串*/
        StringTokenizer strTok = new StringTokenizer(dateStr, " _-./");
        String[] strAry = new String[strTok.countTokens()];
        /*组成数组*/
        int i = 0;
        while (strTok.hasMoreTokens()) {
            strAry[i++] = strTok.nextToken();
        }
        /*从数组中获得年月日*/
        int y = Integer.parseInt(strAry[0]);
        int m = Integer.parseInt(strAry[1]);
        int d = Integer.parseInt(strAry[2]);
        //创建日历
        GregorianCalendar calender = new GregorianCalendar(y, (m - 2), d);
        int lastDate = calender.getActualMaximum(Calendar.DAY_OF_MONTH);
        return String.valueOf(y) + "-" + String.valueOf(m - 1) + "-" +
                String.valueOf(lastDate);
    }

    /**
     * 取当前日期
     * @param
     */
    public static String getstrCurrentDate() {
        Locale locale = Locale.CHINA;

        java.util.Date date = new java.util.Date(System.currentTimeMillis());

        String strDate = DateFormat.getDateInstance(DateFormat.DEFAULT, locale).
                         format(date);
        return strDate;
    }

    /**
     * 取当前日期时间
     * @param
     */
    public static String getstrCurrentDatetime() {
        java.util.Date date = new java.util.Date(System.currentTimeMillis());

        return formateDate(date, "yyyy-MM-dd kk:mm:ss");
    }


    /**
     * 取得指定日期的接下来的几天后的日期
     *
     * @param strDate String
     * @param backCount int 接下来的天数
     * @param formate String 如yyyy-mm-dd等formate
     * @return String
     */
    public static String getBackDayDate(String strDate, int backCount,
                                        String formate) throws SystemException {
        try {
            /* 设置日期格式 */
            SimpleDateFormat formatter = new SimpleDateFormat(
                    "yyyy-MM-dd kk:mm:ss");
            /* 初始化日期 */
            Date validateDate = null;
            /* 日期转换 */
            if (strDate != null && !strDate.trim().equals("")) {
                validateDate = formatter.parse(strDate);
            }
            long time = (validateDate.getTime() / 1000) - backCount * 24 * 60;

            validateDate.setTime(time * 1000);
            return formateDate(validateDate, formate);
        } catch (ParseException e) {
            SystemException se = new SystemException("日期转换失败", e);
            e.printStackTrace();
            throw (se);
        }

    }

    /**
     * 获取指定时间的前backMin分钟的时间
     *
     * @param strDate String 格式为yyyy-MM-dd kk:MM:SS
     * @param backMin int
     * @param formate String
     * @return String
     */
    public static String getBackDayMin(String strDate, int backMin,
                                       String formate) throws SystemException {
        try {
            /* 设置日期格式 */
            SimpleDateFormat formatter = new SimpleDateFormat(
                    "yyyy-MM-dd kk:mm:ss");
            /* 初始化日期 */
            Date validateDate = null;
            /* 日期转换 */
            if (strDate != null && !strDate.trim().equals("")) {
                validateDate = formatter.parse(strDate);
            }
            long time = (validateDate.getTime() / 1000) - backMin * 60;

            validateDate.setTime(time * 1000);
            return formateDate(validateDate, formate);
        } catch (ParseException e) {
            SystemException se = new SystemException("日期转换失败", e);
            e.printStackTrace();
            throw (se);
        }
    }

    /**
     * 字符串转换为日期
     * @param dateString String 被转换的字符串
     * @return Date 返回日期
     * @throws SystemException
     */
    public static Date changeDate(String dateString) throws SystemException {
        /* 设置日期格式 */
        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        /* 初始化日期 */
        Date validateDate = null;
        /* 日期转换 */
        if (dateString != null && !dateString.trim().equals("")) {
            try {
                validateDate = (Date) formatter.parse(dateString);
            } catch (ParseException e) {
                SystemException se = new SystemException("日期转换失败", e);
                e.printStackTrace();
                throw (se);
            }
        }
        /* 返回转换好的日期 */
        return validateDate;
    }

    /**
     * 字符串转换为时间
     * @param dateString String 被转换的字符串
     * @return Date 返回日期
     * @throws SystemException
     */
    public static Date changeDatetime(String datetimeString) throws
            SystemException {
        /* 设置日期格式 */
        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd kk:mm:ss");
        /* 初始化日期 */
        Date validateDate = null;
        /* 日期转换 */
        if (datetimeString != null && !datetimeString.trim().equals("")) {
            try {
                validateDate = (Date) formatter.parse(datetimeString);
            } catch (ParseException e) {
                SystemException se = new SystemException("日期转换失败", e);
                e.printStackTrace();
                throw (se);
            }
        }
        /* 返回转换好的日期 */
        return validateDate;
    }

    /**
     * 根据指定的格式格式化日期
     *
     * @param date Date 日期
     * @param dateFormat String 指定的格式,如:yyyy-MM-dd等
     * @return String
     */
    public static String formateDate(Date date, String dateFormat) {
        if (dateFormat == null || dateFormat.equals("")) {
            return "";
        }
        SimpleDateFormat format = new SimpleDateFormat(dateFormat);
        return format.format(date);
    }

    /**
     * 传人的参数长度不足补0
     */
    public static String fillZero(String inputStr, int length) {
        String val = inputStr;
        for (int i = 0; i < length - inputStr.length(); i++) {
            val = "0" + val;
        }
        return val;
    }

    /**
     * 去除字符串的空值
     * @param str
     * @return
     */

    public static String trimNull(String str) {
        if (str == null) {
            str = "";
        }
        return str;
    }

    public static boolean isNull(final String str) {
    	return (str == null || str.trim().equals(""));
    }
    
    public static Integer toInteger(String str, int num) {
        if (str == null || str.equals("")) {
            return new Integer(num);
        } else {
            return new Integer(str);
        }
    }

    public static String toChinese(String str) throws SystemException {
        try {
            if (str == null) {
                str = "";
            } else {
                str = new String(str.getBytes("ISO-8859-1"), "gb2312");
            }
        } catch (Exception e) {
            new SystemException(e.getMessage());
        }
        return str;

    }


    /**
     * 转化数字型金额表达方式为汉语大写金额表达方式
     *如传入123.23 -- 壹佰贰拾叁圆贰角叁分
     * @param num String 数字型金额表达方式
     * @return String
     */
    public static String transNumToChinese(BigDecimal bigdMoneyNumber) {
        /*中文金额单位数组*/
        String[] straChineseUnit = new String[] {
                                   "分", "角", "圆", "拾", "佰", "仟", "万", "拾", "佰",
                                   "仟", "亿", "拾", "佰",
                                   "仟"};
        /*中文数字字符数组*/
        String[] straChineseNumber = new String[] {
                                     "零", "壹", "贰", "叁", "肆", "伍", "陆", "柒",
                                     "捌", "玖"};
        String strChineseCurrency = "";
        /*零数位标记*/
        boolean bZero = true;
        /*中文金额单位下标*/
        int ChineseUnitIndex = 0;

        try {
            if (bigdMoneyNumber.intValue() == 0) {
                return "零圆整";
            }

            /*处理小数部分，四舍五入*/
            double doubMoneyNumber = Math.round(bigdMoneyNumber.doubleValue() *
                                                100);

            /*是否负数*/
            boolean bNegative = doubMoneyNumber < 0;

            /*取绝对值*/
            doubMoneyNumber = Math.abs(doubMoneyNumber);

            /*循环处理转换操作*/
            while (doubMoneyNumber > 0) {
                /*整的处理(无小数位)*/
                if (ChineseUnitIndex == 2 && strChineseCurrency.length() == 0) {
                    strChineseCurrency = strChineseCurrency + "整";
                }

                /*非零数位的处理*/
                if (doubMoneyNumber % 10 > 0) {
                    strChineseCurrency = straChineseNumber[(int)
                                         doubMoneyNumber % 10] +
                                         straChineseUnit[ChineseUnitIndex] +
                                         strChineseCurrency;
                    bZero = false;
                }
                /*零数位的处理*/
                else {
                    /*元的处理(个位)*/
                    if (ChineseUnitIndex == 2) {
                        /*段中有数字*/
                        if (doubMoneyNumber > 0) {
                            strChineseCurrency = straChineseUnit[
                                                 ChineseUnitIndex] +
                                                 strChineseCurrency;
                            bZero = true;
                        }
                    }
                    /*万、亿数位的处理*/
                    else if (ChineseUnitIndex == 6 || ChineseUnitIndex == 10) {
                        /*段中有数字*/
                        if (doubMoneyNumber % 1000 > 0) {
                            strChineseCurrency = straChineseUnit[
                                                 ChineseUnitIndex] +
                                                 strChineseCurrency;
                        }
                    }

                    /*前一数位非零的处理*/
                    if (!bZero) {
                        strChineseCurrency = straChineseNumber[0] +
                                             strChineseCurrency;
                    }
                    bZero = true;
                }

                doubMoneyNumber = Math.floor(doubMoneyNumber / 10);
                ChineseUnitIndex++;
            }
            //负数的处理
            if (bNegative) {
                strChineseCurrency = "负" + strChineseCurrency;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
        return strChineseCurrency;
    }

    /**
     * 转化数字表达方式为中文数字表达方式
     *如传入123 -- 一二三
     * @param num String 数字表达方式
     * @return String
     */
    public static String transNumToChineseNum(String bigdNumber) {
        /*中文金额单位数组*/
        String[] straChineseUnit = new String[] {
                                   "零", "一", "二", "三", "四", "五", "六", "七", "八",
                                   "九"};
        String ChineseNum = "";
        for (int i = 0; i < bigdNumber.length(); i++) {
            ChineseNum +=
                    straChineseUnit[Integer.parseInt(String.valueOf(bigdNumber.
                    charAt(i)))];
        }
        return ChineseNum;
    }


    /*判断parentStr中是否包含sonstr元素
     sonStr String
     parentStr String //用逗号搁开的字符串
     return boolean
     */
    public static boolean isInStr(String sonStr, String parentStr) {
        boolean isIn = false;
        /*拆分parentStr字符串*/
        StringTokenizer st = new StringTokenizer(parentStr, ",");
        while (st.hasMoreTokens()) {
            if (sonStr.equals(st.nextToken())) {
                isIn = true;
            } else {
                isIn = false;
            }
        }
        return isIn;
    }


    public static String[] convertArrayListToArray(ArrayList l) {
        String s[] = new String[l.size()];
        for (int i = 0; i < l.size(); i++) {
            s[i] = (String) l.get(i);
        }
        return s;
    }

    public static long stringToLong(String s) {
        if (!s.equals("")) {
            return Long.parseLong(s);
        } else {
            return Constants.DATABASE_NULL_NUMBER;
        }
    }

    /**
     * 获得一天的开始时间或结束时间,精确到时分秒 flag: AM 表示开始时间 PM 表示结束时间
     * @param date String
     * @return Date
     */
    public static Date getDayTime(String date,String flag) {
        Calendar calendar = Calendar.getInstance();
        StringTokenizer strTok = new StringTokenizer(date, " _-./");
        String[] strAry = new String[strTok.countTokens()];
        /*组成数组*/
        int i = 0;
        while (strTok.hasMoreTokens()) {
            strAry[i++] = strTok.nextToken();
        }
        /*从数组中获得年月日*/
        int y = Integer.parseInt(strAry[0]);
        int m = Integer.parseInt(strAry[1]);
        int d = Integer.parseInt(strAry[2]);
        if("AM".equals(flag)){
            calendar.set(y, m-1, d, 0, 0, 0);
        }else if("PM".equals(flag)){
            calendar.set(y, m-1, d, 24, 0, 0);
        }
        return calendar.getTime();

    }
}
