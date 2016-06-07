package com.sodo.kumail.weather360;

/**
 * Created by kumail on 5/25/2016.
 */
public class HourlyWeather {

    String main_temp;
    String max_temp;
    String min_temp;
    String main_time;
    String image;
    String description;

    public HourlyWeather(int main_temp,int max_temp,int min_temp,String main_time, String image,String description)
    {
        this.main_temp=getTemp(main_temp);
        this.max_temp=getTemp(max_temp);
        this.min_temp=getTemp(min_temp);
        this.description=description;

        this.main_time=getTime(main_time);
        this.image=image;

    }

    public String getTemp(int tempV)
    {
        //int tempV=Integer.parseInt(temp);

        return (tempV-273)+""+ (char) 0x00B0;

    }
    public String getTime(String time)
    {
        int i;
        String res=null;
        for(i=0;i<time.length();i++)
        {
            if(time.charAt(i)==' ')
            {
              break;
            }
        }
        res=time.charAt(i+1)+time.charAt(i+2)+":00";

      return res;
    }
}
