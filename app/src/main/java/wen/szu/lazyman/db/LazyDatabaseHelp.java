package wen.szu.lazyman.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by wen on 2017/10/17.
 */

public class LazyDatabaseHelp extends SQLiteOpenHelper{

    public static String DB_NAME="Lazyman.db";
    public static int DB_VERSION=1;
    public static String TABLE_ALARM="alarm";
    //alarm表中各列的名字
    public static String KEY_ALARM_ID="id";
    public static String KEY_ALARM_TIME="time";
    public static String KEY_ALARM_DATE="_date";
    public static String KEY_ALARM_ISOPENED="isopened";

    //alarm表的建表语句
    public static String CREATE_ALARM="create table "+TABLE_ALARM+" ("
            +KEY_ALARM_ID + " integer primary key autoincrement, "
            +KEY_ALARM_TIME + " integer, "
            +KEY_ALARM_DATE + " text, "
            +KEY_ALARM_ISOPENED + " integer )";

    private Context context;

    public LazyDatabaseHelp(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        this.context=context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //创建alarm表
        db.execSQL(CREATE_ALARM);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
