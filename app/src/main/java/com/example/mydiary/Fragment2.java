package com.example.mydiary;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.github.channguyen.rsv.RangeSliderView;

public class Fragment2 extends Fragment {
    private static final String TAG = Fragment2.class.getCanonicalName();

    Context context;
    OnTabItemSelectedListener listener;
    OnRequestListener requestListener;

    ImageView weatherIcon;
    TextView dateTextView;
    TextView locationTextView;


    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        this.context = context;

        if (context instanceof OnTabItemSelectedListener) {
            listener = (OnTabItemSelectedListener) context;
        }

        if (context instanceof OnRequestListener) {
            requestListener = (OnRequestListener) context;
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();

        if (context != null) {
            context = null;
            listener = null;
            requestListener = null;
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment2, container, false);

        initUI(rootView);

        if (requestListener != null) {
            requestListener.onRequest("getCurrentLocation");
        }

        return rootView;
    }

    private void initUI(ViewGroup rootView) {

        weatherIcon = rootView.findViewById(R.id.weatherIcon);
        dateTextView = rootView.findViewById(R.id.dateTextView);
        locationTextView = rootView.findViewById(R.id.locationTextView);

        Button saveButton = rootView.findViewById(R.id.saveButton);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    listener.onTabSelected(0);
                }
            }
        });

        Button deleteButton = rootView.findViewById(R.id.deleteButton);
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    listener.onTabSelected(0);
                }
            }
        });

        Button closeButton = rootView.findViewById(R.id.closeButton);
        closeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    listener.onTabSelected(0);
                }
            }
        });

        RangeSliderView sliderView = rootView.findViewById(R.id.sliderView);
        sliderView.setOnSlideListener(new RangeSliderView.OnSlideListener() {
            @Override
            public void onSlide(int index) {
                Toast.makeText(getContext(), "moodIndex Changed to : " + index, Toast.LENGTH_SHORT).show();
            }
        });

        sliderView.setInitialIndex(2);
    }

    /**
     * ① 맑음
     * ② 구름 조금
     * ③ 구름 많음
     * ④ 흐림
     * ⑤ 비
     * ⑥ 눈/비
     * ⑦ 눈
     *
     */
    public void setWeater(String data) {
        if (data != null) {
            if (data.equals("맑음")) {
                weatherIcon.setImageResource(R.drawable.weather_1);
            }
            ///////start to coding
        }
    }



    public void setAddress(String data) {
        locationTextView.setText(data);
    }

    public void setDateString(String dateString) {
        dateTextView.setText(dateString);
    }
}
