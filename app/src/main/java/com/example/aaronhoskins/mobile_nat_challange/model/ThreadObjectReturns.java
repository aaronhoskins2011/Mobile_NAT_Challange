package com.example.aaronhoskins.mobile_nat_challange.model;

import com.example.aaronhoskins.mobile_nat_challange.data.remote.WeatherDataHandler;
import com.example.aaronhoskins.mobile_nat_challange.model.conditions.WeatherProfile;
import com.example.aaronhoskins.mobile_nat_challange.model.forecast.ForecastProfile;

/**
 * Created by cathye.johnson on 9/5/2017.
 */

public class ThreadObjectReturns {
    WeatherDataHandler wdh;
    WeatherProfile weatherProfile;
    ForecastProfile forecastProfile;

    public WeatherDataHandler getWdh() {
        return wdh;
    }

    public void setWdh(WeatherDataHandler wdh) {
        this.wdh = wdh;
    }

    public WeatherProfile getWeatherProfile() {
        return weatherProfile;
    }

    public void setWeatherProfile(WeatherProfile weatherProfile) {
        this.weatherProfile = weatherProfile;
    }

    public ForecastProfile getForecastProfile() {
        return forecastProfile;
    }

    public void setForecastProfile(ForecastProfile forecastProfile) {
        this.forecastProfile = forecastProfile;
    }

    public ThreadObjectReturns(WeatherDataHandler wdh, WeatherProfile weatherProfile, ForecastProfile forecastProfile) {

        this.wdh = wdh;
        this.weatherProfile = weatherProfile;
        this.forecastProfile = forecastProfile;
    }
}
