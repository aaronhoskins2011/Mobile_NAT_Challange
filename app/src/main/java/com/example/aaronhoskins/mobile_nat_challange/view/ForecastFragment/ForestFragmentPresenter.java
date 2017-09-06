package com.example.aaronhoskins.mobile_nat_challange.view.ForecastFragment;

import android.util.Log;

import com.example.aaronhoskins.mobile_nat_challange.model.ForecastDataDisplayLists;
import com.example.aaronhoskins.mobile_nat_challange.model.forecast.ForecastProfile;
import com.example.aaronhoskins.mobile_nat_challange.model.forecast.HourlyForecast;

import java.util.ArrayList;

import static com.example.aaronhoskins.mobile_nat_challange.data.local.Constants.TAG_FORCAST_FRAGMENT;

public class ForestFragmentPresenter {
    ForecastDataDisplayLists forecastDataDisplayLists = new ForecastDataDisplayLists();
    ArrayList<Double> temps = new ArrayList<>();
       //                          //
      //   initKeyValueLists      //
     //--------------------------//
    public ForecastDataDisplayLists initKeyValueLists(ForecastProfile forecastProfile){
        String day = null;

        for(HourlyForecast forecast: forecastProfile.getHourlyForecast()){
            if(day == null){
                day = forecast.getFCTTIME().getWeekdayName();
            }// end if
            if(day.equals(forecast.getFCTTIME().getWeekdayName())) {
                temps.add(Double.parseDouble(forecast.getTemp().getEnglish()));
            } else {
                double curHiTemp = -1000.00; //Hold the max temp found per day
                double curLoTemp = 1000.00;  //Hold the min temp found per day
                for(Double d : temps){
                    if(d < curLoTemp){curLoTemp = d;}//end if comparison for low temps
                    if(d > curHiTemp){curHiTemp = d;}//end if comparison fo High temps
                }//end if/else
                Log.d(TAG_FORCAST_FRAGMENT, "initKeyValueLists: Day: " + day + " Hi Temp: " +  curHiTemp + " Lo Temp: " + curLoTemp);
                //add to prospective array list
                forecastDataDisplayLists.getDaysList().add(day);
                forecastDataDisplayLists.getHiTemps().add(curHiTemp);
                forecastDataDisplayLists.getLoTemps().add(curLoTemp);
                //setup for next iteration
                day = forecast.getFCTTIME().getWeekdayName();
                temps.clear();

            }
        }
        return forecastDataDisplayLists;
    }
}
