package com.example.aaronhoskins.mobile_nat_challange.view.CurrentConditionsFragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.aaronhoskins.mobile_nat_challange.R;
import com.example.aaronhoskins.mobile_nat_challange.model.conditions.WeatherProfile;


import static com.example.aaronhoskins.mobile_nat_challange.data.local.Constants.CURRENT_CONDITION_DISPLAY;
import static com.example.aaronhoskins.mobile_nat_challange.data.local.Constants.DEGREE_MARK;
import static com.example.aaronhoskins.mobile_nat_challange.data.local.Constants.FEELS_LIKE_DISPLAY;
import static com.example.aaronhoskins.mobile_nat_challange.data.local.Constants.HUMIDITY_DISPLAY;
import static com.example.aaronhoskins.mobile_nat_challange.data.local.Constants.WEATHER_PROFILE_PARAM;
import static com.example.aaronhoskins.mobile_nat_challange.data.local.Constants.WIND_INTRO_DISPLAY;

public class CurrentConditionsFragment extends Fragment {

    private WeatherProfile weatherProfile;
    private OnFragmentInteractionListener mListener;
    //Views
    TextView tvTemperature;
    TextView tvLocation;
    TextView tvCurrentWeather;
    TextView tvRelativeHumidity;
    TextView tvWindCondition;
    TextView tvFeelsLike;
    ImageView imgWeatherImage;

    public CurrentConditionsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @return A new instance of fragment CurrentConditionsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CurrentConditionsFragment newInstance(WeatherProfile param1) {
        CurrentConditionsFragment fragment = new CurrentConditionsFragment();
        Bundle args = new Bundle();
        args.putSerializable(WEATHER_PROFILE_PARAM, param1);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            weatherProfile = (WeatherProfile)getArguments().getSerializable(WEATHER_PROFILE_PARAM);
        }

    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        tvCurrentWeather = (TextView)view.findViewById(R.id.tvCurrentWeather);
        tvFeelsLike = (TextView)view.findViewById(R.id.tvFeelsLike);
        tvLocation = (TextView)view.findViewById(R.id.tvLocation);
        tvRelativeHumidity = (TextView)view.findViewById(R.id.tvRelativeHumidity);
        tvTemperature = (TextView)view.findViewById(R.id.tvTemperature);
        tvWindCondition = (TextView)view.findViewById(R.id.tvWindCondition);
        imgWeatherImage = (ImageView)view.findViewById(R.id.ivWeatherImage);


        Glide.with(this).load(weatherProfile.getCurrentObservation().getIconUrl()).into(imgWeatherImage);
        tvCurrentWeather.setText(CURRENT_CONDITION_DISPLAY + weatherProfile.getCurrentObservation().getWeather());
        tvFeelsLike.setText(FEELS_LIKE_DISPLAY + weatherProfile.getCurrentObservation().getFeelslikeString());
        tvLocation.setText(weatherProfile.getCurrentObservation().getDisplayLocation().getFull());
        tvRelativeHumidity.setText(HUMIDITY_DISPLAY + weatherProfile.getCurrentObservation().getRelativeHumidity());
        tvTemperature.setText(String.valueOf(weatherProfile.getCurrentObservation().getTempF()) + DEGREE_MARK);
        tvWindCondition.setText(WIND_INTRO_DISPLAY + weatherProfile.getCurrentObservation().getWindString());
        if(Double.parseDouble(weatherProfile.getCurrentObservation().getTempF()) > 60.0){
            tvTemperature.setTextColor(getResources().getColor(R.color.orangeRed));
        } else {
            tvTemperature.setTextColor(getResources().getColor(R.color.icyBlue));
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_current_conditions, container, false);
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
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
        void onFragmentInteraction(Uri uri);
    }


}
