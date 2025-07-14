package com.louis.longagocode.timer;

import java.util.Calendar;
import java.util.Date;

/**
 * @author louis
 * <p>
 * Date: 2019/12/23
 * Description:
 */
public class CalenderTest {

    public static Date getBeforeDateByMins(Date date , int mins) {
        Calendar cal = Calendar.getInstance();// 使用默认时区和语言环境获得一个日历。
        cal.setTime(date);
        cal.add(Calendar.MINUTE, -mins);// 取当前日期的前ji分.
        return cal.getTime();
    }

    public static void main(String[] args) {
        Date beforeDateByMins = getBeforeDateByMins(new Date(), 1200);
        System.out.println(beforeDateByMins.getTime());
    }
}
