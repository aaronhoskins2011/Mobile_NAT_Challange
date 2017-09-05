package com.example.aaronhoskins.mobile_nat_challange.data.local;



public final class Constants {
    //Weather Underground constants
    public static final String WEATHER_UNDERGROUND_KEY = "36fddd5400064a1f";
    public static final String URL_SCHEME_HTTP = "http";
    public static final String WEATHER_UNDERGROUND_BASE_URL = "api.wunderground.com";
    public static final String WEATHER_UNDERGROUND_FEATURE_CONDITIONS = "conditions";
    public static final String WEATHER_UNDERGROUND_FEATURE_HOURLY_10_DAY = "hourly10day";
    public static final String FEATURE_10_DAY_HOURLY = "featureHr10Day";
    public static final String FEATURE_CURRENT_CONDITIONS = "currentConditions";

    //Fragment IDs
    public static final String CURRENT_CONDITIONS_FRAG_ID = "currentConditionFrag";
    public static final String FORECAST_FRAG_ID = "forecastFrag";

    //Log Tags
    public static final String TAG_WEATHER_HANDLER = "Weather_Data_Handler";
    public static final String TAG_MAIN_ACTIVITY = "Main_Activity";
    public static final String TAG_FORCAST_FRAGMENT = "Forecast_Fragment";

    //Fragment Strings
    public static final String WEATHER_PROFILE_PARAM = "weatherprofile";

    //Display Strings
    public static final String WIND_INTRO_DISPLAY = "\tWinds are ";
    public static final String CURRENT_CONDITION_DISPLAY = "\tCurrent Conditions:\t";
    public static final String HUMIDITY_DISPLAY = "\tHumidity:\t\t";
    public static final String FEELS_LIKE_DISPLAY = "\tFeels Like:\t\t";
    public static final String DEGREE_MARK = "" + (char)0x00B0;

    //Intent Data id's
    public static final String PASS_ZIP_CODE_STRING = "passZipCode";




}
