package com.sodo.kumail.weather360;

import android.graphics.Bitmap;
import android.support.v4.util.LruCache;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;

/**
 * Created by kumail on 5/25/2016.
 */
public class MyQueueSingleton {

    static RequestQueue requestQueue;

    static ImageLoader imageLoader;

    private  MyQueueSingleton()
    {
        if(requestQueue==null)
        {
            requestQueue= Volley.newRequestQueue(MyApplication.getMyApplication());

        }
        if(imageLoader==null)
        {
            imageLoader=new ImageLoader(requestQueue,new ImageLoader.ImageCache() {
                LruCache<String,Bitmap> lruCache= new LruCache<>(20);
                @Override
                public Bitmap getBitmap(String s) {
                    return lruCache.get(s);
                }

                @Override
                public void putBitmap(String s, Bitmap bitmap) {

                    lruCache.put(s,bitmap);
                }
            });
        }
    }
    public static RequestQueue getRequestQueue()
    {
        MyQueueSingleton myQueueSingleton=new MyQueueSingleton();
        return requestQueue;
    }
    public static ImageLoader getImageLoader()
    {
        MyQueueSingleton myQueueSingleton=new MyQueueSingleton();
        return imageLoader;

    }

}
