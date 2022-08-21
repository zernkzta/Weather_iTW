package com.example.weather_itw.ui.SunMoon;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.dynamicanimation.animation.DynamicAnimation;
import androidx.dynamicanimation.animation.SpringAnimation;
import androidx.dynamicanimation.animation.SpringForce;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.weather_itw.MainViewModel;
import com.example.weather_itw.R;
import com.example.weather_itw.databinding.FragmentSunmoonBinding;
import com.example.weather_itw.ui.hourWeather.HourWeatherViewModel;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.TimeZone;

import pl.droidsonroids.gif.GifDrawable;

public class SunMoonFragment extends Fragment {

    private SpringAnimation animation;
    private GifDrawable gifDrawable;
    private FragmentSunmoonBinding binding;
    private SunMoonViewModel sunMoonViewModel;
    private HourWeatherViewModel hourWeatherViewModel;
    private Calendar cal;
    private String today,nextday;
    private String sun_rise,sun_set;
    private int start,end,current_time;
    private int hour = 0,min = 0;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        sunMoonViewModel = new ViewModelProvider(this).get(SunMoonViewModel.class);
        MainViewModel mainViewModel = new ViewModelProvider(requireActivity()).get(MainViewModel.class);
        hourWeatherViewModel = new ViewModelProvider(requireActivity()).get(HourWeatherViewModel.class);
        binding = FragmentSunmoonBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        binding.sun.setVisibility(View.INVISIBLE);
        binding.riseTime.setVisibility(View.INVISIBLE);
        binding.setTime.setVisibility(View.INVISIBLE);
        animation = new SpringAnimation(binding.weaDayNight,DynamicAnimation.TRANSLATION_Y,0);
        binding.imgSunrise.setOnLongClickListener(longClickListener);
        init_Time();

        mainViewModel.getTitle().observe(getViewLifecycleOwner(), title -> {
            binding.pbLoading.setVisibility(View.VISIBLE);      //轉圈讀取動畫
            hourWeatherViewModel.setHourWeather(title);         //設定地區
            sunMoonViewModel.setSunInfo(title,today,nextday);   //取得api特定日期的資料(日)
            sunMoonViewModel.setMoonInfo(title,today,nextday);  //取得api特定日期的資料(月)
            callViewModel();    //從api中獲取資料並更新ui
        });
        return root;
    }

    private final View.OnLongClickListener longClickListener = v -> {
        Toast.makeText(getActivity(), "日出", Toast.LENGTH_SHORT).show();
        return false;
    };
    private void display_Animation(boolean display){    //是否顯示日出動畫
        if(display){
            binding.sun.setVisibility(View.VISIBLE);
            binding.riseTime.setVisibility(View.VISIBLE);
            binding.setTime.setVisibility(View.VISIBLE);
            binding.aniSunrise.setVisibility(View.VISIBLE);
            binding.aniSunset.setVisibility(View.VISIBLE);
            binding.imageView.setVisibility(View.INVISIBLE);

        }
        else{
            binding.sun.setVisibility(View.INVISIBLE);
            binding.riseTime.setVisibility(View.INVISIBLE);
            binding.setTime.setVisibility(View.INVISIBLE);
            binding.aniSunrise.setVisibility(View.INVISIBLE);
            binding.aniSunset.setVisibility(View.INVISIBLE);
            binding.imageView.setVisibility(View.VISIBLE);
            try{
                gifDrawable = new GifDrawable(getResources(), R.drawable.charging);
            }catch (IOException e){
                e.printStackTrace();
            }
            binding.imageView.setImageDrawable(gifDrawable);
        }
    }
    private void callViewModel(){
        sunMoonViewModel.getSunInfo().observe(getViewLifecycleOwner(), sun -> {     //太陽
            binding.setSunData(sun);
            sun_rise = sun.get_SunRise_Time();  //從api取得的日出時間
            sun_set = sun.get_SunSet_Time();    //從api取得的日落時間
            binding.sun.setStart_Time(sun_rise);//設定日出時間
            binding.sun.setEnd_Time(sun_set);   //設定日落時間
            start = trans_Time(sun_rise);
            end = trans_Time(sun_set);
            current_time = trans_Time(hour+":"+min);    //目前時間
    //      DEBUG : current_time = trans_Time("04:12");
            // 早/晚上圖片的彈簧動畫
            animation.getSpring().setDampingRatio(SpringForce.DAMPING_RATIO_LOW_BOUNCY);
            animation.getSpring().setStiffness(SpringForce.STIFFNESS_VERY_LOW);
            animation.setStartVelocity(4000);

        });
        sunMoonViewModel.getMoonInfo().observe(getViewLifecycleOwner(), moon -> {    //月亮
            binding.setMoonData(moon);
            if(moon.getSuccess().equals("true")){
                binding.pbLoading.setVisibility(View.GONE);
                animation.start();
            }
            if(current_time >= start && current_time<=end){     //白天時間
                display_Animation(true);                        //顯示動畫
                binding.sun.setTime(hour+":"+min);
                //binding.sun.setTime("05:12");
                binding.weaDayNight.setImageResource(R.drawable.day);   //日落以前為白天
            }
            else{
                display_Animation(false);  //動畫隱藏，顯示charging gif圖片
                binding.weaDayNight.setImageResource(R.drawable.night); //日落以後為夜晚
            }
        });
            hourWeatherViewModel.getHourWeather().observe(getViewLifecycleOwner(), hourWeather -> {
            binding.setHourWeather(hourWeather);    //data binding
        });

    }

    public void init_Time(){    //初始化時間
        cal = Calendar.getInstance();
        cal.setTimeZone(TimeZone.getTimeZone("GMT+8"));
        hour = cal.get(Calendar.HOUR_OF_DAY);   //取得現在時間(小時)
        min  = cal.get(Calendar.MINUTE);        //取得現在時間(分鐘)
        Log.d("TIME","TIME = "+hour+" : "+min);
        today = LocalDate.now().toString();     //api所需資訊,time from
        nextday = LocalDate.now().plusDays(1).toString();   //api所需資訊,time to


    }
    private int trans_Time(String time) {       //將時間轉換為秒
        int value = 0;
        if (time == null) {
            return value;
        }
        String[] s = time.split(":");
        int hour = Integer.parseInt(s[0]);
        int minute = Integer.parseInt(s[1]);
        value = hour * 60 + minute;
        //Log.d("SUN","hour = "+hour+", min = "+minute+", Time tran = "+value);
        return value;
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}