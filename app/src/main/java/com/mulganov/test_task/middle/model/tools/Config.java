package com.mulganov.test_task.middle.model.tools;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class Config {
    public String title;
    public String text;

    public int  minute;
    public int  hour;
    public int  day;
    public int  month;
    public int  year;

    public long dateLong;

    public String icon;

    public Config(){

    }

    public Config(Info info, Context context){
        Calendar calendar = new GregorianCalendar();

        calendar.setTimeInMillis(info.getDateLong());

        minute = calendar.get(Calendar.MINUTE);

        hour = calendar.get(Calendar.HOUR_OF_DAY);

        day = calendar.get(Calendar.DAY_OF_MONTH);

        month = calendar.get(Calendar.MONTH);

        year = calendar.get(Calendar.YEAR);

        Bitmap bitmap = info.getIcon();

        dateLong = info.getDateLong();

        title = info.getTitle();
        text = info.getText();

        String file = year + "_" + month + "_" + day + "_" + hour + "_" + minute + "_"+ title + "_" + text;

        String filename = context.getFilesDir() + "/" + file + ".png";

        icon = filename;

        try (FileOutputStream out = new FileOutputStream(filename)) {
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, out); // bmp is your Bitmap instance
            // PNG is a lossless format, the compression factor (100) is ignored
        } catch (IOException e) {
            e.printStackTrace();
        }

        JSONHelper.exportFromJSON(this, new File(context.getFilesDir() + "/" + file + ".json"));
    }

}
