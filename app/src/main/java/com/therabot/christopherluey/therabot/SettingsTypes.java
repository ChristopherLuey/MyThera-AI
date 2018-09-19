package com.therabot.christopherluey.therabot;

/**
 * Created by christopherluey on 9/16/18.
 */

public class SettingsTypes {
    public static final int HIDE_MESSAGE_SENT = 0;
    public static final int HIDE_BOT_ICON = 1;
    public static final int CHANGE_TEXT = 2;
    public static final int ABOUT = 3;
    public static final int FEEDBACK = 4;
    public static final int CLEAR = 5;
    public static final int SAVEOPEN = 6;
    //public static final int RESET = 7;
    public static final int TEXT = 8;

    public int type;
    public int data;

    public SettingsTypes(int type, int data){
        this.type=type;
        this.data=data;
    }
}
