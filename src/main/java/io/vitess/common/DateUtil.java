package io.vitess.common;

import org.springframework.util.StringUtils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 日期转换处理
 * @author weiy add by 2011-08-09
 * @edit fanht
 */

public abstract class DateUtil {
    public static String YMD = "yyyy-MM-dd";
    
    public static final String PATTERN_SIMPLE = "yyyy-MM-dd";
    
    public static final String PATTERN_SIMPLE_YYYYMMDD = "yyyyMMdd";

    public static final String PATTERN_NORMAL = "yyyy-MM-dd HH:mm:ss";

    public static final String PATTERN_FULL = "yyyy-MM-dd HH:mm:ss S";
    
    public static final String PATTERN_SPECIAL = "yyyyMMddHHmmss";
    
    public static final String PATTERN_FULL1 = "MM/dd/yyyy HH:mm:ss.S";
    
    public static final String PATTERN_ALL = "yyyy-MM-dd HH:mm:ss:SSS";
    
    public static final String PATTERN_SPECIAL_ALL = "yyyyMMddHHmmssSSS";
    
    public static final String PATTERN_SPECIAL_APPLE = "dd/MM/yy HH:mm:ss";
    
    public static boolean DateCompare(Date compareDate, Date dateBegin, Date dateEnd) {
        if (null == compareDate || null == dateBegin || null == dateEnd) {
            return false;
        }
        /*if (Hour(compareDate) <= Hour(dateEnd) && Hour(compareDate) >= Hour(dateBegin)) {
            return true;
        } else {
            return false;   
        }*/
        if(compareDate.compareTo(dateBegin)>=0 && compareDate.compareTo(dateEnd)<=0) {
            return true;
        } else {
            return false;
        }
    }
    
    public static boolean DateCompare(Date dateBegin, Date dateEnd) {
        if (null == dateBegin || null == dateEnd) {
            return false;
        }
        if(dateEnd.compareTo(dateBegin)>=0) {
            return true;
        } else {
            return false;
        }
    }
    
