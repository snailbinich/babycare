package com.jiayouwa.util;

import org.joda.time.*;

import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * Created by zhaoyan on 2017/2/4.
 */
public class TemplateUtil {

    public static final String headPrefix = "http://babycare-1253299307.costj.myqcloud.com/";

    public static int countBornDays(Date birthday){
        if(birthday == null){
            return 0;
        }
        Date now = new Date();
        return (int)TimeUnit.MILLISECONDS.toDays(now.getTime()-birthday.getTime());
    }


    public static String getToday(){
        DateTime dateTime = new DateTime();

        return dateTime.toString("yyyy-MM-dd");
    }
}
