package wen.szu.lazyman.model;

import java.util.Date;
import java.util.List;

/**
 * Created by wen on 2017/10/16.
 */

public class Alarm {

    public static String MONDAY="周一";
    public static String TUESDAY="周二";
    public static String WEDNESDAY="周三";
    public static String THURSDAY="周四";
    public static String FRIDAY="周五";
    public static String SATURDAY="周六";
    public static String SUNDAY="周日";
    public static String EVERY_DAY="每天";

    private int id;
    private Date time;
    private List<String> date;
    private boolean isOpened;

    public Alarm(Date time, List<String> date, boolean isOpened) {
        this.time = time;
        this.date = date;
        this.isOpened = isOpened;
    }

    public Alarm(int id,Date time, List<String> date,  boolean isOpened) {
        this.time = time;
        this.date = date;
        this.id = id;
        this.isOpened = isOpened;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<String> getDate() {
        return date;
    }

    public void setDate(List<String> date) {
        this.date = date;
    }

    public boolean isOpened() {
        return isOpened;
    }

    public void setOpened(boolean opened) {
        isOpened = opened;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }
}
