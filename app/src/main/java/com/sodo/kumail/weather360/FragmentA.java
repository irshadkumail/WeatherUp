package com.sodo.kumail.weather360;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by kumail on 5/24/2016.
 */
public class FragmentA extends Fragment {

    TextView humidity,pressure,speed,degrees,sunrise,sunset;
    TextView main_temp,min_temp,max_temp,location,description;
    ImageView icon;
    TodayWeather todayWeather;



    public View onCreateView(LayoutInflater layoutInflater,ViewGroup parent,Bundle bundle)
    {
        View view= layoutInflater.inflate(R.layout.fragment_a,parent,false);
        init(view);
        getJson();


        return view;
    }
    public void init(View view)
    {
        humidity= (TextView) view.findViewById(R.id.conditions_humidity_value);
        pressure= (TextView) view.findViewById(R.id.conditions_pressure_value);
        speed=(TextView)view.findViewById(R.id.wind_speed_value);
        degrees=(TextView)view.findViewById(R.id.wind_deg_value);
        sunrise= (TextView) view.findViewById(R.id.sunrise_value);
        sunset= (TextView) view.findViewById(R.id.sunset_value);
        description=(TextView)view.findViewById(R.id.today_description);
        main_temp=(TextView)view.findViewById(R.id.main_temp);
        min_temp=(TextView)view.findViewById(R.id.min_temp_value);
        max_temp=(TextView)view.findViewById(R.id.max_text_value);
        location=(TextView)view.findViewById(R.id.city_text);
        icon=(ImageView)view.findViewById(R.id.today_icon);

    }
    public void getJson()
    {
        Log.d("Irshad","Yaha to aagaya-getJson");
        RequestQueue requestQueue=MyQueueSingleton.getRequestQueue();

        JsonObjectRequest jsonObjectRequest= new JsonObjectRequest(Request.Method.GET,"http://api.openweathermap.org/data/2.5/weather?q="+StartActivity.selected_city+"&appid=235bef5a99d6bc6193525182c409602c",null,new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject jsonObject) {
                parseJson(jsonObject);

            }
        },new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                Log.d("Irshad","Error hgya be");
            }
        });
        requestQueue.add(jsonObjectRequest);
    }
    public void parseJson(JSONObject jsonObject)
    {
        Log.d("Irshad","Yaha to aagaya parseJson");
        try {
            String description_value=jsonObject.getJSONArray("weather").getJSONObject(0).getString("main")+jsonObject.getJSONArray("weather").getJSONObject(0).getString("description");
            String temp_value=jsonObject.getJSONObject("main").getString("temp");
            String temp_max_value=jsonObject.getJSONObject("main").getString("temp_max");
            String temp_min_value=jsonObject.getJSONObject("main").getString("temp_min");
            String speed_value=jsonObject.getJSONObject("wind").getString("speed");
            String pressure_value=jsonObject.getJSONObject("main").getString("pressure");
            String humidity_value=jsonObject.getJSONObject("main").getString("humidity");
            String icon_value=jsonObject.getJSONArray("weather").getJSONObject(0).getString("icon");
            String degree_value=jsonObject.getJSONObject("clouds").getString("all");
            String sunset_value=jsonObject.getJSONObject("sys").getString("sunset");
            String sunrise_value=jsonObject.getJSONObject("sys").getString("sunrise");
            String location=jsonObject.getString("name");

            todayWeather= new TodayWeather(description_value,temp_value,temp_max_value,temp_min_value,speed_value,pressure_value,humidity_value,icon_value,degree_value,sunset_value,sunrise_value,location);
            setValues();

        } catch (JSONException e) {
            e.printStackTrace();
        }


    }
    public void setValues()
    {
        Log.d("Irshad","Yaha to aagaya");
        humidity.setText(todayWeather.humidity_value);
        pressure.setText(todayWeather.pressure_value);
        description.setText(todayWeather.description_value);
        main_temp.setText(todayWeather.temp_value);
        min_temp.setText(todayWeather.temp_min_value);
        max_temp.setText(todayWeather.temp_max_value);
        speed.setText(todayWeather.speed_value);
        //degrees.setText(todayWeather.degree_value);
        location.setText(todayWeather.location_value);
        sunset.setText(todayWeather.sunset_value);
        sunrise.setText(todayWeather.sunrise_value);
        ImageLoader imageLoader=MyQueueSingleton.getImageLoader();
        imageLoader.get("http://openweathermap.org/img/w/"+todayWeather.icon_value+".png",new ImageLoader.ImageListener() {
            @Override
            public void onResponse(ImageLoader.ImageContainer imageContainer, boolean b) {
                icon.setImageBitmap(imageContainer.getBitmap());
            }

            @Override
            public void onErrorResponse(VolleyError volleyError) {

            }
        });


    }
}
