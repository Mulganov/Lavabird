package com.mulganov.test_task.middle.model.service;

import android.content.pm.ApplicationInfo;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;

import android.service.notification.NotificationListenerService;
import android.service.notification.StatusBarNotification;
import android.util.Log;

import com.mulganov.test_task.middle.MainActivity;
import com.mulganov.test_task.middle.model.tools.Config;
import com.mulganov.test_task.middle.model.tools.Info;

public class NLService extends NotificationListenerService {

    private String TAG = this.getClass().getSimpleName();

    @Override
    public void onCreate() {
        super.onCreate();

        System.out.println("Create Service");

        Log.i(TAG, "onCreate");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onNotificationPosted(StatusBarNotification sbn) {

        String title = sbn.getNotification().extras.getString("android.title");
        String text = sbn.getNotification().extras.getString("android.text");

        ApplicationInfo appInfo = (ApplicationInfo) sbn.getNotification().extras.get("android.appInfo");

        long date = sbn.getPostTime();

        Drawable icon = appInfo.loadIcon(getPackageManager());

        Info info = new Info();
        info.setDateLong(date);
        info.setText(text);
        info.setTitle(title);
        info.setIcon(((BitmapDrawable)icon).getBitmap());

        MainActivity.activity.addInfo(info);

        new Config(info, getApplicationContext());

//        for (String key: sbn.getNotification().extras.keySet()){
//            System.out.println("key: " + key + " value: " + sbn.getNotification().extras.get(key));
//        }

    }

    @Override
    public void onNotificationRemoved(StatusBarNotification sbn) {
    }

}