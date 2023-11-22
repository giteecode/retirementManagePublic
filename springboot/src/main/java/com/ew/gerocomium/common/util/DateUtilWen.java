package com.ew.gerocomium.common.util;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.ObjUtil;

import java.util.Calendar;
import java.util.Date;

public class DateUtilWen {
    /**
     * Date字符串转Date
     *
     * @param dateStr
     * @return
     */
    public static Date dateStrToDate(String dateStr) {
        return ObjUtil.isEmpty(dateStr) ?
                null :
                DateUtil.parseDate(dateStr);
    }

    /**
     * Date转Date字符串
     *
     * @param date
     * @param "yyyy/MM/dd hh:mm:ss"
     * @return
     */
    public static String dateToDateStr(Date date, String formatStr) {
        return ObjUtil.isEmpty(date) ?
                null :
                DateUtil.format(date, formatStr);
    }

    /**
     * 根据时间获取【前n天】或【后n天】
     *
     * @param date
     * @param dayBdeforeNum
     * @return
     */
    public static Date getNowDayAroundTime(Date date, Integer dayBdeforeNum) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DATE, dayBdeforeNum);
        return calendar.getTime();
    }

    /**
     * 根据时间获取【前n月】或【后n月】
     *
     * @param date
     * @param monthBeforeNum
     * @return
     */
    public static Date getNowMonthAroundTime(Date date, Integer monthBeforeNum) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.MONTH, monthBeforeNum);
        return calendar.getTime();
    }

    /**
     * 获取时间所对应当天的开始时间
     *
     * @param date
     * @return
     */
    public static Date getDayStartTime(Date date) {
        if (date == null) {
            return null;
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        return calendar.getTime();
    }

    /**
     * 获取时间所对应当天的结束时间
     *
     * @param date
     * @return
     */
    public static Date getDayEndTime(Date date) {
        if (date == null) {
            return null;
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        return calendar.getTime();
    }

    /**
     * 获取时间所对应月份的第一天
     *
     * @param date
     * @return
     */
    public static Date getMonthFirstDay(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMinimum(Calendar.DAY_OF_MONTH));
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        return calendar.getTime();
    }

    /**
     * 获取时间所对应月份的最后一天
     *
     * @param date
     * @return
     */
    public static Date getMonthLastDay(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        return calendar.getTime();
    }

    /**
     * 获取时间所对应年份的第一天
     *
     * @param date
     * @return
     */
    public static Date getYearFirstDay(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.DAY_OF_YEAR, calendar.getActualMinimum(Calendar.DAY_OF_YEAR));
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        return calendar.getTime();
    }

    /**
     * 获取时间所对应年份的最后一天
     *
     * @param date
     * @return
     */
    public static Date getYearLastDay(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.DAY_OF_YEAR, calendar.getActualMaximum(Calendar.DAY_OF_YEAR));
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        return calendar.getTime();
    }

    /**
     * 根据时间获取当前月份
     *
     * @param date
     * @return
     */
    public static Integer getMonthNum(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.MONTH) + 1;
    }

    /**
     * 根据时间获取【年月】的格式化日期
     *
     * @param date
     * @param symbol
     * @return
     */
    public static String getYearToMonthFormatDateStr(Date date, String symbol) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int month = calendar.get(Calendar.MONTH) + 1;
        return String.valueOf(calendar.getWeekYear())
                .concat(symbol)
                .concat(month < 10 ? "0" + month : String.valueOf(month));
    }

    /**
     * 根据出生日期获取年龄
     *
     * @param birthday
     * @return
     */
    public static Integer getAgeByBirthDay(Date birthday) {
        //获取当前时间的Calendar对象
        Calendar cal = Calendar.getInstance();
        //获取出生日期的Calendar对象
        Calendar bir = Calendar.getInstance();
        bir.setTime(birthday);

        //如果出生日期大于当前日期，则返回0
        if (cal.before(birthday)) {
            return 0;
        }

        //取出当前年月日
        int nowYear = cal.get(Calendar.YEAR);
        int nowMonth = cal.get(Calendar.MONTH);
        int nowDay = cal.get(Calendar.DAY_OF_MONTH);
        //取出出生日期的年月日
        int birthYear = bir.get(Calendar.YEAR);
        int birthMonth = bir.get(Calendar.MONTH);
        int birthDay = bir.get(Calendar.DAY_OF_MONTH);

        //计算年份
        int age = nowYear - birthYear;

        //计算月份和日，看看是否大于当前月日，如果小于则减去一岁
        if (nowMonth < birthMonth || (nowMonth == birthMonth && nowDay < birthDay)) {
            age--;
        }
        return age;
    }

    /**
     * 获取两时间相差几月零几天
     *
     * @param startTime
     * @param endTime
     */
    public void getBetweenMonthAndDay(Date startTime, Date endTime) {
        startTime = getDayStartTime(startTime);
        endTime = getDayEndTime(endTime);
        Date nowTime = getDayEndTime(new Date());
        endTime = nowTime.before(endTime) ? nowTime : endTime;

        Date temporaryDate;
        long month = 0L;
        long day = 0L;
        while (startTime.before(endTime)) {
            temporaryDate = getNowMonthAroundTime(startTime, 1);
            if (temporaryDate.before(endTime)) {
                month++;
            } else {
                day = DateUtil.betweenDay(startTime, endTime, true);
            }
            startTime = temporaryDate;
        }

        System.out.println(month);
        System.out.println(day);
    }
}
