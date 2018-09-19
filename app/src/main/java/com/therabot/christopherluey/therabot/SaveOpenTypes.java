package com.therabot.christopherluey.therabot;

/**
 * Created by christopherluey on 9/18/18.
 */

public class SaveOpenTypes {
    public static final int SAVE = 0;
    public static final int OPEN = 1;

    public int type;
    public int data;

    public SaveOpenTypes(int type, int data){
        this.type=type;
        this.data=data;
    }
}
