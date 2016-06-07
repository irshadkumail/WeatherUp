package com.sodo.kumail.weather360;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by kumail on 5/25/2016.
 */
public class FragmentB extends Fragment {

    RecyclerView recyclerView;
    MyHourlyAdapter myHourlyAdapter;
    ArrayList<HourlyWeather> arrayList;


    public View onCreateView(LayoutInflater layoutInflater,ViewGroup parent,Bundle bundle)
    {
        View view= layoutInflater.inflate(R.layout.fragment_b,parent,false);

        recyclerView= (RecyclerView) view.findViewById(R.id.hourly_recycler);


        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        getJson();

        return view;
    }
    public void getJson()
    {
        RequestQueue requestQueue=MyQueueSingleton.getRequestQueue();

        JsonObjectRequest jsonObjectRequest= new JsonObjectRequest(Request.Method.GET,"http://api.openweathermap.org/data/2.5/forecast?q="+StartActivity.selected_city+"&mode=json&appid=235bef5a99d6bc6193525182c409602c",null,new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject jsonObject) {
                recyclerView.setAdapter(new MyHourlyAdapter(getActivity(),parseJson(jsonObject)));

            }
        },new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {

            }
        });

        requestQueue.add(jsonObjectRequest);


    }
    public ArrayList<HourlyWeather> parseJson(JSONObject jsonObject)
    {
        ArrayList<HourlyWeather> weathers= new ArrayList<>();
        try {
            JSONArray jsonArray=jsonObject.getJSONArray("list");

            for(int i=0;i<10;i++)
            {
                JSONObject currentJsonObject=jsonArray.getJSONObject(i);

                int main_temp=currentJsonObject.getJSONObject("main").getInt("temp");
                int max_temp=currentJsonObject.getJSONObject("main").getInt("temp_max");
                int min_temp=currentJsonObject.getJSONObject("main").getInt("temp_min");
                String icon=currentJsonObject.getJSONArray("weather").getJSONObject(0).getString("icon");
                String main_time=currentJsonObject.getString("dt_txt");
                String description=currentJsonObject.getJSONArray("weather").getJSONObject(0).getString("main")+" "+currentJsonObject.getJSONArray("weather").getJSONObject(0).getString("description");

                HourlyWeather hourlyWeather= new HourlyWeather(main_temp,max_temp,min_temp,main_time,icon,description);
                weathers.add(hourlyWeather);

            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return weathers;
    }


    class MyHourlyAdapter extends RecyclerView.Adapter<HourlyViewHolder>
    {
        Context context;
        LayoutInflater layoutInflater;
        HourlyViewHolder hourlyViewHolder;
        ArrayList<HourlyWeather> arrayList;

        public MyHourlyAdapter(Context context,ArrayList<HourlyWeather> arrayList)
        {
            this.context=context;
            this.arrayList=arrayList;
            layoutInflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }

        public HourlyViewHolder onCreateViewHolder(ViewGroup viewGroup,int pos)
        {
            View view=layoutInflater.inflate(R.layout.hourly_row,viewGroup,false);
            hourlyViewHolder= new HourlyViewHolder(view);

            return hourlyViewHolder;
        }
        public void onBindViewHolder(HourlyViewHolder holder,int pos)
        {
           //hourlyViewHolder.max_temp.setText(arrayList.get(pos).max_temp+"");
           //hourlyViewHolder.min_temp.setText(arrayList.get(pos).min_temp+"");
           hourlyViewHolder.main_temp.setText(arrayList.get(pos).main_temp+"");
           hourlyViewHolder.time.setText(arrayList.get(pos).main_time);
            ImageLoader imageLoader=MyQueueSingleton.getImageLoader();
            hourlyViewHolder.description_row.setText(arrayList.get(pos).description);

            imageLoader.get("http://openweathermap.org/img/w/"+arrayList.get(pos).image+".png",new ImageLoader.ImageListener() {
                @Override
                public void onResponse(ImageLoader.ImageContainer imageContainer, boolean b) {
                    hourlyViewHolder.imageView.setImageBitmap(imageContainer.getBitmap());


                }

                @Override
                public void onErrorResponse(VolleyError volleyError) {

                    Log.d("Irshad","Some error while retriving icons");
                }
            });





        }
        public int getItemCount()
        {

            return 10;
        }
    }
}
