package wen.szu.lazyman.db;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import wen.szu.lazyman.LazyManApplication;
import wen.szu.lazyman.model.Alarm;

/**
 * Created by wen on 2017/10/17.
 */

public class AlarmTableHelper {

    private static LazyDatabaseHelp lazyDatabaseHelp=null;
    private static SQLiteDatabase db=null;

    //插入一个闹钟到数据库中
    public static void addAlarm(Alarm alarm){

        try {
            openWritableDB();
            ContentValues values=new ContentValues();
            values.put(LazyDatabaseHelp.KEY_ALARM_TIME, alarm.getTime().getTime());
            values.put(LazyDatabaseHelp.KEY_ALARM_DATE,alarm.getDateListString());
            if(alarm.isOpened()==true)
                values.put(LazyDatabaseHelp.KEY_ALARM_ISOPENED,1);
            else
                values.put(LazyDatabaseHelp.KEY_ALARM_ISOPENED,0);
            db.insert(LazyDatabaseHelp.TABLE_ALARM,null,values);
            //获取插入的那条数据的自增长ID
            Cursor cursor=db.rawQuery("select last_insert_rowid() from "+LazyDatabaseHelp.TABLE_ALARM,null);
            cursor.moveToFirst();
            alarm.setId(cursor.getInt(0));

        }catch (Exception e){
            Log.e("LazyDatabase","addAlarm 出现错误  "+e.getMessage());
        }finally {
            closeDB();
        }
    }

    //从数据库获取闹钟列表
    public static List<Alarm> getAlarmList(){
        List<Alarm> alarmList=new ArrayList<>();
        try {
            openReadableDB();
            Cursor cursor=db.query(LazyDatabaseHelp.TABLE_ALARM,null,null,null,null,null,null);

            if(cursor.moveToFirst()){
                do{
                    int id=cursor.getInt(cursor.getColumnIndex(LazyDatabaseHelp.KEY_ALARM_ID));
                    long time=cursor.getLong(cursor.getColumnIndex(LazyDatabaseHelp.KEY_ALARM_TIME));
                    String dateListStr=cursor.getString(cursor.getColumnIndex(LazyDatabaseHelp.KEY_ALARM_DATE));
                    int isOpenedInt=cursor.getInt(cursor.getColumnIndex(LazyDatabaseHelp.KEY_ALARM_ISOPENED));
                    List<String> dateList=Arrays.asList(dateListStr.split(","));
                    boolean isOpened=isOpenedInt==0?false:true;
                    alarmList.add(new Alarm(id,new Date(time),dateList,isOpened));

                }while (cursor.moveToNext());
            }

        }catch (Exception e){
            Log.e("LazyDatabase","getAlarmList 出现错误  "+e.getMessage());
        }finally {
            closeDB();
            return alarmList;
        }
    }

    //连接可写数据库
    private static void openWritableDB(){
        lazyDatabaseHelp=new LazyDatabaseHelp(LazyManApplication.getContextObject(),
                LazyDatabaseHelp.DB_NAME,null,LazyDatabaseHelp.DB_VERSION);
        db=lazyDatabaseHelp.getWritableDatabase();
    }
    //连接可读数据库
    private static void openReadableDB(){
        lazyDatabaseHelp=new LazyDatabaseHelp(LazyManApplication.getContextObject(),
                LazyDatabaseHelp.DB_NAME,null,LazyDatabaseHelp.DB_VERSION);
        db=lazyDatabaseHelp.getReadableDatabase();
    }
    //关闭数据库
    private static void closeDB(){
        try {
            if(db!=null) {
                db.close();
                db=null;
            }
            if(lazyDatabaseHelp!=null) {
                lazyDatabaseHelp.close();
                lazyDatabaseHelp=null;
            }
        }catch (Exception e){
            Log.e("LazyDatabase","closeDB 出现错误  "+e.getMessage());
        }

    }
}
