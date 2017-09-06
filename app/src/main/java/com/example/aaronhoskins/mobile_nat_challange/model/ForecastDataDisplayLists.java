package com.example.aaronhoskins.mobile_nat_challange.model;

import java.util.ArrayList;

/**
 * Created by cathye.johnson on 9/5/2017.
 */

public class ForecastDataDisplayLists {
    ArrayList<Double> HiTemps = new ArrayList<Double>();
    ArrayList<Double> LoTemps = new ArrayList<Double>();
    ArrayList<String> daysList = new ArrayList<String>();

    public ForecastDataDisplayLists() {
    }

    public ArrayList<Double> getHiTemps() {
        return HiTemps;
    }

    public void setHiTemps(ArrayList<Double> hiTemps) {
        HiTemps = hiTemps;
    }

    public ArrayList<Double> getLoTemps() {
        return LoTemps;
    }

    public void setLoTemps(ArrayList<Double> loTemps) {
        LoTemps = loTemps;
    }

    public ArrayList<String> getDaysList() {
        return daysList;
    }

    public void setDaysList(ArrayList<String> daysList) {
        this.daysList = daysList;
    }

    public ForecastDataDisplayLists(ArrayList<Double> hiTemps, ArrayList<Double> loTemps, ArrayList<String> daysList) {

        HiTemps = hiTemps;
        LoTemps = loTemps;
        this.daysList = daysList;
    }
}
