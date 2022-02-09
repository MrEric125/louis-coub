package com.louis.coub.escustomer.collect;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

@Slf4j
public class DateTimeUtils {


    public static final String DATE_yyyyMMddHHmm="yyyyMMddHHmm";
    public static final String DATE_YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";

    public static Date dateParse(String sDate, String pattern) throws ParseException {
        SimpleDateFormat dateFm = new SimpleDateFormat(pattern);
        return dateFm.parse(sDate);
    }
    public static String formatDateTime(Long dateTime, String pattern) {
        if (dateTime == null || StringUtils.isBlank(pattern)){
            throw new NullPointerException("参数不能为空");
        }
        final SimpleDateFormat formatter = new SimpleDateFormat(pattern, Locale.SIMPLIFIED_CHINESE);
        formatter.setTimeZone(TimeZone.getTimeZone("Asia/Shanghai"));
        return formatter.format(new Date(dateTime));
    }

    public static String formatDateTime(Date date, String pattern) {
        if (date == null || StringUtils.isBlank(pattern)){
            throw new NullPointerException("参数不能为空");
        }

        final SimpleDateFormat formatter = new SimpleDateFormat(pattern, Locale.SIMPLIFIED_CHINESE);
        formatter.setTimeZone(TimeZone.getTimeZone("Asia/Shanghai"));
        return formatter.format(date);

    }
    
    public static long dateTimeToLong(String date, String pattern) {
    	long time = 0L;
    	try {
			time = dateParse(date, pattern).getTime();
		} catch (ParseException ex) {
			log.error("时间转换为long型失败:",ex);
		}
    	
    	return time;
    }

    public static String formatDateTime(Date date) {
        return formatDateTime(date,DATE_YYYY_MM_DD_HH_MM_SS);
    }

    public static String formatDateTime() {
        return formatDateTime(new Date(System.currentTimeMillis()));
    }

    public static Date dateParse(String sDate) throws ParseException {
        return dateParse(sDate,DATE_YYYY_MM_DD_HH_MM_SS);
    }
}