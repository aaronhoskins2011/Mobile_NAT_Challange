package com.example.aaronhoskins.mobile_nat_challange.data.remote;

import android.net.Uri;
import android.util.Log;

import com.example.aaronhoskins.mobile_nat_challange.model.conditions.WeatherProfile;
import com.example.aaronhoskins.mobile_nat_challange.model.forecast.ForecastProfile;
import com.google.gson.Gson;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import static com.example.aaronhoskins.mobile_nat_challange.data.local.Constants.FEATURE_10_DAY_HOURLY;
import static com.example.aaronhoskins.mobile_nat_challange.data.local.Constants.FEATURE_CURRENT_CONDITIONS;
import static com.example.aaronhoskins.mobile_nat_challange.data.local.Constants.TAG_WEATHER_HANDLER;
import static com.example.aaronhoskins.mobile_nat_challange.data.local.Constants.URL_SCHEME_HTTP;
import static com.example.aaronhoskins.mobile_nat_challange.data.local.Constants.WEATHER_UNDERGROUND_BASE_URL;
import static com.example.aaronhoskins.mobile_nat_challange.data.local.Constants.WEATHER_UNDERGROUND_FEATURE_CONDITIONS;
import static com.example.aaronhoskins.mobile_nat_challange.data.local.Constants.WEATHER_UNDERGROUND_FEATURE_HOURLY_10_DAY;
import static com.example.aaronhoskins.mobile_nat_challange.data.local.Constants.WEATHER_UNDERGROUND_KEY;




public class WeatherDataHandler {
      //                          //
     //  get Weather Data        //
    //**************************//
    public WeatherProfile getWeatherData(String zipCode) throws IOException {
        Gson gson = new Gson();
        return gson.fromJson(getJsonResponse(FEATURE_CURRENT_CONDITIONS,zipCode),WeatherProfile.class);
    }
      //                          //
     //  get Forecast Data       //
    //**************************//
    public ForecastProfile getForecastData(String zipCode) throws IOException{
        Gson gson = new Gson();
        return gson.fromJson(getJsonResponse(FEATURE_10_DAY_HOURLY,zipCode), ForecastProfile.class);
    }

      //                      //
     //  Get Json Response   //
    //**********************//
      String responseReturn = "ERROR";
    public String getJsonResponse(String dataNeeded, String zipCode) throws IOException {
        final OkHttpClient client = new OkHttpClient();
        final Request request = new Request.Builder()
                                    .url(getURLForJsonResponse(dataNeeded, zipCode))
                                    .build();

        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);
                responseReturn = response.body().string();
            }
        Log.d(TAG_WEATHER_HANDLER, "getJsonResponse: Json Response Generated for " + dataNeeded);
        return responseReturn;
    }
          //                      //
         //  Get URL For Json    //
        //**********************//
    public String getURLForJsonResponse(String dataNeeded, String zipCode){
        String returnString;
        Uri.Builder uriBuilder = new Uri.Builder();
        uriBuilder.scheme(URL_SCHEME_HTTP)
                .authority(WEATHER_UNDERGROUND_BASE_URL)
                .appendPath("api")
                .appendPath(WEATHER_UNDERGROUND_KEY);
        //switch between what kind of json is needed
        switch(dataNeeded){
            case FEATURE_CURRENT_CONDITIONS:
                uriBuilder.appendPath(WEATHER_UNDERGROUND_FEATURE_CONDITIONS);
                break;
            case FEATURE_10_DAY_HOURLY:
                uriBuilder.appendPath(WEATHER_UNDERGROUND_FEATURE_HOURLY_10_DAY);
                break;
        }
        uriBuilder.appendPath("q").appendPath(zipCode + ".json");
        returnString = uriBuilder.build().toString(); //just so we can log it to, otherwise return this
        Log.d(TAG_WEATHER_HANDLER, "getURLForJsonResponse: URL = " + returnString);
        return returnString;
    }
}
