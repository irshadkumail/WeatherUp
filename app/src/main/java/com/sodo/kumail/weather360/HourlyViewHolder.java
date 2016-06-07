package com.sodo.kumail.weather360;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by kumail on 5/25/2016.
 */
public class HourlyViewHolder extends RecyclerView.ViewHolder {

    TextView main_temp,min_temp,max_temp,time;
    ImageView imageView;
    TextView description_row;

    public HourlyViewHolder(View view)
    {
        super(view);
        main_temp= (TextView) view.findViewById(R.id.row_main_temp);
       // min_temp=(TextView)view.findViewById(R.id.row_min_temp);
       // max_temp=(TextView)view.findViewById(R.id.row_max_temp);
        description_row=(TextView)view.findViewById(R.id.row_description);
        time= (TextView) view.findViewById(R.id.row_time);
        imageView= (ImageView) view.findViewById(R.id.row_image);
    }
}
