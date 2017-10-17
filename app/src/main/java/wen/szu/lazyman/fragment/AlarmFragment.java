package wen.szu.lazyman.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import wen.szu.lazyman.R;
import wen.szu.lazyman.adapter.AlarmAdapter;
import wen.szu.lazyman.model.Alarm;
import wen.szu.lazyman.view.RecycleViewDivider;

/**
 * Created by wen on 2017/10/15.
 */

public class AlarmFragment extends Fragment{

    private View rootView=null;
    private RecyclerView alarmListView;
    private ImageView addAlarmImageView;
    private AlarmAdapter alarmAdapter;
    private List<Alarm> alarmList;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        if(rootView==null) {
            rootView = inflater.inflate(R.layout.alarm_fragment_layout, container,false);
        }
        initDate();
        initView();

        return rootView;
    }

    void initView(){

        alarmListView= (RecyclerView) rootView.findViewById(R.id.alarm_listview);
        addAlarmImageView= (ImageView) rootView.findViewById(R.id.add_alarm_imageview);
        alarmAdapter=new AlarmAdapter(getActivity(),R.layout.alarm_item_layout,alarmList);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getActivity());
        alarmListView.setLayoutManager(linearLayoutManager);
        alarmListView.addItemDecoration(new RecycleViewDivider(getActivity(),LinearLayoutManager.HORIZONTAL));
        alarmListView.setAdapter(alarmAdapter);

    }
    void initDate(){
        alarmList=new ArrayList<>();
        List<String> dList=new ArrayList<>();
        dList.add(Alarm.MONDAY);
        dList.add(Alarm.WEDNESDAY);
        alarmList.add(new Alarm(new Date(),dList,true));
        alarmList.add(new Alarm(new Date(),dList,false));
        alarmList.add(new Alarm(new Date(),dList,true));
        alarmList.add(new Alarm(new Date(),dList,false));
        alarmList.add(new Alarm(new Date(),dList,true));
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (rootView != null) {
            ((ViewGroup) rootView.getParent()).removeView(rootView);
        }
        Log.e("lazyman","AlarmFragment 销毁");
    }
}
