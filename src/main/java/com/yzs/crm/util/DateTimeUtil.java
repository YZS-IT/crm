package com.yzs.crm.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateTimeUtil {

    public static String getSysTime(){

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        Date date = new Date();

        return sdf.format(date);

    }

    public static int GetCompareRes(String str1, String str2){
        return str1.compareTo(str2); //日期通过字符串比较大小,负数表示str1比str2小(位数不一样时,前面位数小)
    }

}
