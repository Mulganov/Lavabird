package com.mulganov.test_task.middle.model.tools;

import android.content.Context;

import com.google.gson.Gson;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class JSONHelper {

    public static Config importFromJSON(String file) {
        InputStreamReader streamReader = null;
        FileInputStream fileInputStream = null;
        try{
            fileInputStream = new FileInputStream(file);
            streamReader = new InputStreamReader(fileInputStream);
            Gson gson = new Gson();
            Config elements = gson.fromJson(streamReader, Config.class);
            return  elements;
        }
        catch (IOException ex){
            ex.printStackTrace();
        }
        finally {
            if (streamReader != null) {
                try {
                    streamReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (fileInputStream != null) {
                try {
                    fileInputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return null;
    }

    public static void exportFromJSON(Object obj, File dir) {

        Gson gson = new Gson();

        String text = "";
        text = gson.toJson(obj);

        System.out.println("Text: " + text + "\n");

        try {
            DataOutputStream file = new DataOutputStream(
                    new FileOutputStream(dir + ""));
            file.write(text.getBytes());
            file.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        File file = new File(dir + "");

        InputStream initialStream = null;
        try {
            initialStream = new FileInputStream(
                    file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }
}