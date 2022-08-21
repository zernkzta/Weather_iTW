package com.example.weather_itw.ui.hourWeather;

import androidx.databinding.ObservableField;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.weather_itw.BuildConfig;
import com.example.weather_itw.model.hour.HourWeather;
import com.example.weather_itw.model.week.WeekWeather;
import com.example.weather_itw.repository.WeatherRepository;

public class HourWeatherViewModel extends ViewModel {
    private WeatherRepository weatherRepository;
    private LiveData<HourWeather> hourWeatherLiveData;

    public HourWeatherViewModel() {
        weatherRepository = new WeatherRepository();
        hourWeatherLiveData = new MutableLiveData<>();
    }



    public LiveData<HourWeather> getHourWeather(){
        return hourWeatherLiveData;
    }

    public void setHourWeather(String title){
        this.hourWeatherLiveData = weatherRepository.getHourWeather(title);
    }

}