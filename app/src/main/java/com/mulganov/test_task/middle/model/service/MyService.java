package com.mulganov.test_task.middle.model.service;

import android.app.ActivityManager;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.IBinder;

import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.mulganov.test_task.middle.R;


public class MyService extends Service {

    public static boolean isRunning(Context context) {
        Class<?> serviceClass = MyService.class;
        ActivityManager manager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        for (ActivityManager.RunningServiceInfo service : manager.getRunningServices(Integer.MAX_VALUE)) {
            if (serviceClass.getName().equals(service.service.getClassName())) {
                return true;
            }
        }
        return false;
    }

    private Thread thread = new Thread(new Runnable() {
        @Override
        public void run() {
            while (true){
            }
        }
    });

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        try {
            thread.start();
        }catch (IllegalThreadStateException ex){

        }

        return Service.START_STICKY;
    }


    public MyService() {
    }

    @Override
    public void onCreate(){
        super.onCreate();

        if (Build.VERSION.SDK_INT >= 26) {
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID,
                    "Channel human readable title",
                    NotificationManager.IMPORTANCE_DEFAULT);

            ((NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE)).createNotificationChannel(channel);

            Notification notification = new NotificationCompat.Builder(this, CHANNEL_ID)
                    .setContentTitle("Приложения работает в фоне")
                    .setSmallIcon(R.drawable.ic_icon)
                    .setContentText("text")
                    .build();

            startForeground(2, notification);
        }

        try {
            thread.start();
        }catch (IllegalThreadStateException ex){

        }
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    private static String CHANNEL_ID = "Lavabird";

    public static void createNotification(int n, String title,String text, Context context){
        createNotificationChannel(CHANNEL_ID, context);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_icon)
                .setContentTitle(title)
                .setContentText(text)
                .setAutoCancel(true)
//                .setContentIntent(PendingIntent.getActivity(getApplicationContext(), 0, new Intent(getApplicationContext(), LoginActivity.class), PendingIntent.FLAG_UPDATE_CURRENT))
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);
        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(context);

        notificationManager.notify(n, builder.build());
    }

    private static void createNotificationChannel(String id, Context context) {
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = CHANNEL_ID;
            String description = "description";
            NotificationChannel channel = new NotificationChannel(id, name, NotificationManager.IMPORTANCE_DEFAULT);
            channel.enableLights(true);
            channel.setDescription(description);
            // Register the channel with the system; you can't change the importance
            // or other notification behaviors after this
            NotificationManager notificationManager = context.getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }
}
