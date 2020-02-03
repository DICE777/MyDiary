package com.example.mydiary.data;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

class WeatherBody {

    @SerializedName("data")
    public ArrayList<WeatherItem> datas;
}
