package com.mulganov.test_task.middle.model;

import android.view.View;
import android.widget.ImageView;

import com.mulganov.test_task.middle.R;
import com.mulganov.test_task.middle.model.tools.Info;
import com.mulganov.test_task.middle.model.tools.Utils;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class Filter {
    public static final int ALL =    0;
    public static final int HOUR =   1;
    public static final int DAY =    2;
    public static final int MONTH =  3;

    private int filter;

    private ArrayList<ImageView> listView;

    public ArrayList<Info> all = new ArrayList<>();

    public Filter(){
    }

    public int getFilter() {
        return filter;
    }

    public void setFilter(int filter) {
        this.filter = filter;
    }

    public ArrayList<ImageView> getListView() {
        return listView;
    }

    public void setListView(ArrayList<ImageView> listView) {
        this.listView = listView;
    }

    public ArrayList<Info> upDate(View v, int filter){
        for (ImageView image: getListView()){
            image.setImageResource(R.drawable.radio_button_unchecked);
        }

        ((ImageView)v).setImageResource(R.drawable.radio_button_checked);

        this.filter = filter;

        if (filter == HOUR) return getHour();

        return getAll();
    }

    private ArrayList<Info> getHour(){
        ArrayList<Info> a = new ArrayList<>();

        Calendar calendar = new GregorianCalendar();

        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int month = calendar.get(Calendar.MONTH);

        for (Info i: all){
            if (i.getMonth() == month)
                if (i.getDay() == day)
                    if (i.getHour() == hour)
                        a.add(i);
        }

        return Utils.getParseArray(a);
    }

    private ArrayList<Info> getDay(){
        ArrayList<Info> a = new ArrayList<>();

        Calendar calendar = new GregorianCalendar();

        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int month = calendar.get(Calendar.MONTH);

        for (Info i: all){
            if (i.getMonth() == month)
                if (i.getDay() == day)
//                    if (i.getHour() == hour)
                        a.add(i);
        }

        return Utils.getParseArray(a);
    }

    private ArrayList<Info> getMonth(){
        ArrayList<Info> a = new ArrayList<>();

        Calendar calendar = new GregorianCalendar();

        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int month = calendar.get(Calendar.MONTH);

        for (Info i: all){
            if (i.getMonth() == month)
//                if (i.getDay() == day)
//                    if (i.getHour() == hour)
                    a.add(i);
        }

        return Utils.getParseArray(a);
    }


    public void setAll(ArrayList<Info> all) {
        this.all = all;
    }

    public ArrayList<Info> getAll(){
        return Utils.getParseArray(all);
    }
}
