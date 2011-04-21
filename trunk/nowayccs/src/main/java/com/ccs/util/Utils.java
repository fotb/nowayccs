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
     * ȡ�����ڵ��ܼ�
     *
     * @param dateStr String ����
     * @return int �ܼ�ֵ
     *
     * ���� 1
     * ��һ 2
     * |
     * ���� 7
     */
    public static int getWeek(String dateStr) {
        /*���yyyy-MM-dd��ʽ�������ַ���*/
        StringTokenizer strTok = new StringTokenizer(dateStr, " _-./");
        String[] strAry = new String[strTok.countTokens()];
        /*�������*/
        int i = 0;
        while (strTok.hasMoreTokens()) {
            strAry[i++] = strTok.nextToken();
        }
        /*�������л��������*/
        int year = Integer.parseInt(strAry[0]);
        int month = Integer.parseInt(strAry[1]);
        int date = Integer.parseInt(strAry[2]);

        /**
         * ��ø����ڵ�����
         * GregorianCalendar(int year, int month, int date)�е��·ݱ���Ǵ�0��ʼ��,
         * ��1��Ϊ0,�����·�Ҫ��1
         */
        GregorianCalendar gc = new GregorianCalendar(year, month - 1, date);
        int week = gc.get(Calendar.DAY_OF_WEEK);
        return week;
    }

    /**
     * ���ݴ�������ں�week���,��ø�week������
     *
     * @param date String ����
     * @param weekFlag int week���
     * @return String yyyy-MM-dd��ʽ�������ַ���
     */
    public static String getWeekDate(String dateStr, int weekFlag) {
        /*���yyyy-MM-dd��ʽ�������ַ���*/
        StringTokenizer strTok = new StringTokenizer(dateStr, " _-./");
        String[] strAry = new String[strTok.countTokens()];
        /*�������*/
        int i = 0;
        while (strTok.hasMoreTokens()) {
            strAry[i++] = strTok.nextToken();
        }
        /*�������л��������*/
        int year = Integer.parseInt(strAry[0]);
        int month = Integer.parseInt(strAry[1]);
        int date = Integer.parseInt(strAry[2]);

        /**
         * ��ø����ڵ�����
         * GregorianCalendar(int year, int month, int date)�е��·ݱ���Ǵ�0��ʼ��,
         * ��1��Ϊ0,�����·�Ҫ��1
         */
        GregorianCalendar gc = new GregorianCalendar(year, month - 1, date);
        int week = gc.get(Calendar.DAY_OF_WEEK);

        /*�Ƚϵ�ǰ���ں�ϣ����õ�����,��������*/
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
        /*���"yyyy-MM-dd��ʽ�������ַ���*/
        return gc.get(gc.YEAR) + "-" + (gc.get(gc.MONTH) + 1) + "-" +
                gc.get(gc.DATE);
    }

    /**
     * ���ݸ������ڷ�������������
     * @param String
     */
    public static String getSunDate(String monDate) {
        StringTokenizer monTok = new StringTokenizer(monDate, " _-./");
        String[] monStr = new String[monTok.countTokens()];
        int e = 0; //���ڻ�ȡ�ַ���
        while (monTok.hasMoreTokens()) {
            monStr[e++] = monTok.nextToken();
        }
        int y = (new Integer(monStr[0])).intValue(); //year
        int m = (new Integer(monStr[1])).intValue(); //month
        int d = (new Integer(monStr[2])).intValue(); //date
        //��������
        GregorianCalendar calender = new GregorianCalendar(y, (m - 1), d);
        java.util.Date dat = calender.getTime();
        //int weekDay = dat.getDay(); //ȡ������
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
        int year = calender.get(calender.YEAR); //��
        int month = calender.get(calender.MONTH) + 1; //��
        int date = calender.get(Calendar.DATE); //��
        int weekday = calender.get(Calendar.DAY_OF_WEEK); //��

        ymd = (new Integer(year).toString()) + "-" +
              (new Integer(month).toString()) +
              "-" + (new Integer(date).toString());
        return ymd;
    }

    /**
     * ��ȡ��һ��
     *
     * @param date Date ��������
     * @return String
     */
    public static String getPreviousMonth(Date date) {
        SimpleDateFormat spf = new SimpleDateFormat("yyyy-MM-dd");
        String dateStr = spf.format(date);
        /*���yyyy-MM-dd��ʽ�������ַ���*/
        StringTokenizer strTok = new StringTokenizer(dateStr, " _-.:/");
        String[] strAry = new String[strTok.countTokens()];
        /*�������*/
        int i = 0;
        while (strTok.hasMoreTokens()) {
            strAry[i++] = strTok.nextToken();
        }
        /*�������л��������*/
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
        /*��������(yyyy-MM)*/
        return year + "-" + month;
    }

    /**
     * ��ȡ��һ�µĵ�һ�������
     *
     * @param date Date ��������
     * @return String
     */
    public static String getPreviousMonthFirstDay(Date date) {
        SimpleDateFormat spf = new SimpleDateFormat("yyyy-MM-dd");
        String dateStr = spf.format(date);
        /*���yyyy-MM-dd��ʽ�������ַ���*/
        StringTokenizer strTok = new StringTokenizer(dateStr, " _-./");
        String[] strAry = new String[strTok.countTokens()];
        /*�������*/
        int i = 0;
        while (strTok.hasMoreTokens()) {
            strAry[i++] = strTok.nextToken();
        }
        /*�������л��������*/
        int y = Integer.parseInt(strAry[0]);
        int m = Integer.parseInt(strAry[1]);
        int d = Integer.parseInt(strAry[2]);
        //��������
        GregorianCalendar calender = new GregorianCalendar(y, (m - 2), d);
        int firstDate = calender.getActualMinimum(Calendar.DAY_OF_MONTH);
        return String.valueOf(y) + "-" + String.valueOf(m - 1) + "-" +
                String.valueOf(firstDate);
    }

    /**
     * ��ȡ���µĵ�һ�������
     *
     * @param date Date ��������
     * @return String
     */
    public static String getCurrentMonthFirstDay(Date date) {
        SimpleDateFormat spf = new SimpleDateFormat("yyyy-MM-dd");
        String dateStr = spf.format(date);
        /*���yyyy-MM-dd��ʽ�������ַ���*/
        StringTokenizer strTok = new StringTokenizer(dateStr, " _-./");
        String[] strAry = new String[strTok.countTokens()];
        /*�������*/
        int i = 0;
        while (strTok.hasMoreTokens()) {
            strAry[i++] = strTok.nextToken();
        }
        /*�������л��������*/
        int y = Integer.parseInt(strAry[0]);
        int m = Integer.parseInt(strAry[1]);
        int d = Integer.parseInt(strAry[2]);
        //��������
        GregorianCalendar calender = new GregorianCalendar(y, (m - 1), d);
        int firstDate = calender.getActualMinimum(Calendar.DAY_OF_MONTH);
        return String.valueOf(y) + "-" + String.valueOf(m) + "-" +
                String.valueOf(firstDate);
    }

    /**
     * ��ȡ���µ����һ��
     *
     * @param date Date ��������
     * @return String
     */
    public static String getCurrentMonthLastDay(Date date) {
        SimpleDateFormat spf = new SimpleDateFormat("yyyy-MM-dd");
        String dateStr = spf.format(date);
        /*���yyyy-MM-dd��ʽ�������ַ���*/
        StringTokenizer strTok = new StringTokenizer(dateStr, " _-./");
        String[] strAry = new String[strTok.countTokens()];
        /*�������*/
        int i = 0;
        while (strTok.hasMoreTokens()) {
            strAry[i++] = strTok.nextToken();
        }
        /*�������л��������*/
        int y = Integer.parseInt(strAry[0]);
        int m = Integer.parseInt(strAry[1]);
        int d = Integer.parseInt(strAry[2]);
        //��������
        GregorianCalendar calender = new GregorianCalendar(y, (m - 1), d);
        int lastDate = calender.getActualMaximum(Calendar.DAY_OF_MONTH);
        return String.valueOf(y) + "-" + String.valueOf(m) + "-" +
                String.valueOf(lastDate);
    }

    /**
     * ��ȡ��һ�µ����һ��
     *
     * @param date Date ��������
     * @return String
     */
    public static String getPreviousMonthLastDay(Date date) {
        SimpleDateFormat spf = new SimpleDateFormat("yyyy-MM-dd");
        String dateStr = spf.format(date);
        /*���yyyy-MM-dd��ʽ�������ַ���*/
        StringTokenizer strTok = new StringTokenizer(dateStr, " _-./");
        String[] strAry = new String[strTok.countTokens()];
        /*�������*/
        int i = 0;
        while (strTok.hasMoreTokens()) {
            strAry[i++] = strTok.nextToken();
        }
        /*�������л��������*/
        int y = Integer.parseInt(strAry[0]);
        int m = Integer.parseInt(strAry[1]);
        int d = Integer.parseInt(strAry[2]);
        //��������
        GregorianCalendar calender = new GregorianCalendar(y, (m - 2), d);
        int lastDate = calender.getActualMaximum(Calendar.DAY_OF_MONTH);
        return String.valueOf(y) + "-" + String.valueOf(m - 1) + "-" +
                String.valueOf(lastDate);
    }

    /**
     * ȡ��ǰ����
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
     * ȡ��ǰ����ʱ��
     * @param
     */
    public static String getstrCurrentDatetime() {
        java.util.Date date = new java.util.Date(System.currentTimeMillis());

        return formateDate(date, "yyyy-MM-dd kk:mm:ss");
    }


    /**
     * ȡ��ָ�����ڵĽ������ļ���������
     *
     * @param strDate String
     * @param backCount int ������������
     * @param formate String ��yyyy-mm-dd��formate
     * @return String
     */
    public static String getBackDayDate(String strDate, int backCount,
                                        String formate) throws SystemException {
        try {
            /* �������ڸ�ʽ */
            SimpleDateFormat formatter = new SimpleDateFormat(
                    "yyyy-MM-dd kk:mm:ss");
            /* ��ʼ������ */
            Date validateDate = null;
            /* ����ת�� */
            if (strDate != null && !strDate.trim().equals("")) {
                validateDate = formatter.parse(strDate);
            }
            long time = (validateDate.getTime() / 1000) - backCount * 24 * 60;

            validateDate.setTime(time * 1000);
            return formateDate(validateDate, formate);
        } catch (ParseException e) {
            SystemException se = new SystemException("����ת��ʧ��", e);
            e.printStackTrace();
            throw (se);
        }

    }

    /**
     * ��ȡָ��ʱ���ǰbackMin���ӵ�ʱ��
     *
     * @param strDate String ��ʽΪyyyy-MM-dd kk:MM:SS
     * @param backMin int
     * @param formate String
     * @return String
     */
    public static String getBackDayMin(String strDate, int backMin,
                                       String formate) throws SystemException {
        try {
            /* �������ڸ�ʽ */
            SimpleDateFormat formatter = new SimpleDateFormat(
                    "yyyy-MM-dd kk:mm:ss");
            /* ��ʼ������ */
            Date validateDate = null;
            /* ����ת�� */
            if (strDate != null && !strDate.trim().equals("")) {
                validateDate = formatter.parse(strDate);
            }
            long time = (validateDate.getTime() / 1000) - backMin * 60;

            validateDate.setTime(time * 1000);
            return formateDate(validateDate, formate);
        } catch (ParseException e) {
            SystemException se = new SystemException("����ת��ʧ��", e);
            e.printStackTrace();
            throw (se);
        }
    }

    /**
     * �ַ���ת��Ϊ����
     * @param dateString String ��ת�����ַ���
     * @return Date ��������
     * @throws SystemException
     */
    public static Date changeDate(String dateString) throws SystemException {
        /* �������ڸ�ʽ */
        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        /* ��ʼ������ */
        Date validateDate = null;
        /* ����ת�� */
        if (dateString != null && !dateString.trim().equals("")) {
            try {
                validateDate = (Date) formatter.parse(dateString);
            } catch (ParseException e) {
                SystemException se = new SystemException("����ת��ʧ��", e);
                e.printStackTrace();
                throw (se);
            }
        }
        /* ����ת���õ����� */
        return validateDate;
    }

    /**
     * �ַ���ת��Ϊʱ��
     * @param dateString String ��ת�����ַ���
     * @return Date ��������
     * @throws SystemException
     */
    public static Date changeDatetime(String datetimeString) throws
            SystemException {
        /* �������ڸ�ʽ */
        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd kk:mm:ss");
        /* ��ʼ������ */
        Date validateDate = null;
        /* ����ת�� */
        if (datetimeString != null && !datetimeString.trim().equals("")) {
            try {
                validateDate = (Date) formatter.parse(datetimeString);
            } catch (ParseException e) {
                SystemException se = new SystemException("����ת��ʧ��", e);
                e.printStackTrace();
                throw (se);
            }
        }
        /* ����ת���õ����� */
        return validateDate;
    }

    /**
     * ����ָ���ĸ�ʽ��ʽ������
     *
     * @param date Date ����
     * @param dateFormat String ָ���ĸ�ʽ,��:yyyy-MM-dd��
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
     * ���˵Ĳ������Ȳ��㲹0
     */
    public static String fillZero(String inputStr, int length) {
        String val = inputStr;
        for (int i = 0; i < length - inputStr.length(); i++) {
            val = "0" + val;
        }
        return val;
    }

    /**
     * ȥ���ַ����Ŀ�ֵ
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
     * ת�������ͽ���﷽ʽΪ�����д����﷽ʽ
     *�紫��123.23 -- Ҽ�۷�ʰ��Բ��������
     * @param num String �����ͽ���﷽ʽ
     * @return String
     */
    public static String transNumToChinese(BigDecimal bigdMoneyNumber) {
        /*���Ľ�λ����*/
        String[] straChineseUnit = new String[] {
                                   "��", "��", "Բ", "ʰ", "��", "Ǫ", "��", "ʰ", "��",
                                   "Ǫ", "��", "ʰ", "��",
                                   "Ǫ"};
        /*���������ַ�����*/
        String[] straChineseNumber = new String[] {
                                     "��", "Ҽ", "��", "��", "��", "��", "½", "��",
                                     "��", "��"};
        String strChineseCurrency = "";
        /*����λ���*/
        boolean bZero = true;
        /*���Ľ�λ�±�*/
        int ChineseUnitIndex = 0;

        try {
            if (bigdMoneyNumber.intValue() == 0) {
                return "��Բ��";
            }

            /*����С�����֣���������*/
            double doubMoneyNumber = Math.round(bigdMoneyNumber.doubleValue() *
                                                100);

            /*�Ƿ���*/
            boolean bNegative = doubMoneyNumber < 0;

            /*ȡ����ֵ*/
            doubMoneyNumber = Math.abs(doubMoneyNumber);

            /*ѭ������ת������*/
            while (doubMoneyNumber > 0) {
                /*���Ĵ���(��С��λ)*/
                if (ChineseUnitIndex == 2 && strChineseCurrency.length() == 0) {
                    strChineseCurrency = strChineseCurrency + "��";
                }

                /*������λ�Ĵ���*/
                if (doubMoneyNumber % 10 > 0) {
                    strChineseCurrency = straChineseNumber[(int)
                                         doubMoneyNumber % 10] +
                                         straChineseUnit[ChineseUnitIndex] +
                                         strChineseCurrency;
                    bZero = false;
                }
                /*����λ�Ĵ���*/
                else {
                    /*Ԫ�Ĵ���(��λ)*/
                    if (ChineseUnitIndex == 2) {
                        /*����������*/
                        if (doubMoneyNumber > 0) {
                            strChineseCurrency = straChineseUnit[
                                                 ChineseUnitIndex] +
                                                 strChineseCurrency;
                            bZero = true;
                        }
                    }
                    /*������λ�Ĵ���*/
                    else if (ChineseUnitIndex == 6 || ChineseUnitIndex == 10) {
                        /*����������*/
                        if (doubMoneyNumber % 1000 > 0) {
                            strChineseCurrency = straChineseUnit[
                                                 ChineseUnitIndex] +
                                                 strChineseCurrency;
                        }
                    }

                    /*ǰһ��λ����Ĵ���*/
                    if (!bZero) {
                        strChineseCurrency = straChineseNumber[0] +
                                             strChineseCurrency;
                    }
                    bZero = true;
                }

                doubMoneyNumber = Math.floor(doubMoneyNumber / 10);
                ChineseUnitIndex++;
            }
            //�����Ĵ���
            if (bNegative) {
                strChineseCurrency = "��" + strChineseCurrency;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
        return strChineseCurrency;
    }

    /**
     * ת�����ֱ�﷽ʽΪ�������ֱ�﷽ʽ
     *�紫��123 -- һ����
     * @param num String ���ֱ�﷽ʽ
     * @return String
     */
    public static String transNumToChineseNum(String bigdNumber) {
        /*���Ľ�λ����*/
        String[] straChineseUnit = new String[] {
                                   "��", "һ", "��", "��", "��", "��", "��", "��", "��",
                                   "��"};
        String ChineseNum = "";
        for (int i = 0; i < bigdNumber.length(); i++) {
            ChineseNum +=
                    straChineseUnit[Integer.parseInt(String.valueOf(bigdNumber.
                    charAt(i)))];
        }
        return ChineseNum;
    }


    /*�ж�parentStr���Ƿ����sonstrԪ��
     sonStr String
     parentStr String //�ö��Ÿ鿪���ַ���
     return boolean
     */
    public static boolean isInStr(String sonStr, String parentStr) {
        boolean isIn = false;
        /*���parentStr�ַ���*/
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
     * ���һ��Ŀ�ʼʱ������ʱ��,��ȷ��ʱ���� flag: AM ��ʾ��ʼʱ�� PM ��ʾ����ʱ��
     * @param date String
     * @return Date
     */
    public static Date getDayTime(String date,String flag) {
        Calendar calendar = Calendar.getInstance();
        StringTokenizer strTok = new StringTokenizer(date, " _-./");
        String[] strAry = new String[strTok.countTokens()];
        /*�������*/
        int i = 0;
        while (strTok.hasMoreTokens()) {
            strAry[i++] = strTok.nextToken();
        }
        /*�������л��������*/
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