    /**
     * 日期转换，一天中第一秒
     * @param date
     */
    public static Date getFirstSencond(String dateStr){
        Date date = parseDate(dateStr,YMD);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH), 00, 00, 00);
        return calendar.getTime();
    }
    
    /**
     * 日期转换，一天中最后一秒
     * @param date
     */
    public static Date getLastSencond(String dateStr){
        Date date = parseDate(dateStr,YMD);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH), 23, 59, 59);
        return calendar.getTime();
    }
    
    /**
     * 日期转换，一天中最后一秒
     * @param date
     */
    public static Date getLastSencond(Date date){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH), 23, 59, 59);
        return calendar.getTime();
    }

    /**
     * 日期时间是否在给定时分秒比较(不计算日期,只计算时分秒)
     * 
     * @param date
     * @param time format(hh:mm:ss)
     * @return the value 0 if the time represented by the argument is equal to the time represented
     *         by this Calendar; a value less than 0 if the time of this Calendar is before the time
     *         represented by the argument; and a value greater than 0 if the time of this Calendar
     *         is after the time represented by the argument.
     */
    public static int compareToTime(Date date, String time) {
        Calendar baseDate = Calendar.getInstance();
        baseDate.setTime(date);
        Calendar cmpDate = Calendar.getInstance();
        cmpDate.setTime(getDateWithTime(date, time));
        return baseDate.compareTo(cmpDate);
    }

    public static Date getDateWithTime(Date date, String time) {
        Pattern p = Pattern.compile("[0-9]{2}:[0-9]{2}:[0-9]{2}");
        Matcher m = p.matcher(time);
        if (!m.matches()) {
            throw new IllegalArgumentException();
        }
        String[] tmp = time.split(":");
        Calendar cmpDate = Calendar.getInstance();
        cmpDate.setTime(date);
        cmpDate.set(Calendar.HOUR_OF_DAY, Integer.parseInt(tmp[0], 10));
        cmpDate.set(Calendar.MINUTE, Integer.parseInt(tmp[1], 10));
        cmpDate.set(Calendar.SECOND, Integer.parseInt(tmp[2], 10));
        return cmpDate.getTime();
    }

    /**
     * get the current time
     */
    public static Date now() {
        return Calendar.getInstance().getTime();
    }

    public static Date today() {
        return roundDate(now());
    }

    /**
     * Add specified number of days to a date.
     */
    public static Date addDays(Date date, int num) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.DATE, num);
        return c.getTime();
    }
    
    /**
     * 时间运算,分钟
     * @param date
     * @param num
     * @return
     */
    public static Date addMinute(Date date, int num){
    	Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.MINUTE, num);
        return c.getTime();
    }

    /**
     * Set hour, minute, second, millisecond of _c to 0.
     */
    public static Calendar roundCalendar(Calendar _c) {
        _c.set(Calendar.HOUR_OF_DAY, 0);
        _c.set(Calendar.MINUTE, 0);
        _c.set(Calendar.SECOND, 0);
        _c.set(Calendar.MILLISECOND, 0);
        return _c;
    }

    /**
     * Set hour, minute, second, millisecond of _c to 0.
     */
    public static Date roundDate(Date _d) {
        Calendar c = Calendar.getInstance();
        c.setTime(_d);
        return roundCalendar(c).getTime();
    }

    /**
     * convert to the sql date
     */
    public static java.sql.Date toSQLDate(Date date) {
        return new java.sql.Date(date.getTime());
    }

    /**
     * convert to the utility date
     */
    public static Date toUtilDate(java.sql.Date date) {
        return new Date(date.getTime());
    }

    /**
     * Get the interval days of the two date.If return result is less than 0, date _one is before
     * _two; if the return result is greater than 0, date _one is after _two.
     */
    public static int getInterval(Date _one, Date _two) {
        Calendar one = Calendar.getInstance();
        one.setTime(_one);
        Calendar two = Calendar.getInstance();
        two.setTime(_two);
        // System.out.println("One date is " + _one.toString() + ", and two date is " +
        // _two.toString());

        int yearOne = one.get(Calendar.YEAR);
        int yearTwo = two.get(Calendar.YEAR);
        int dayOne = one.get(Calendar.DAY_OF_YEAR);
        int dayTwo = two.get(Calendar.DAY_OF_YEAR);

        // System.out.println("One date is the number " + dayOne + " of " + yearOne);
        // System.out.println("Two date is the number " + dayTwo + " of " + yearTwo);

        if (yearOne == yearTwo) {
            return dayOne - dayTwo;
        } else if (yearOne < yearTwo) {
            int yearDays = 0;
            while (yearOne < yearTwo) {
                if (isLeapyear(yearOne)) {
                    yearDays += 366;
                } else {
                    yearDays += 365;
                }

                yearOne += 1;
            }
            return dayOne - yearDays - dayTwo;
        } else {
            int yearDays = 0;
            while (yearTwo < yearOne) {
                if (isLeapyear(yearTwo)) {
                    yearDays += 366;
                } else {
                    yearDays += 365;
                }

                yearTwo += 1;
            }
            return dayOne - dayTwo + yearDays;
        }
    }

    public static int getMonthInterval(Date _one, Date _two) {
        Calendar one = Calendar.getInstance();
        one.setTime(_one);
        Calendar two = Calendar.getInstance();
        two.setTime(_two);
        int yearInt = two.get(Calendar.YEAR) - one.get(Calendar.YEAR);
        int monthInt = two.get(Calendar.MONTH) - one.get(Calendar.MONTH);
        int dayInt = two.get(Calendar.DAY_OF_MONTH) - one.get(Calendar.DAY_OF_MONTH);
        return yearInt * 12 + monthInt + (dayInt > 0 ? 1 : 0);
    }

    public static boolean isLeapyear(Date _date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(_date);
        int year = calendar.get(Calendar.YEAR);
        return isLeapyear(year);
    }

    /**
     * 是否闰年
     * @param _year
     * @return
     */
    public static boolean isLeapyear(String _year) {
        return isLeapyear(Integer.valueOf(_year));
    }
    
    public static boolean isLeapyear(int _year) {
        if (_year % 4 == 0) {
            if (_year % 100 == 0) {
                if (_year % 400 == 0) {
                    return true;
                } else {
                    return false;
                }
            } else {
                return true;
            }
        } else {
            return false;
        }
    }

    /**
     * 制定日期YYYY-MM-DD
     * 
     * @param date
     * @return
     */
    public static boolean isYearDate(String date) {
        String EL =
                "^((\\d{2}(([02468][048])|([13579][26]))[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])))))|(\\d{2}(([02468][1235679])|([13579][01345789]))[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|(1[0-9])|(2[0-8]))))))";
        Pattern p = Pattern.compile(EL);
        Matcher m = p.matcher(date);
        boolean b = m.matches();
        return b;
    }
    
    /**
     * 制定日期yyyy-MM-dd HH:mm:ss
     * 
     * @param date
     * @return
     */
    public static boolean isDate(String date) {
        String EL =
                "([0-9]{3}[1-9]|[0-9]{2}[1-9][0-9]{1}|[0-9]{1}[1-9][0-9]{2}|[1-9][0-9]{3})-(((0[13578]|1[02])-(0[1-9]|[12][0-9]|3[01]))|((0[469]|11)-(0[1-9]|[12][0-9]|30))|(02-(0[1-9]|[1][0-9]|2[0-8]))) ([01]?\\d|2[0-3]):[0-5]?\\d:[0-5]?\\d";
        Pattern p = Pattern.compile(EL);
        Matcher m = p.matcher(date);
        boolean b = m.matches();
        return b;
    }
    
    public static Date parseDate(String date, String pattern){
        SimpleDateFormat formatter = new SimpleDateFormat(pattern);
        try {
            return formatter.parse(date);
        } catch (Exception e) {
            return null;
        }
    }
    
    public static String formatDate(Date date, String pattern){
        SimpleDateFormat formatter = new SimpleDateFormat(pattern);
        try {
            return formatter.format(date);
        } catch (Exception e) {
            return null;
        }
    }
    
    public static String longToDateStr(long dateLong, String pattern){
        java.util.Date dt = new Date(dateLong);
        String date = formatDate(dt,pattern);
        return date;
    }
    
    public static Date parse(String src) {
        SimpleDateFormat util = new SimpleDateFormat(PATTERN_SIMPLE);
        Date ret = null;
        if (StringUtils.hasText(src)) {
            try {
                util.applyPattern(PATTERN_NORMAL);
                ret = util.parse(src);
            } catch (Exception ex) {
            }
            if (ret == null) {
                try {
                    util.applyPattern(PATTERN_SIMPLE);
                    ret = util.parse(src);
                } catch (Exception ex) {
                }
            }
            if (ret == null) {
                try {
                    util.applyPattern(PATTERN_FULL);
                    ret = util.parse(src);
                } catch (Exception ex) {
                }
            }
            if (ret == null) {
                try {
                    util.applyPattern(PATTERN_FULL1);
                    ret = util.parse(src);
                } catch (Exception ex) {
                }
            }
            if (ret == null) {
                try {
                    util.applyPattern(PATTERN_SPECIAL_ALL);
                    ret = util.parse(src);
                } catch (Exception ex) {
                }
            }
            if (ret == null) {
                try {
                    util.applyPattern(PATTERN_SPECIAL);
                    ret = util.parse(src);
                } catch (Exception ex) {
                }
            }
           
            
        }
        if (ret == null) {
            throw new IllegalArgumentException("## cant parse to Date . not supported by default pattern: $" + src + "$");
        }
        return ret;
    }
    /**
     * 
     * @Description:  把配置的时间进行 时间 加减操作。
     * @author zhiyong.shi
     * 2017年2月10日
     */
    public static Date getChangeDate(Date date , int year, int month, int day, int hour){
    	 Calendar cal = Calendar.getInstance();
    	 cal.setTime(date);
    	 cal.add(Calendar.YEAR, year);
    	 cal.add(Calendar.MONTH, month);
    	 cal.add(Calendar.DAY_OF_MONTH, day);
    	 cal.add(Calendar.HOUR_OF_DAY, hour);
		return cal.getTime();
    }
    public static void main(String[] args) {
    	System.out.println(isDate("2016-2-29 01:20:21"));
    }
}
