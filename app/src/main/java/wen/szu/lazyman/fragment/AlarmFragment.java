package wen.szu.lazyman.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import wen.szu.lazyman.R;

/**
 * Created by wen on 2017/10/15.
 */

public class AlarmFragment extends Fragment{

    private View rootView=null;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        if(rootView==null) {
            rootView = inflater.inflate(R.layout.alarm_fragment_layout, container, false);
            Log.e("lazyman", "AlarmFragment 创建");
        }
        return rootView;
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
