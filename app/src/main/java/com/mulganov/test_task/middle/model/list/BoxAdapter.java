package com.mulganov.test_task.middle.model.list;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.mulganov.test_task.middle.R;
import com.mulganov.test_task.middle.model.tools.Info;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class BoxAdapter extends BaseAdapter {
    Context ctx;
    LayoutInflater lInflater;
    ArrayList<Info> objects;

    public BoxAdapter(Context context, ArrayList<Info> elements) {
        ctx = context;
        objects = elements;
        lInflater = (LayoutInflater) ctx
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    // кол-во элементов
    @Override
    public int getCount() {
        return objects.size();
    }

    // элемент по позиции
    @Override
    public Object getItem(int position) {
        return objects.get(position);
    }

    // id по позиции
    @Override
    public long getItemId(int position) {
        return position;
    }

    // пункт списка
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // используем созданные, но не используемые view
        View view = convertView;
        if (view == null) {
            view = lInflater.inflate(R.layout.item, parent, false);
        }

        Info p = getProduct(position);

        ((ImageView) view.findViewById(R.id.iIcon)).setImageBitmap(p.getIcon());

        ((TextView) view.findViewById(R.id.tTitle)).setText(p.getTitle());
        ((TextView) view.findViewById(R.id.tText)).setText(p.getText());

        String time = null;
        String date = null;

        if (p.getDate() == null && p.getTime() == null){
            Calendar calendar = new GregorianCalendar();
            calendar.setTimeInMillis(p.getDateLong());


            String minute = calendar.get(Calendar.MINUTE) + "";
            if (minute.length() == 1)
                minute = "0" + minute;

            String hour = calendar.get(Calendar.HOUR_OF_DAY) + "";
            if (hour.length() == 1)
                hour = "0" + hour;

            String day = calendar.get(Calendar.DAY_OF_MONTH) + "";
            if (day.length() == 1)
                day = "0" + day;

            String month = calendar.get(Calendar.MONTH) + "";
            if (month.length() == 1)
                month = "0" + month;

            String year = calendar.get(Calendar.YEAR) + "";
            if (year.length() == 1)
                year = "0" + year;

            time = hour + ":" + minute;
            date = day + "." + month + "." + year;
        }else{
            time = p.getTime();
            date = p.getDate();
        }

        ((TextView) view.findViewById(R.id.tTime)).setText(time);
        ((TextView) view.findViewById(R.id.tDate)).setText(date);

        return view;
    }

    // товар по позиции
    Info getProduct(int position) {
        return ((Info) getItem(position));
    }

}