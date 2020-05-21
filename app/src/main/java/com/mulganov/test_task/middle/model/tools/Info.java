package com.mulganov.test_task.middle.model.tools;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;

import java.util.Calendar;

public class Info {
    private String title;
    private String text;
    private Bitmap icon;
    private long dateLong;
    private String time;
    private String date;

    private int hour;
    private int day;
    private int month;

    public Info(){}

    public Info(Config config, Bitmap bitmap) {
        String minute = config.minute + "";
        if (minute.length() == 1)
            minute = "0" + minute;

        String hour = config.hour + "";
        if (hour.length() == 1)
            hour = "0" + hour;

        String day = config.day + "";
        if (day.length() == 1)
            day = "0" + day;

        String month = config.month + "";
        if (month.length() == 1)
            month = "0" + month;

        String year = config.year + "";
        if (year.length() == 1)
            year = "0" + year;

        time = hour + ":" + minute;
        date = day + "." + month + "." + year;

        icon = bitmap;

        title = config.title;
        text = config.text;

        this.hour = config.hour;
        this.day = config.day;
        this.month = config.month;

        this.dateLong = config.dateLong;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Bitmap getIcon() {
        return icon;
    }

    public void setIcon(Bitmap icon) {
        this.icon = icon;
    }

    public long getDateLong() {
        return dateLong;
    }

    public void setDateLong(long date) {
        this.dateLong = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getHour() {
        return hour;
    }

    public void setHour(int hour) {
        this.hour = hour;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }
}
