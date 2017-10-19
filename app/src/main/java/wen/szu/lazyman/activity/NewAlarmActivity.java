package wen.szu.lazyman.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.TimePicker;

import wen.szu.lazyman.R;
import wen.szu.lazyman.db.AlarmTableHelper;
import wen.szu.lazyman.model.Alarm;

/**
 * Created by wen on 2017/10/18.
 */

public class NewAlarmActivity extends Activity implements View.OnClickListener{

    private TextView titleTextview;
    private ImageView cancelImageview;
    private ImageView okImageview;
    private RelativeLayout repeatRelativeLayout;
    private RelativeLayout vibrationRelativeLayout;
    private RelativeLayout ringRelativeLayout;
    private RelativeLayout alarmNameRelativeLayout;
    private Button deleteAlarmButton;
    private TextView repeatTextView;
    private TextView ringTextView;
    private TextView alarmNameTextView;
    private RadioButton vibrationRadioButton;
    private TimePicker timePicker;

    private Alarm alarm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.new_alarm_layout);
        initView();
    }
    void initView(){
        titleTextview= (TextView) findViewById(R.id.new_alarm_top_textview);
        cancelImageview= (ImageView) findViewById(R.id.cancel_imageview);
        okImageview= (ImageView) findViewById(R.id.ok_imageview);
        repeatRelativeLayout= (RelativeLayout) findViewById(R.id.repeat_date_relativelayout);
        vibrationRelativeLayout= (RelativeLayout) findViewById(R.id.vibration_relativelayout);
        ringRelativeLayout= (RelativeLayout) findViewById(R.id.ring_relativelayout);
        alarmNameRelativeLayout= (RelativeLayout) findViewById(R.id.alarm_name_relativelayout);
        deleteAlarmButton= (Button) findViewById(R.id.delete_alarm_button);
        repeatTextView= (TextView) findViewById(R.id.repeat_date_textview);
        ringTextView= (TextView) findViewById(R.id.ring_textview);
        alarmNameTextView= (TextView) findViewById(R.id.alarm_name_textview);
        vibrationRadioButton= (RadioButton) findViewById(R.id.vibration_radiobutton);
        timePicker= (TimePicker) findViewById(R.id.new_alarm_timepicker);
        initAlarmInfo();
        setListener();
    }

    //为各个控件设置相应的初始化值
    void initAlarmInfo(){
        Intent intent=getIntent();
        String title=intent.getStringExtra("title");
        if(title!=null&&intent.getStringExtra("title").equals("edit"))
            titleTextview.setText("编辑闹钟");
        else
            titleTextview.setText("新建闹钟");

        if(intent.getIntExtra("alarmId",-1)!=-1){
            alarm = AlarmTableHelper.getAlarmById(intent.getIntExtra("alarmId",-1));
        }else{
            alarm=new Alarm();
        }
        repeatTextView.setText(alarm.getDateListString());
        ringTextView.setText(alarm.getRingName());
        alarmNameTextView.setText(alarm.getName());

    }

    void setListener(){

        cancelImageview.setOnClickListener(this);
        deleteAlarmButton.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.cancel_imageview:
                showCancelDialog();
                break;
        }
    }

    //显示取消确认对话框
    private void showCancelDialog(){
        final AlertDialog.Builder normalDialog =
                new AlertDialog.Builder(NewAlarmActivity.this);
        //normalDialog.setIcon(R.drawable.icon_dialog);
        AlertDialog dialog=null;
        normalDialog.setTitle("提示");
        normalDialog.setMessage("确定放弃修改?");
        normalDialog.setPositiveButton("确定",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                });
        normalDialog.setNegativeButton("取消",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        // 显示
        normalDialog.setCancelable(false);
        dialog=normalDialog.show();
    }
}
