package com.mulganov.test_task.middle.model.tools;

import android.content.ComponentName;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.provider.Settings;
import android.text.TextUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

public class Utils {

    public static boolean isNotificationServiceEnabled(Context context){
        String pkgName = context.getPackageName();
        final String flat = Settings.Secure.getString(context.getContentResolver(),
                "enabled_notification_listeners");
        if (!TextUtils.isEmpty(flat)) {
            final String[] names = flat.split(":");
            for (int i = 0; i < names.length; i++) {
                final ComponentName cn = ComponentName.unflattenFromString(names[i]);
                if (cn != null) {
                    if (TextUtils.equals(pkgName, cn.getPackageName())) {
                        return true;
                    }
                }
            }
        }

        return false;
    }

    public static ArrayList<Info> getAllInfo(Context context){
        ArrayList<Info> a = new ArrayList<>();

        String filename = context.getFilesDir() + "/";

        for (File file: new File(filename).listFiles()){
            if (file.getName().contains(".json")){
                System.out.println(file + "");
                Config config = JSONHelper.importFromJSON(file + "");
                Bitmap bitmap = loadBitmap(new File(config.icon));

                Info info = new Info(config, bitmap);

                a.add(info);
            }
        }

        return a;
    }

   private static Bitmap loadBitmap(File f){
        try { // Decode image size
            BitmapFactory.Options o = new BitmapFactory.Options();
            o.inJustDecodeBounds = true;
            BitmapFactory.decodeStream(new FileInputStream(f), null, o);
            // The new size we want to scale to
            int REQUIRED_SIZE = 70;
            // Find the correct scale value. It should be the power of 2.
            int scale = 1;
            while (o.outWidth / scale / 2 >= REQUIRED_SIZE &&
                    o.outHeight / scale / 2 >= REQUIRED_SIZE
            ) {
                scale *= 2;
            }
            // Decode with inSampleSize
            BitmapFactory.Options o2 = new BitmapFactory.Options();
            o2.inSampleSize = scale;
            return BitmapFactory.decodeStream(new FileInputStream(f), null, o2);
        } catch (FileNotFoundException ex) {
        }

        return null;
    }

    public static ArrayList<Info> getParseArray(ArrayList<Info> list){
        list = (ArrayList<Info>) list.clone();
        ArrayList<Info> a = new ArrayList<>();

        long[] aa = new long[list.size()];

        for (int i = 0; i < list.size(); i++){
            aa[i] = list.get(i).getDateLong();
        }

        Arrays.sort(aa);

        for (int j = 0; j < aa.length; j++){
            for (Info i: list) {
                if (i.getDateLong() == aa[j]) {
                    a.add(i);
                }
            }
        }

        list = new ArrayList<>();

        for (int i = a.size()-1; i >= 0; i--){
            list.add(a.get(i));
        }

        return list;
    }

}
