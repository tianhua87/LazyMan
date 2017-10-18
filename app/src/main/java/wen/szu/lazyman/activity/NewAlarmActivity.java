package wen.szu.lazyman.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import wen.szu.lazyman.R;

/**
 * Created by wen on 2017/10/18.
 */

public class NewAlarmActivity extends Activity{

    private TextView titleTextview;
    private ImageView cancelImageview;
    private ImageView okImageview;

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

        Intent intent=getIntent();
        String title=intent.getStringExtra("title");
        if(title!=null&&intent.getStringExtra("title").equals("edit"))
            titleTextview.setText("编辑闹钟");
        else
            titleTextview.setText("新建闹钟");
        Toast.makeText(this,intent.getIntExtra("alarmId",-1)+"",Toast.LENGTH_SHORT).show();
    }
}
