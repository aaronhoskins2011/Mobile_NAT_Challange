package com.example.aaronhoskins.mobile_nat_challange.view.ForecastFragment;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.aaronhoskins.mobile_nat_challange.R;

import java.util.ArrayList;

/**
 * Created by cathye.johnson on 9/2/2017.
 */

public class ForecastRecyleViewAdaptor extends RecyclerView.Adapter<ForecastRecyleViewAdaptor.ViewHolder>{
    ArrayList<Double> hiTempList = new ArrayList<Double>();
    ArrayList<Double> loTempList = new ArrayList<Double>();
    ArrayList<String> dayOfForecast = new ArrayList<String>();
    Context context;

    public ForecastRecyleViewAdaptor(ArrayList<Double> hiTempList, ArrayList<Double> loTempList, ArrayList<String> dayOfForecast) {
        this.hiTempList = hiTempList;
        this.loTempList = loTempList;
        this.dayOfForecast = dayOfForecast;
    }

    @Override
    public ForecastRecyleViewAdaptor.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.forecast_item,parent, false);
        context = parent.getContext();
        return new ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(ForecastRecyleViewAdaptor.ViewHolder holder, int position) {
        holder.tvDay.setText(dayOfForecast.get(position));
        holder.tvHiTemp.setText(String.valueOf(hiTempList.get(position)));
        holder.tvLoTemp.setText(String.valueOf(loTempList.get(position)));
        Glide.with(context).load("http://icons.wxug.com/i/c/k/nt_partlycloudy.gif").into(holder.imgWeatherPic);
    }
    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvDay;
        TextView tvHiTemp;
        TextView tvLoTemp;
        ImageView imgWeatherPic;

        public ViewHolder(View itemView) {
            super(itemView);
            tvDay = (TextView)itemView.findViewById(R.id.tvDay);
            tvHiTemp = (TextView)itemView.findViewById(R.id.tvHighTemp);
            tvLoTemp = (TextView)itemView.findViewById(R.id.tvLowTemp);
            imgWeatherPic = (ImageView)itemView.findViewById(R.id.imgWeatherIcon);

        }
    }

    @Override
    public int getItemCount() {
        return hiTempList.size();
    }
}
