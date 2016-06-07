package com.sodo.kumail.weather360;

import android.app.Application;

/**
 * Created by kumail on 5/25/2016.
 */
public class MyApplication extends Application {

    public static MyApplication myApplication;

    public void onCreate()
    {
        super.onCreate();
        myApplication=this;

    }
    public static MyApplication getMyApplication()
    {
        return myApplication;
    }

}
