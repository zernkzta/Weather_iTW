package com.example.weather_itw.ui.SunMoon;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.weather_itw.BuildConfig;
import com.example.weather_itw.model.moon.Moon;
import com.example.weather_itw.model.sun.Sun;
import com.example.weather_itw.repository.WeatherRepository;

public class SunMoonViewModel extends ViewModel {
    private WeatherRepository weatherRepository;
    private MutableLiveData<Sun> sunLiveData;
    private MutableLiveData<Moon> moonLiveData;

    public SunMoonViewModel() {
        weatherRepository = new WeatherRepository();
        sunLiveData = new MutableLiveData<>();
        moonLiveData = new MutableLiveData<>();
    }

    public void setSunInfo(String title,String time_from,String time_to){
        sunLiveData = weatherRepository.getSunInfo(title,time_from,time_to);
    }

    public MutableLiveData<Sun> getSunInfo(){
        return this.sunLiveData;
    }

    public void setMoonInfo(String title,String time_from,String time_to){
        moonLiveData = weatherRepository.getMoonInfo(title,time_from,time_to);
    }

    public MutableLiveData<Moon> getMoonInfo(){
        return this.moonLiveData;
    }

}