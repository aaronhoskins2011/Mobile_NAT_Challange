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
import com.example.aaronhoskins.mobile_nat_challange.model.ForecastDataDisplayLists;
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
            initArrays();
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

   public void initArrays(){
       ForestFragmentPresenter forestFragmentPresenter = new ForestFragmentPresenter();
       ForecastDataDisplayLists forecastDataDisplayLists;
       forecastDataDisplayLists = forestFragmentPresenter.initKeyValueLists(forecastProfile);
       HiTemps = forecastDataDisplayLists.getHiTemps();
       LoTemps = forecastDataDisplayLists.getLoTemps();
       daysList = forecastDataDisplayLists.getDaysList();
   }


    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onForecastInteraction(Uri uri);

}
}
