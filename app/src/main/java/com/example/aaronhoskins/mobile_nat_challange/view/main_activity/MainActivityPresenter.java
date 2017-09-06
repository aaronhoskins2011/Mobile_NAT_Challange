package com.example.aaronhoskins.mobile_nat_challange.view.main_activity;

import com.example.aaronhoskins.mobile_nat_challange.data.remote.WeatherDataHandler;
import com.example.aaronhoskins.mobile_nat_challange.model.ThreadObjectReturns;
import com.example.aaronhoskins.mobile_nat_challange.model.conditions.WeatherProfile;
import com.example.aaronhoskins.mobile_nat_challange.model.forecast.ForecastProfile;

import java.io.IOException;

public class MainActivityPresenter {
    WeatherProfile weatherProfile;
    ForecastProfile forecastProfile;
    WeatherDataHandler wdh = new WeatherDataHandler();
       //                   //
      //  intitInformation //
     //*******************//
    public ThreadObjectReturns initInformation(String zipCode){
        Thread dataThread = new Thread(initWeatherProfileRunnable(zipCode));
        Thread arrayListThread = new Thread(initForecastProfileRunnable(zipCode));
        dataThread.start();
        arrayListThread.start();
        try {
            dataThread.join();
            arrayListThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }//end try/catch
        return new ThreadObjectReturns(wdh,weatherProfile,forecastProfile);
    }//end initInformation
      //                              //
     //  intitWeatherProfileRunnable //
    //******************************//
    Runnable initWeatherProfileRunnable(final String zipCode){
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                try {
                    weatherProfile = wdh.getWeatherData(zipCode);
                } catch (IOException e) {
                    e.printStackTrace();
                }//end try/catch
            }
        };
        return runnable;
    }// end initWeatherProfile
      //                               //
     //  intitForecastProfileRunnable //
    //*******************************//
    Runnable initForecastProfileRunnable (final String zipCode){
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                try {
                    forecastProfile = wdh.getForecastData(zipCode);
                } catch (IOException e) {
                    e.printStackTrace();
                }//end try/catch
            }
        };
        return runnable;
    }//end initForecastProfile
}//end class MainActivityPresenter
