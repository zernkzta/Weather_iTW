package com.example.weather_itw.retrofit;

import java.util.concurrent.TimeUnit;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitRequest {
    private static Retrofit retrofit_wea;
    private static Retrofit retrofit_aqi;

    public static Retrofit getRetrofitInstance_weather() {

        OkHttpClient okHttpClient = new OkHttpClient().newBuilder()
                .connectTimeout(20, TimeUnit.SECONDS)   // 設置連線Timeout
                .addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BASIC))
                .build();

        if (retrofit_wea == null) {
            retrofit_wea = new retrofit2.Retrofit.Builder()
                    .baseUrl(ApiRequest.WEA_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(okHttpClient)
                    .build();
        }

        return retrofit_wea;
    }
    public static Retrofit getRetrofitInstance_aqi() {
        OkHttpClient okHttpClient = new OkHttpClient().newBuilder()
                .connectTimeout(20, TimeUnit.SECONDS)   // 設置連線Timeout
                .addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BASIC))
                .build();
        if (retrofit_aqi == null) {
            retrofit_aqi = new retrofit2.Retrofit.Builder()
                    .baseUrl(ApiRequest.AQI_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(okHttpClient)
                    .build();
        }
        return retrofit_aqi;
    }
}
