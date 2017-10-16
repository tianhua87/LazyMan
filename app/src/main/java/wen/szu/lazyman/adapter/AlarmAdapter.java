package wen.szu.lazyman.adapter;

import android.content.Context;
import android.media.Image;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

import java.util.List;

import wen.szu.lazyman.R;
import wen.szu.lazyman.model.Alarm;
import wen.szu.lazyman.utils.DateUtil;

/**
 * Created by wen on 2017/10/16.
 */

public class AlarmAdapter extends RecyclerView.Adapter<AlarmAdapter.ViewHolder>{

    private Context context;
    private int resourceID;
    private List<Alarm> alarmList;

    public AlarmAdapter(Context context, int resourceID, List<Alarm> alarmList) {
        this.context=context;
        this.resourceID=resourceID;
        this.alarmList=alarmList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //false
        View view= LayoutInflater.from(context).inflate(resourceID,parent,false);
        ViewHolder viewHolder=new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Alarm alarm=alarmList.get(position);
        if(alarm.isOpened()==true)
            holder.swift_radioButton.setChecked(true);
        else
            holder.swift_radioButton.setChecked(false);
        holder.timeTextview.setText(DateUtil.getHourAndMinute(alarm.getTime()));
        StringBuilder date=new StringBuilder();
        for (int i=0;i<alarm.getDate().size()-1;i++)
            date.append(alarm.getDate().get(i)).append(',');
        date.append(alarm.getDate().get(alarm.getDate().size()-1));
        holder.dateTextview.setText(date.toString());
    }

    @Override
    public int getItemCount() {
        return alarmList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder{

        public TextView timeTextview;
        public TextView dateTextview;
        public RadioButton swift_radioButton;

        public ViewHolder(View itemView) {
            super(itemView);
            timeTextview= (TextView) itemView.findViewById(R.id.time_textview);
            dateTextview= (TextView) itemView.findViewById(R.id.date_textview);
            swift_radioButton= (RadioButton) itemView.findViewById(R.id.swift_radiobutton);

        }



    }
}
