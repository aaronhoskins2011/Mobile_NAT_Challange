package com.example.aaronhoskins.mobile_nat_challange.view.main_activity;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;

import com.example.aaronhoskins.mobile_nat_challange.R;
import com.example.aaronhoskins.mobile_nat_challange.data.remote.WeatherDataHandler;
import com.example.aaronhoskins.mobile_nat_challange.model.conditions.WeatherProfile;
import com.example.aaronhoskins.mobile_nat_challange.model.forecast.ForecastProfile;
import com.example.aaronhoskins.mobile_nat_challange.view.CurrentConditionsFragment.CurrentConditionsFragment;
import com.example.aaronhoskins.mobile_nat_challange.view.ForecastFragment.ForecastFragment;

import java.io.IOException;

import static com.example.aaronhoskins.mobile_nat_challange.data.local.Constants.CURRENT_CONDITIONS_FRAG_ID;
import static com.example.aaronhoskins.mobile_nat_challange.data.local.Constants.FORECAST_FRAG_ID;
import static com.example.aaronhoskins.mobile_nat_challange.data.local.Constants.PASS_ZIP_CODE_STRING;

public class MainActivity extends AppCompatActivity implements CurrentConditionsFragment.OnFragmentInteractionListener, ForecastFragment.OnFragmentInteractionListener {
   WeatherProfile weatherProfile;
    WeatherDataHandler wdh;
    ForecastProfile forecastProfile;
    FrameLayout currentConditionsFrame;
    FrameLayout forecastFrame;
    EditText etZipCode;
    String zipCode = "37148";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        etZipCode = (EditText)findViewById(R.id.etZipCode);
        wdh = new WeatherDataHandler();
       if(!(getIntent().getStringExtra(PASS_ZIP_CODE_STRING) == null)){
           zipCode = getIntent().getStringExtra(PASS_ZIP_CODE_STRING);
       }

        Thread dataThread = new Thread(initWeatherProfileRunnable(zipCode));
        Thread arrayListThread = new Thread(initForecastProfileRunnable(zipCode));
        dataThread.start();
        arrayListThread.start();
        try {
            dataThread.join();
            arrayListThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        initFragmentsForActivity();

    }

    Runnable initWeatherProfileRunnable(final String zipCode){
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                try {
                    weatherProfile = wdh.getWeatherData(zipCode);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        };
        return runnable;
    }
    Runnable initForecastProfileRunnable (final String zipCode){
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                try {
                    forecastProfile = wdh.getForecastData(zipCode);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        };
        return runnable;
    }
    public void initFragmentsForActivity(){
        initForecastFragment();
        initCurrentConditionFragment();
    }
    public void initForecastFragment(){
        forecastFrame = (FrameLayout)findViewById(R.id.flForecast);
        ForecastFragment forecastFragment = ForecastFragment.newInstance(forecastProfile);
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.flForecast,forecastFragment ,FORECAST_FRAG_ID)
                .commit();
    }
    public void initCurrentConditionFragment(){
        currentConditionsFrame = (FrameLayout)findViewById(R.id.flCurrentConditions);
        CurrentConditionsFragment currentConditionsFragment = CurrentConditionsFragment.newInstance(weatherProfile);
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.flCurrentConditions,currentConditionsFragment ,CURRENT_CONDITIONS_FRAG_ID)
                .commit();
    }


    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    @Override
    public void onForecastInteraction(Uri uri) {}

    public void onClickSetZip(View view) {
        zipCode = etZipCode.getText().toString();
        Intent intent = new Intent(this,MainActivity.class);
        intent.putExtra(PASS_ZIP_CODE_STRING, zipCode);
        startActivity(intent);
    }
}
