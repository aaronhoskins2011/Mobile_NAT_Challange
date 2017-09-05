package com.example.aaronhoskins.mobile_nat_challange.view.ForecastFragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.aaronhoskins.mobile_nat_challange.R;
import com.example.aaronhoskins.mobile_nat_challange.model.forecast.ForecastProfile;
import com.example.aaronhoskins.mobile_nat_challange.model.forecast.HourlyForecast;

import java.util.ArrayList;

import static com.example.aaronhoskins.mobile_nat_challange.data.local.Constants.TAG_FORCAST_FRAGMENT;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ForecastFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ForecastFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ForecastFragment extends Fragment {
    ForecastProfile forecastProfile;
    RecyclerView forecastRecycleView;
    RecyclerView.LayoutManager layoutManager;
    RecyclerView.ItemAnimator itemAnimator;
    ForecastRecyleViewAdaptor forecastRecyleVieAdaptor;

    private static final String ARG_PARAM1 = "param1";

    // TODO: Rename and change types of parameters
    private String mParam1;


    private OnFragmentInteractionListener mListener;

    public ForecastFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @return A new instance of fragment ForecastFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ForecastFragment newInstance(ForecastProfile param1) {
        ForecastFragment fragment = new ForecastFragment();
        Bundle args = new Bundle();
        args.putSerializable(ARG_PARAM1, param1);
        fragment.setArguments(args);
        return fragment;
    }
    ArrayList<Double> temps = new ArrayList<Double>();
    ArrayList<Double> HiTemps = new ArrayList<Double>();
    ArrayList<Double> LoTemps = new ArrayList<Double>();
    ArrayList<String> daysList = new ArrayList<String>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            forecastProfile = (ForecastProfile) getArguments().getSerializable(ARG_PARAM1);
            initKeyValueLists();
        }
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        forecastRecyleVieAdaptor = new ForecastRecyleViewAdaptor(HiTemps,LoTemps,daysList);
        forecastRecycleView = (RecyclerView)view.findViewById(R.id.rvForecast);
        layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        itemAnimator = new DefaultItemAnimator();
        forecastRecycleView.setLayoutManager(layoutManager);
        forecastRecycleView.setItemAnimator(itemAnimator);
        forecastRecycleView.setAdapter(forecastRecyleVieAdaptor);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_forecast, container, false);
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onForecastInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }
    public OnFragmentInteractionListener getmListener() {
        return mListener;
    }
    public void initKeyValueLists(){
        String day = null;

        for(HourlyForecast forecast: forecastProfile.getHourlyForecast()){
            if(day == null){day = forecast.getFCTTIME().getWeekdayName();}
            if(day.equals(forecast.getFCTTIME().getWeekdayName())) {
                temps.add(Double.parseDouble(forecast.getTemp().getEnglish()));
            } else {
                double curHiTemp = -1000.00;
                double curLoTemp = 1000.00;
                for(Double d : temps){
                    if(d < curLoTemp){curLoTemp = d;}
                    if(d > curHiTemp){curHiTemp = d;}
                }
                Log.d(TAG_FORCAST_FRAGMENT, "onCreate: Day: " + day + " Hi Temp: " +  curHiTemp + " Lo Temp: " + curLoTemp);
                daysList.add(day);
                day = forecast.getFCTTIME().getWeekdayName();
                HiTemps.add(curHiTemp);
                LoTemps.add(curLoTemp);
                temps.clear();

            }
        }
    }




    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onForecastInteraction(Uri uri);

}
}
