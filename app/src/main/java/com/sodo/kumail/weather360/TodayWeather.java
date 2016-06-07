package com.sodo.kumail.weather360;

/**
 * Created by kumail on 5/26/2016.
 */
public class TodayWeather {
    String description_value;
    String temp_value;
    String temp_max_value;
    String temp_min_value;
    String speed_value;
    String pressure_value;
    String humidity_value;
    String icon_value;
    String degree_value;
    String sunset_value;
    String sunrise_value;
    String location_value;

    public  TodayWeather(String description_value,String temp_value,String temp_max_value,String temp_min_value,String speed_value,String pressure_value,String humidity_value,String icon_value,String degree_value,String sunset_value,String sunrise_value,String location_value)
    {

        this.description_value = description_value;
        this.temp_value = getTemp(temp_value);
        this.temp_max_value =getTemp(temp_max_value);
        this.temp_min_value = getTemp(temp_min_value);
        this.speed_value = speed_value;
        this.pressure_value = pressure_value;
        this.humidity_value = humidity_value;
        this.icon_value = icon_value;
        this.degree_value = degree_value;
        this.sunset_value = sunset_value;
        this.sunrise_value = sunrise_value;
        this.location_value=location_value;
    }

    public String getTemp(String temp)
    {
        int tempV= (int) Double.parseDouble(temp);

        return (tempV-273)+""+ (char) 0x00B0;

    }
}
