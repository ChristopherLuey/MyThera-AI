package com.therabot.christopherluey.therabot;

/**
 * Created by christopherluey on 9/18/18.
 */

import java.io.Serializable;

public class message implements Serializable {
    String id, message;


    public message() {
    }

    public message(String id, String message, String createdAt) {
        this.id = id;
        this.message = message;


    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }


}