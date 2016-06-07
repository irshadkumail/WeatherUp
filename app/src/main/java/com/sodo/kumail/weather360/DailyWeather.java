package com.sodo.kumail.weather360;

import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import java.util.TimeZone;

/**
 * Created by kumail on 5/27/2016.
 */
public class DailyWeather {

    String main_temp_value;
    String icon;
    String description_value;
    long date_value;

    DailyWeather(String main_temp_value,String icon,String description_value,long date_value)
    {

        this.main_temp_value = main_temp_value;
        this.icon = icon;
        this.description_value = description_value;
        this.date_value = date_value;
    }
    private String getDate(long time) {
       return null;
    }

}