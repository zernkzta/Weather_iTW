package com.example.weather_itw.ui.weekWeather;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.weather_itw.BuildConfig;
import com.example.weather_itw.model.week.WeekWeather;
import com.example.weather_itw.repository.WeatherRepository;

public class WeekWeatherViewModel extends ViewModel {
    private WeatherRepository weatherRepository;
    private MutableLiveData<WeekWeather> weekWeatherMutableLiveData;

    public WeekWeatherViewModel() {
        weekWeatherMutableLiveData = new MutableLiveData<>();
        weatherRepository = new WeatherRepository();
    }

    public void setWeekWeather(String title){
        weekWeatherMutableLiveData = weatherRepository.getWeekWeather(title);
    }

    public LiveData<WeekWeather> getWeekWeather() {
        return this.weekWeatherMutableLiveData;
    }
}