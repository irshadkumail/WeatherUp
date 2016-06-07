package com.sodo.kumail.weather360;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by kumail on 5/25/2016.
 */
public class FragmentC extends Fragment {

    RecyclerView recyclerView
    public View onCreateView(LayoutInflater layoutInflater,ViewGroup parent,Bundle bundle)
    {
        View view= layoutInflater.inflate(R.layout.fragment_c,parent,false);

        recyclerView= (RecyclerView) view.findViewById(R.id.daily_recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        return view;
    }
    public void getJson()
    {
        RequestQueue requestQueue=MyQueueSingleton.getRequestQueue();

        JsonObjectRequest jsonObjectRequest= new JsonObjectRequest(Request.Method.GET,"",null,new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject jsonObject) {

            }
        },new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {

            }
        });

    }
    public ArrayList<DailyWeather> parseJson(JSONObject jsonObject)
    {
        ArrayList<DailyWeather> arrayList=new ArrayList<>();
        try {
            JSONArray array= jsonObject.getJSONArray("list");

            for(int i=0;i<array.length();i++)
            {
                JSONObject currentJson=array.getJSONObject(i);

                String temp=currentJson.getJSONObject("temp").getString("day");
                String description=currentJson.getJSONArray("weather").getJSONObject(0).getString("description");
                String icon=currentJson.getJSONArray("weather").getJSONObject(0).getString("icon");
                long date=currentJson.getLong("dt");

                arrayList.add(new DailyWeather(temp,icon,description,date));


            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
}
