package com.therabot.christopherluey.therabot;

import android.util.Log;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import static com.therabot.christopherluey.therabot.MainActivity.listMessage;

/**
 * Created by christopherluey on 9/15/18.
 */

public class ReadWrite {

    //Save current transcript to external SD card
    static public void write(File file) {
        Log.d("savefile", "onClick: ");
        FileOutputStream stream = null;
        try {
            stream = new FileOutputStream(file);
        } catch (Exception e) {
            Log.d("create new file fail", "onClick: ");
        }
        try {
            String var = ",";
            for (int i = 1; i <= listMessage.size(); i++){
                stream.write(String.valueOf(listMessage.get(i-1)).getBytes());
                stream.write(var.getBytes());
            }
        } catch (Exception e) {
            Log.d("streamwritefail", "onClick: ");
        } finally {
            try {
                stream.close();
            } catch (Exception e) {
                Log.d("stream.closefail", "onClick: ");
            }
        }
        Log.d("complete", "savefile: ");
    }

    //Read the saved file and return it's contents
    static public String read (File file){
        int length = (int) file.length();

        byte[] bytes = new byte[length];

        FileInputStream in = null;
        try {
            in = new FileInputStream(file);
        } catch (FileNotFoundException e) {
            Log.d("newfileinputstreamfail", "onClick: ");
        }
        try {
            in.read(bytes);
            Log.d("bytes", String.valueOf(bytes));
        } catch (Exception e) {
            Log.d("inreadfail", "onClick: ");
        } finally {
            try {
                in.close();
            } catch (Exception e) {
                Log.d("inclosefail", "onClick: ");
            }
        }
        String contents = new String(bytes);
        Log.d("Fileread", "read: ");
        return contents;
    }
}
