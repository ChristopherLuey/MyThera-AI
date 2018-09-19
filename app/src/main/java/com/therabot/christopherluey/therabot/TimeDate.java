package com.therabot.christopherluey.therabot;

import android.util.Log;

import java.util.Calendar;

/**
 * Created by christopherluey on 9/15/18.
 * Used to convert the Calendar.getInstance into time values.
 * Day of Week, Month, Day, HH:MM, AM/PM, Year
 */

public class TimeDate {

    static String getCreatedAt() {
        Log.d("getCreatedAt", "GetcreatedAt ");
        Calendar currentTime = Calendar.getInstance();
        String Month_Number = String.valueOf(currentTime.get(Calendar.MONTH));

        String Month;
        if(Month_Number.equals("0")){
            Month = "Jan";
        } else if(Month_Number.equals("1")){
            Month = "Feb";
        } else if(Month_Number.equals("2")){
            Month = "Mar";
        } else if(Month_Number.equals("3")){
            Month = "Apr";
        } else if(Month_Number.equals("4")){
            Month = "May";
        } else if(Month_Number.equals("5")){
            Month = "Jun";
        } else if(Month_Number.equals("6")){
            Month = "Jul";
        } else if(Month_Number.equals("7")){
            Month = "Aug";
        } else if(Month_Number.equals("8")){
            Month = "Sep";
        } else if(Month_Number.equals("9")){
            Month = "Oct";
        } else if(Month_Number.equals("10")){
            Month = "Nov";
        } else if(Month_Number.equals("11")){
            Month = "Dec";
        } else{
            Month = "Month not Available";
        }

        String Day = String.valueOf(currentTime.get(Calendar.DAY_OF_MONTH));
        String Year = String.valueOf(currentTime.get(Calendar.YEAR));

        String Day_of_Week_Number = String.valueOf(currentTime.get(Calendar.DAY_OF_WEEK));
        String Day_of_Week;
        if(Day_of_Week_Number.equals("1")){
            Day_of_Week = "Sunday";
        } else if(Day_of_Week_Number.equals("2")) {
            Day_of_Week = "Monday";
        } else if(Day_of_Week_Number.equals("3")) {
            Day_of_Week = "Tuesday";
        } else if(Day_of_Week_Number.equals("4")) {
            Day_of_Week = "Wednesday";
        } else if(Day_of_Week_Number.equals("5")) {
            Day_of_Week = "Thursday";
        } else if(Day_of_Week_Number.equals("6")) {
            Day_of_Week = "Friday";
        } else if(Day_of_Week_Number.equals("7")) {
            Day_of_Week = "Saturday";
        } else {
            Day_of_Week = "Day of Week Not Available";
        }

        String Hour_24 = String.valueOf(currentTime.get(Calendar.HOUR_OF_DAY));
        String Hour;
        String AMPM;
        if(Hour_24.equals("0")){
            Hour = "12:";
            AMPM = "AM";
        } else if(Hour_24.equals("1")){
            Hour = "1:";
            AMPM = "AM";
        } else if(Hour_24.equals("2")){
            Hour = "2:";
            AMPM = "AM";
        } else if(Hour_24.equals("3")){
            Hour = "3:";
            AMPM = "AM";
        } else if(Hour_24.equals("4")){
            Hour = "4:";
            AMPM = "AM";
        } else if(Hour_24.equals("5")){
            Hour = "5:";
            AMPM = "AM";
        } else if(Hour_24.equals("6")){
            Hour = "6:";
            AMPM = "AM";
        } else if(Hour_24.equals("7")){
            Hour = "7:";
            AMPM = "AM";
        } else if(Hour_24.equals("8")){
            Hour = "8:";
            AMPM = "AM";
        } else if(Hour_24.equals("9")){
            Hour = "9:";
            AMPM = "AM";
        } else if(Hour_24.equals("10")){
            Hour = "10:";
            AMPM = "AM";
        } else if(Hour_24.equals("11")){
            Hour = "11:";
            AMPM = "AM";
        } else if(Hour_24.equals("12")){
            Hour = "12:";
            AMPM = "PM";
        } else if(Hour_24.equals("13")){
            Hour = "1:";
            AMPM = "PM";
        } else if(Hour_24.equals("14")){
            Hour = "2:";
            AMPM = "PM";
        } else if(Hour_24.equals("15")){
            Hour = "3:";
            AMPM = "PM";
        } else if(Hour_24.equals("16")){
            Hour = "4:";
            AMPM = "PM";
        } else if(Hour_24.equals("17")){
            Hour = "5:";
            AMPM = "PM";
        } else if(Hour_24.equals("18")){
            Hour = "6:";
            AMPM = "PM";
        } else if(Hour_24.equals("19")){
            Hour = "7:";
            AMPM = "PM";
        } else if(Hour_24.equals("20")){
            Hour = "8:";
            AMPM = "PM";
        } else if(Hour_24.equals("21")){
            Hour = "9:";
            AMPM = "PM";
        } else if(Hour_24.equals("22")){
            Hour = "10:";
            AMPM = "PM";
        } else if(Hour_24.equals("23")){
            Hour = "11:";
            AMPM = "PM";
        } else {
            Hour = "Hour Not Available";
            AMPM = "Time Not Available";
        }

        String minutenumber = String.valueOf(currentTime.get(Calendar.MINUTE));
        String Minute;
        if(minutenumber.equals("0")){
            Minute = "00";
        } else if(minutenumber.equals("1")){
            Minute = "01";
        } else if(minutenumber.equals("2")){
            Minute = "02";
        } else if(minutenumber.equals("3")){
            Minute = "03";
        } else if(minutenumber.equals("4")){
            Minute = "04";
        } else if(minutenumber.equals("5")){
            Minute = "05";
        } else if(minutenumber.equals("6")){
            Minute = "06";
        } else if(minutenumber.equals("7")){
            Minute = "07";
        } else if(minutenumber.equals("8")){
            Minute = "08";
        } else if(minutenumber.equals("9")){
            Minute = "09";
        } else{
            try {
                Minute = minutenumber;
            } catch (Exception e){
                Minute = "Minutes not available";
            }
        }

        String Date = Day_of_Week + " " + Month + " " + Day + ", " + Hour + Minute + " " + AMPM + ", " + Year;

        return Date;
    }
}
