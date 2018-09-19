package com.therabot.christopherluey.therabot;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import static com.therabot.christopherluey.therabot.MainActivity.listMessage;

/**
 * Created by christopherluey on 9/15/18.
 * Class that allows user to write files to device and read files from device
 */

public class ReadWrite {

    //Save current transcript to external SD card
    static public void write(File file, Context context) {
        FileOutputStream stream = null;
        try {
            stream = new FileOutputStream(file);
        } catch (Exception e) {
            Toast toast = Toast.makeText(context, "Could Not Write File.", Toast.LENGTH_SHORT);
            toast.show();
        }
        try {
            String var = "#";
            if (MainActivity.listMessage.size() > 1) {
                for (int i = 1; i <= listMessage.size(); i++) {
                    stream.write(String.valueOf(listMessage.get(i-1)).getBytes());
                    stream.write(var.getBytes());
                }
            } else{
                stream.write(String.valueOf(listMessage.get(0)).getBytes());
            }
        } catch (Exception e) {
            Toast toast = Toast.makeText(context, "Could Not Write File.", Toast.LENGTH_SHORT);
            toast.show();
        } finally {
            try {
                stream.close();
            } catch (Exception e) {
                Toast toast = Toast.makeText(context, "Could Not Write File.", Toast.LENGTH_SHORT);
                toast.show();
            }
        }
    }

    //Read the saved file and return it's contents
    static public String read (File file, Context context){
        int length = (int) file.length();

        byte[] bytes = new byte[length];

        FileInputStream in = null;
        try {
            in = new FileInputStream(file);
        } catch (FileNotFoundException e) {
            Toast toast = Toast.makeText(context, "Could Not Read File.", Toast.LENGTH_SHORT);
            toast.show();

        }
        try {
            in.read(bytes);
        } catch (Exception e) {
            Toast toast = Toast.makeText(context, "Could Not Read File.", Toast.LENGTH_SHORT);
            toast.show();
        } finally {
            try {
                in.close();
            } catch (Exception e) {
                Toast toast = Toast.makeText(context, "Could Not Read File.", Toast.LENGTH_SHORT);
                toast.show();
            }
        }
        String contents = new String(bytes);
        return contents;
    }
}
