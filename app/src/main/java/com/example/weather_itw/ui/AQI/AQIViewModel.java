package com.example.weather_itw.ui.AQI;

import android.os.CountDownTimer;
import android.os.Handler;
import android.os.SystemClock;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.weather_itw.model.aqi.AQI;
import com.example.weather_itw.model.aqi.AQIV2;
import com.example.weather_itw.repository.WeatherRepository;

import java.util.Timer;

public class AQIViewModel extends ViewModel {
    private WeatherRepository weatherRepository;
    private MutableLiveData<AQIV2> AQILiveData;
    private MutableLiveData<String> title;
    private Handler handler;
    private Timer timer;


    public AQIViewModel() {
        weatherRepository = new WeatherRepository();
        AQILiveData = new MutableLiveData<>();
        title = new MutableLiveData<>();
        title.setValue("新北市,板橋");
        handler = new Handler();
        timer = new Timer();
    }
    public void setTitle(String title) {
        this.title.setValue(title);
    }
    public MutableLiveData<String> getTitle() {
        return title;
    }
    public MutableLiveData<AQIV2> getAQILiveData(){
        return this.AQILiveData;
    }

    public void setAQILiveData(){
            AQILiveData = weatherRepository.getAQIInfo();
            Log.d("AQITAG","Call Once");

    }
}
