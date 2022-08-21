package com.example.weather_itw;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;

import android.app.Dialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ExpandableListView;

import com.example.weather_itw.Adapter.MenuExpandableListAdapter;
import com.example.weather_itw.Dist.Dist;
import com.example.weather_itw.Utils.SecondaryMemuUtils;
import com.example.weather_itw.ui.AQI.AQIFragment;
import com.example.weather_itw.ui.SunMoon.SunMoonFragment;
import com.example.weather_itw.ui.hourWeather.HourWeatherFragment;
import com.example.weather_itw.ui.hourWeather.HourWeatherViewModel;
import com.example.weather_itw.ui.weekWeather.WeekWeatherFragment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import com.example.weather_itw.databinding.ActivityMainBinding;
import com.google.android.material.navigation.NavigationBarView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements SecondaryMemuUtils.testClickListener{
    private ExpandableListView expandableListView;
    private HourWeatherFragment hourFragment;
    private WeekWeatherFragment weekFragment;
    private SunMoonFragment sunmoonFragment;
    private AQIFragment aqiFragment;
    private FragmentTransaction transaction;
    private ActivityMainBinding binding;
    private final List<String> listGroup = new ArrayList<>();
    private final List<List<String>> listChild = new ArrayList<>();
    private SecondaryMemuUtils second;
    private MainViewModel mainViewModel;
    private Animation animBlink;



    private enum Page {PAGE_HOUR, PAGE_WEEK, PAGE_SUN_MOON, PAGE_AQI}

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mainViewModel = new ViewModelProvider(this).get(MainViewModel.class);
        HourWeatherViewModel hourWeatherViewModel = new ViewModelProvider(this).get(HourWeatherViewModel.class);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        animBlink = AnimationUtils.loadAnimation(getApplicationContext(),
                R.anim.blink);
        second = new SecondaryMemuUtils(MainActivity.this, expandableListView, listGroup, listChild,
                Dist.City, Dist.District);
        binding.navView.setOnItemSelectedListener(navListener); //navView加上監聽事件(選取)
        binding.textDist.setOnClickListener(toolbarListener);    //textView加上監聽事件(按下)
        binding.btnDist.setOnClickListener(toolbarListener);    //button加上監聽事件(按下)
        binding.AQIHelp.setOnClickListener(alertListener);       //button加上監聽事件(按下)
        initPage(Page.PAGE_HOUR);   //預設頁面:hourFragment
        mainViewModel.getTitle().observe(this, s -> {
            binding.textDist.setText(s);
        });
    }


    private void initPage(Page page) {  //初始化Fragment，預設頁面：hourFragment
        transaction = getSupportFragmentManager().beginTransaction();
        hideFragments(transaction);
        switch (page) {
            case PAGE_HOUR:
                if (hourFragment == null) {
                    hourFragment = new HourWeatherFragment();
                    transaction.add(R.id.container, hourFragment);
                } else {
                    transaction.show(hourFragment);
                }
                break;
            case PAGE_WEEK:
                if (weekFragment == null) {
                    weekFragment = new WeekWeatherFragment();
                    transaction.add(R.id.container, weekFragment);
                } else {
                    transaction.show(weekFragment);
                }
                break;
            case PAGE_SUN_MOON:
                if (sunmoonFragment == null) {
                    sunmoonFragment = new SunMoonFragment();
                    transaction.add(R.id.container, sunmoonFragment);
                } else {
                    transaction.show(sunmoonFragment);
                }
                break;
            case PAGE_AQI:
                if (aqiFragment == null) {
                    aqiFragment = new AQIFragment();
                    transaction.add(R.id.container, aqiFragment);

                } else {
                    transaction.show(aqiFragment);
                }
                //binding.txtView.setVisibility(View.INVISIBLE);
                break;

        }
        transaction.commitAllowingStateLoss();

    }

    private void hideFragments(FragmentTransaction transaction) {   //hide fragment func.
        if (hourFragment != null) {
            transaction.hide(hourFragment);
        }
        if (weekFragment != null) {
            transaction.hide(weekFragment);
        }
        if (sunmoonFragment != null) {
            transaction.hide(sunmoonFragment);
        }
        if (aqiFragment != null) {
            transaction.hide(aqiFragment);
        }
    }

    @SuppressLint("NonConstantResourceId")
    private final NavigationBarView.OnItemSelectedListener navListener = item -> {
        //因{中央氣象局觀測地區}與{AQI觀測站}的地區並不一致，需進行區隔
        switch (item.getItemId()) {
            case R.id.hour_weather:                             //Fragment(1)：逐3小時天氣
                initPage(Page.PAGE_HOUR);
                binding.btnDist.setVisibility(View.VISIBLE);    //顯示選取地區按鈕
                binding.textDist.setVisibility(View.VISIBLE);    //顯示地區文字區塊
                binding.textAQI.setVisibility(View.GONE);       //隱藏提示訊息
                binding.AQIHelp.setVisibility(View.GONE);        //隱藏AQI Help按鈕
                return true;
            case R.id.week_weather:                             //Fragment(2)：一周天氣
                initPage(Page.PAGE_WEEK);
                binding.btnDist.setVisibility(View.VISIBLE);
                binding.textDist.setVisibility(View.VISIBLE);
                binding.textAQI.setVisibility(View.GONE);
                binding.AQIHelp.setVisibility(View.GONE);
                return true;
            case R.id.sun_moon:                                 //Fragment(3)：日出/月落
                initPage(Page.PAGE_SUN_MOON);
                binding.btnDist.setVisibility(View.VISIBLE);
                binding.textDist.setVisibility(View.VISIBLE);
                binding.textAQI.setVisibility(View.GONE);
                binding.AQIHelp.setVisibility(View.GONE);
                return true;
            case R.id.aqi:                                      //Fragment(4)：AQI
                initPage(Page.PAGE_AQI);
                binding.btnDist.setVisibility(View.GONE);       //隱藏選取地區按鈕
                binding.textDist.setVisibility(View.GONE);       //隱藏地區文字區塊
                binding.textAQI.setVisibility(View.VISIBLE);    //顯示提示訊息
                binding.textAQI.startAnimation(animBlink);      //閃爍動畫start
                binding.AQIHelp.setVisibility(View.VISIBLE);     //顯示AQI Help按鈕
                return true;
        }
        return false;
    };

    private final View.OnClickListener alertListener = view -> {    //監聽button，顯示AQI help Alert Dialog
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(MainActivity.this);
        View v = getLayoutInflater().inflate(R.layout.alert,null);
        alertDialog.setView(v);
        AlertDialog dialog = alertDialog.create();
        dialog.show();
        dialog.getWindow().setLayout(1000, 1000);
    };
    /*private final View.OnClickListener toolbarListener = view -> {  //監聽事件，按下時顯示雙層地區選單
        listChild.clear();
        listGroup.clear();
        second.addDate(Dist.City);// 加入雙層選單資料
        second.createExpandableListViewDialog(mainViewModel);//顯示雙層選單
    };*/
    private final View.OnClickListener toolbarListener = view -> {  //監聽事件，按下時顯示雙層地區選單
        listChild.clear();
        listGroup.clear();
        second.addDate(Dist.City);// 加入雙層選單資料
        second.createExpandableListViewDialog();


        //Log.d("TESTRESULT","test : "+second.getCity());
    };

    @Override
    public void startListener(String title) {
        mainViewModel.setTitle(title);
        Log.d("TESTRESULT","test result title : "+title);
    }
}