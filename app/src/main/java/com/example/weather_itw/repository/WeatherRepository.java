package com.example.weather_itw.repository;


import android.app.Activity;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.example.weather_itw.BuildConfig;
import com.example.weather_itw.Dist.Dist;
import com.example.weather_itw.model.aqi.AQIV2;
import com.example.weather_itw.model.hour.HourWeather;
import com.example.weather_itw.model.moon.Moon;
import com.example.weather_itw.model.sun.Sun;
import com.example.weather_itw.model.week.WeekWeather;
import com.example.weather_itw.retrofit.ApiRequest;
import com.example.weather_itw.retrofit.RetrofitRequest;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class WeatherRepository extends Activity {
    private static final String TAG = WeatherRepository.class.getSimpleName();
    private final ApiRequest apiRequest_wea;
    private final ApiRequest apiRequest_aqi;
    public WeatherRepository() {
        apiRequest_wea = RetrofitRequest.getRetrofitInstance_weather().create(ApiRequest.class);
        apiRequest_aqi = RetrofitRequest.getRetrofitInstance_aqi().create(ApiRequest.class);
    }
    public MutableLiveData<WeekWeather> getWeekWeather(String title) {
        final MutableLiveData<WeekWeather> data = new MutableLiveData<>();
        String [] arr;
        arr = title.split(",");
        String query = arr[0];
        String location = arr[1];
        apiRequest_wea.getWeekWeather(Dist.Convert_Week(query),location,BuildConfig.WEATHER_API)
                .enqueue(new Callback<WeekWeather>() {
                    @Override
                    public void onResponse(Call<WeekWeather> call, Response<WeekWeather> response) {
                        int status_code = response.code();
                        Log.d("TESTING","week weather Status code : "+status_code);
                        if (response.code() == 200){
                            data.setValue(response.body());
                        }
                        else{
                            data.setValue(null);
                        }
                    }
                    @Override
                    public void onFailure(Call<WeekWeather> call, Throwable t) {
                        data.setValue(null);
                        Log.d("Weather","get data failed");
                    }
                });
        return data;
    }
    public MutableLiveData<HourWeather> getHourWeather(String title) {
        final MutableLiveData<HourWeather> data = new MutableLiveData<>();
        String [] arr;
        arr = title.split(",");
        String query = arr[0];
        String location = arr[1];
        apiRequest_wea.getHourWeather(Dist.Convert_Hour(query),location,BuildConfig.WEATHER_API)
                .enqueue(new Callback<HourWeather>() {
                    @Override
                    public void onResponse(Call<HourWeather> call, Response<HourWeather> response) {
                        int status_code = response.code();
                        Log.d("TESTING","hour weather Status code : "+status_code);
                        if (response.code() == 200){
                            data.setValue(response.body());
                        }
                        else{
                            data.setValue(null);
                        }
                    }
                    @Override
                    public void onFailure(Call<HourWeather> call, Throwable t) {
                        data.setValue(null);
                    }
                });
        return data;
    }
    public MutableLiveData<Sun> getSunInfo(String title,String time_from,String time_to) {
        final MutableLiveData<Sun> data = new MutableLiveData<>();
        String [] arr;
        arr = title.split(",");
        String city = arr[0];
       // String dist = arr[1];
        apiRequest_wea.get_SunInfo(city,time_from,time_to,BuildConfig.WEATHER_API)
                .enqueue(new Callback<Sun>() {
                    @Override
                    public void onResponse(Call<Sun> call, Response<Sun> response) {
                        int status_code = response.code();
                        Log.d("TESTING","Sun Status code : "+status_code);
                        if (response.code() == 200){
                            data.setValue(response.body());
                        }
                        else{
                            data.setValue(null);
                        }
                    }
                    @Override
                    public void onFailure(Call<Sun> call, Throwable t) {
                        data.setValue(null);
                    }
                });
        return data;
    }
    public MutableLiveData<Moon> getMoonInfo(String title, String time_from, String time_to) {
        final MutableLiveData<Moon> data = new MutableLiveData<>();
        String [] arr;
        arr = title.split(",");
        String city = arr[0];
        apiRequest_wea.get_MoonInfo(city,time_from,time_to,BuildConfig.WEATHER_API)
                .enqueue(new Callback<Moon>() {
                    @Override
                    public void onResponse(Call<Moon> call, Response<Moon> response) {
                        int status_code = response.code();
                        Log.d("TESTING","Moon Status code : "+status_code);
                        if (response.code() == 200){
                            data.setValue(response.body());
                        }
                        else{
                            data.setValue(null);
                        }
                    }
                    @Override
                    public void onFailure(Call<Moon> call, Throwable t) {
                        data.setValue(null);
                    }
                });
        return data;
    }
    public MutableLiveData<AQIV2> getAQIInfo(){
        final MutableLiveData<AQIV2> data = new MutableLiveData<>();
        apiRequest_aqi.get_AQIInfo(BuildConfig.AQI_API).enqueue(new Callback<AQIV2>() {
            @Override
            public void onResponse(Call<AQIV2> call, Response<AQIV2> response) {
                int status_code = response.code();
                Log.d("TESTING","AQI v2 Status code : "+status_code);
                if (response.code() == 200){
                    data.setValue(response.body());
                }
                else{
                    data.setValue(null);
                    Log.d("TESTING","AQI catch failed");
                }
            }

            @Override
            public void onFailure(Call<AQIV2> call, Throwable t) {
                data.setValue(null);
                Log.d("TESTING","AQI catch failed");

            }
        });
        return data;
    }

}
