package com.example.weather_itw.retrofit;

import com.example.weather_itw.model.aqi.AQIV2;
import com.example.weather_itw.model.hour.HourWeather;
import com.example.weather_itw.model.moon.Moon;
import com.example.weather_itw.model.sun.Sun;
import com.example.weather_itw.model.week.WeekWeather;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.Streaming;

public interface ApiRequest {
    String WEA_URL="https://opendata.cwb.gov.tw/api/v1/rest/datastore/";//中央氣象局API base URL
    //String AQI_URL="https://data.epa.gov.tw/api/v1/"; //AQI v1 版本停用(2022/05/19起)
    String AQI_URL="https://data.epa.gov.tw/api/v2/";   //AQI v2 版本(不穩定,經常回應過久)
    String SUN = "A-B0062-001";     //日出/日落擷取資料
    String MOON = "A-B0063-001";    //月出/月落擷取資料
    String AQI = "aqx_p_432";       //AQI擷取資料

    @Streaming
    @GET("{dataid}?&elementName=MinT,MaxT,WD,PoP12h,RH,Wx,WS")  //for Fragment WeekWeather
    Call<WeekWeather> getWeekWeather(@Path("dataid") String dataid, @Query("locationName")String locationName, @Header("Authorization") String authorization);

    @Streaming
    @GET("{dataid}?&elementName=Wx,T,RH,WeatherDescription,PoP6h,WS,WD")    //for Fragment HourWeather
    Call<HourWeather> getHourWeather(@Path("dataid") String dataid,@Query("locationName") String locationName, @Header("Authorization") String authorization);

    @Streaming
    @GET(SUN)
    Call<Sun> get_SunInfo(@Query("locationName") String location, @Query("timeFrom") String time_from, @Query("timeTo") String time_to,@Header("Authorization") String authorization);

    @Streaming
    @GET(MOON)
    Call<Moon> get_MoonInfo(@Query("locationName") String location, @Query("timeFrom") String time_from, @Query("timeTo") String time_to,@Header("Authorization") String authorization);

    @Streaming
    @GET(AQI)
    Call<AQIV2> get_AQIInfo(@Query("api_key") String api_key);
}
