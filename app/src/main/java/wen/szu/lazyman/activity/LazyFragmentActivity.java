package wen.szu.lazyman.activity;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;

import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import java.util.ArrayList;
import java.util.List;

import wen.szu.lazyman.R;
import wen.szu.lazyman.adapter.LazyFragmentPagerAdapter;
import wen.szu.lazyman.fragment.AlarmFragment;
import wen.szu.lazyman.fragment.MemoFragment;
import wen.szu.lazyman.fragment.MyselfFragment;

public class LazyFragmentActivity extends FragmentActivity {

    private ViewPager lazyManPager;
    private List<Fragment> fragmentList;
    private RadioGroup radioGroup;
    private RadioButton rbAlarm,rbMemo,rbMyself;
    private Fragment alarmFragment,memoFragment,myselfFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        setContentView(R.layout.lazy_fragment_activity_layout);

        intiView();

    }

    void intiView(){
        //底部菜单栏
        radioGroup = (RadioGroup) findViewById(R.id.radio_group);
        rbAlarm = (RadioButton) findViewById(R.id.rb_alarm);
        rbMemo = (RadioButton) findViewById(R.id.rb_memo);
        rbMyself = (RadioButton) findViewById(R.id.rb_myself);

        lazyManPager= (ViewPager) findViewById(R.id.lazyman_pager);

        radioGroup.setOnCheckedChangeListener(new MyCheckedListener(lazyManPager,rbAlarm,rbMemo,rbMyself));

        //新建Fragment，并添加到fragmentList中去
        fragmentList=new ArrayList<>();
        alarmFragment=new AlarmFragment();
        memoFragment=new MemoFragment();
        myselfFragment=new MyselfFragment();
        fragmentList.add(alarmFragment);
        fragmentList.add(memoFragment);
        fragmentList.add(myselfFragment);
        //创建LazyFragmentPagerAdapter适配器
        LazyFragmentPagerAdapter pagerAdapter=new LazyFragmentPagerAdapter(getSupportFragmentManager(),fragmentList);
        //绑定适配器
        lazyManPager.setAdapter(pagerAdapter);
        lazyManPager.setCurrentItem(0);
        lazyManPager.setOnPageChangeListener(new MyPageChangeListener(radioGroup,rbAlarm,rbMemo,rbMyself));
    }
}

//RadioGroup监听器
class MyCheckedListener implements RadioGroup.OnCheckedChangeListener {
    private ViewPager lazyManPager;
    private RadioButton rbAlarm,rbMemo,rbMyself;

    public MyCheckedListener(ViewPager lazyManPager) {
        this.lazyManPager = lazyManPager;
    }

    public MyCheckedListener(ViewPager lazyManPager, RadioButton rbAlarm, RadioButton rbMemo, RadioButton rbMyself) {
        this.lazyManPager = lazyManPager;
        this.rbAlarm = rbAlarm;
        this.rbMemo = rbMemo;
        this.rbMyself = rbMyself;
    }

    @Override
    public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
        switch (checkedId) {
            case R.id.rb_alarm:
                lazyManPager.setCurrentItem(0, false);
                rbAlarm.setBackgroundResource(R.drawable.alarm_green);
                rbMemo.setBackgroundResource(R.drawable.memo_gray);
                rbMyself.setBackgroundResource(R.drawable.myself_gray);
                break;
            case R.id.rb_memo:
                lazyManPager.setCurrentItem(1, false);
                rbAlarm.setBackgroundResource(R.drawable.alarm_gray);
                rbMemo.setBackgroundResource(R.drawable.memo_green);
                rbMyself.setBackgroundResource(R.drawable.myself_gray);
                break;
            case R.id.rb_myself:
                lazyManPager.setCurrentItem(2, false);
                rbAlarm.setBackgroundResource(R.drawable.alarm_gray);
                rbMemo.setBackgroundResource(R.drawable.memo_gray);
                rbMyself.setBackgroundResource(R.drawable.myself_green);
                break;
        }
    }
}
//viewPager监听器
class MyPageChangeListener implements ViewPager.OnPageChangeListener{
    RadioGroup radioGroup;
    private RadioButton rbAlarm,rbMemo,rbMyself;
    public MyPageChangeListener(RadioGroup radioGroup) {
        this.radioGroup = radioGroup;
    }

    public MyPageChangeListener(RadioGroup radioGroup, RadioButton rbAlarm, RadioButton rbMemo, RadioButton rbMyself) {
        this.radioGroup = radioGroup;
        this.rbAlarm = rbAlarm;
        this.rbMemo = rbMemo;
        this.rbMyself = rbMyself;
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {

        switch (position) {
            case 0:
                //防止check 多次调用onCheckChang，导致滑动卡顿
                ((RadioButton)radioGroup.findViewById(R.id.rb_alarm)).setChecked(true);
                radioGroup.check(R.id.rb_alarm);
                //切换背景图片
                rbAlarm.setBackgroundResource(R.drawable.alarm_green);
                rbMemo.setBackgroundResource(R.drawable.memo_gray);
                rbMyself.setBackgroundResource(R.drawable.myself_gray);
                break;
            case 1:
                ((RadioButton)radioGroup.findViewById(R.id.rb_memo)).setChecked(true);
                radioGroup.check(R.id.rb_memo);
                rbAlarm.setBackgroundResource(R.drawable.alarm_gray);
                rbMemo.setBackgroundResource(R.drawable.memo_green);
                rbMyself.setBackgroundResource(R.drawable.myself_gray);
                break;
            case 2:
                ((RadioButton)radioGroup.findViewById(R.id.rb_myself)).setChecked(true);
                radioGroup.check(R.id.rb_myself);
                rbAlarm.setBackgroundResource(R.drawable.alarm_gray);
                rbMemo.setBackgroundResource(R.drawable.memo_gray);
                rbMyself.setBackgroundResource(R.drawable.myself_green);
                break;

        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}