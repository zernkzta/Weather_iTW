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
        binding.navView.setOnItemSelectedListener(navListener); //navView??????????????????(??????)
        binding.textDist.setOnClickListener(toolbarListener);    //textView??????????????????(??????)
        binding.btnDist.setOnClickListener(toolbarListener);    //button??????????????????(??????)
        binding.AQIHelp.setOnClickListener(alertListener);       //button??????????????????(??????)
        initPage(Page.PAGE_HOUR);   //????????????:hourFragment
        mainViewModel.getTitle().observe(this, s -> {
            binding.textDist.setText(s);
        });
    }


    private void initPage(Page page) {  //?????????Fragment??????????????????hourFragment
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
        //???{???????????????????????????}???{AQI?????????}???????????????????????????????????????
        switch (item.getItemId()) {
            case R.id.hour_weather:                             //Fragment(1)??????3????????????
                initPage(Page.PAGE_HOUR);
                binding.btnDist.setVisibility(View.VISIBLE);    //????????????????????????
                binding.textDist.setVisibility(View.VISIBLE);    //????????????????????????
                binding.textAQI.setVisibility(View.GONE);       //??????????????????
                binding.AQIHelp.setVisibility(View.GONE);        //??????AQI Help??????
                return true;
            case R.id.week_weather:                             //Fragment(2)???????????????
                initPage(Page.PAGE_WEEK);
                binding.btnDist.setVisibility(View.VISIBLE);
                binding.textDist.setVisibility(View.VISIBLE);
                binding.textAQI.setVisibility(View.GONE);
                binding.AQIHelp.setVisibility(View.GONE);
                return true;
            case R.id.sun_moon:                                 //Fragment(3)?????????/??????
                initPage(Page.PAGE_SUN_MOON);
                binding.btnDist.setVisibility(View.VISIBLE);
                binding.textDist.setVisibility(View.VISIBLE);
                binding.textAQI.setVisibility(View.GONE);
                binding.AQIHelp.setVisibility(View.GONE);
                return true;
            case R.id.aqi:                                      //Fragment(4)???AQI
                initPage(Page.PAGE_AQI);
                binding.btnDist.setVisibility(View.GONE);       //????????????????????????
                binding.textDist.setVisibility(View.GONE);       //????????????????????????
                binding.textAQI.setVisibility(View.VISIBLE);    //??????????????????
                binding.textAQI.startAnimation(animBlink);      //????????????start
                binding.AQIHelp.setVisibility(View.VISIBLE);     //??????AQI Help??????
                return true;
        }
        return false;
    };

    private final View.OnClickListener alertListener = view -> {    //??????button?????????AQI help Alert Dialog
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(MainActivity.this);
        View v = getLayoutInflater().inflate(R.layout.alert,null);
        alertDialog.setView(v);
        AlertDialog dialog = alertDialog.create();
        dialog.show();
        dialog.getWindow().setLayout(1000, 1000);
    };
    /*private final View.OnClickListener toolbarListener = view -> {  //????????????????????????????????????????????????
        listChild.clear();
        listGroup.clear();
        second.addDate(Dist.City);// ????????????????????????
        second.createExpandableListViewDialog(mainViewModel);//??????????????????
    };*/
    private final View.OnClickListener toolbarListener = view -> {  //????????????????????????????????????????????????
        listChild.clear();
        listGroup.clear();
        second.addDate(Dist.City);// ????????????????????????
        second.createExpandableListViewDialog();


        //Log.d("TESTRESULT","test : "+second.getCity());
    };

    @Override
    public void startListener(String title) {
        mainViewModel.setTitle(title);
        Log.d("TESTRESULT","test result title : "+title);
    }
}