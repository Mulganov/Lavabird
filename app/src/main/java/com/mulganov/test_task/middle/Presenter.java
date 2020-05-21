package com.mulganov.test_task.middle;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.view.View;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;

import com.mulganov.test_task.middle.model.Filter;
import com.mulganov.test_task.middle.model.service.MyService;
import com.mulganov.test_task.middle.model.tools.Utils;

public class Presenter {
    private Filter filter;
    private MainActivity activity;

    public Presenter(MainActivity activity){
        filter = new Filter();
        this.activity = activity;

        filter.setAll(Utils.getAllInfo(activity));

        activity.addInfo(filter.getAll());
    }

    public void onClickAll(View v){
        activity.addInfo(filter.upDate(v, Filter.ALL));
    }

    public void onClickHour(View v){
        activity.addInfo(filter.upDate(v, Filter.HOUR));
    }

    public void onClickDay(View v){
        activity.addInfo(filter.upDate(v, Filter.DAY));
    }

    public void onClickMonth(View v){
        activity.addInfo(filter.upDate(v, Filter.MONTH));
    }

    public void onClickStart(TextView text, ConstraintLayout layout){
        Context context = text.getContext();

        if (!Utils.isNotificationServiceEnabled(context)){
            context.startActivity(new Intent("android.settings.ACTION_NOTIFICATION_LISTENER_SETTINGS"));
        }

        if (MyService.isRunning(context)){
            text.setText("Start");

            layout.setBackgroundResource(R.drawable.round_button_start);

            context.stopService(new Intent(context, MyService.class));
        }else{
            text.setText("Stop");

            layout.setBackgroundResource(R.drawable.round_button_stop);

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                context.startForegroundService(new Intent(context, MyService.class));
            }else
                context.startService(new Intent(context, MyService.class));

        }
    }

    public Filter getFilter() {
        return filter;
    }

}
