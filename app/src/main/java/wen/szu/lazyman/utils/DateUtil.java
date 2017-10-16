package wen.szu.lazyman.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by wen on 2017/10/17.
 */

public class DateUtil {

    public static String getHourAndMinute(Date date){
        SimpleDateFormat formatter = new SimpleDateFormat("HH:mm");
        return formatter.format(date);
    }

}
