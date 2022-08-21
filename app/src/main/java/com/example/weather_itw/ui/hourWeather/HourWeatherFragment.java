package com.example.weather_itw.ui.hourWeather;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.weather_itw.Adapter.WeekQuickListAdapter;
import com.example.weather_itw.MainViewModel;
import com.example.weather_itw.R;
import com.example.weather_itw.Utils.ImgUtil;
import com.example.weather_itw.databinding.FragmentHourweatherBinding;
import com.example.weather_itw.model.marquee.Marquee;
import com.example.weather_itw.model.hour.WeatherElement;

import java.util.ArrayList;
import java.util.List;

public class HourWeatherFragment extends Fragment {

    private HourWeatherViewModel hourWeatherViewModel;
    private FragmentHourweatherBinding binding;
    private final List<Marquee> marquees = new ArrayList<>();
    private ArrayList<WeatherElement> weatherElementArrayList = new ArrayList<>();
    private WeekQuickListAdapter weekQuickListAdapter;
    private int selected;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        hourWeatherViewModel =
                new ViewModelProvider(this).get(HourWeatherViewModel.class);
        MainViewModel mainViewModel = new ViewModelProvider(requireActivity()).get(MainViewModel.class);
        binding = FragmentHourweatherBinding.inflate(inflater, container, false);
        binding.pbLoading.bringToFront();
        View root = binding.getRoot();
        init();
        mainViewModel.getTitle().observe(getViewLifecycleOwner(), title -> {
            binding.pbLoading.setVisibility(View.VISIBLE);//Loading畫面(轉圈)
            hourWeatherViewModel.setHourWeather(title);//設定地區
            callViewModel();//從api中獲取資料並更新ui
        });
        return root;
    }

    private void init(){ //初始化Adapter與marquees(填入假資料)
        weekQuickListAdapter = new WeekQuickListAdapter(getActivity(), weatherElementArrayList);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this.getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);     //設置為水平
        binding.recyWeaWeek.setLayoutManager(linearLayoutManager);
        binding.recyWeaWeek.setAdapter(weekQuickListAdapter);
        for(int i =0;i<6;i++){
            marquees.add(i,new Marquee());
            marquees.get(i).setImg(R.drawable.cloud);
            marquees.get(i).setTitle("第 "+(i+1)+" 項初始化資訊");
        }

    }

    private void callViewModel() {
            hourWeatherViewModel.getHourWeather().observe(getViewLifecycleOwner(), hourWeather -> {
            List<WeatherElement> weatherElements = hourWeather.getRecords().getLocations().get(0).getLocation().get(0).getWeatherElement();
            weatherElementArrayList.clear();
            weatherElementArrayList.addAll(weatherElements);
            weekQuickListAdapter.notifyDataSetChanged();
            if(!(weatherElementArrayList.isEmpty())){
                binding.pbLoading.setVisibility(View.GONE);
            }
            binding.setHourWeather(hourWeather);
            String hour_WDesc =hourWeather.getRecords().getLocations().get(0).getLocation().get(0).get_Hour_WDesc();
            String hour_Wx = hourWeather.getRecords().getLocations().get(0).getLocation().get(0).get_Hour_Wx();
            binding.weaImg.setImageResource(ImgUtil.convertImg(hour_Wx));
            String[] token = hour_WDesc.split("。");
            for(int i =0;i< token.length;i++){
                marquees.get(i).setImg(ImgUtil.convertImg(hour_Wx));
                marquees.get(i).setTitle(token[i]);
            }
            binding.marqueeView.startWithList(marquees);

                weekQuickListAdapter.setOnItemClickListener(new WeekQuickListAdapter.OnItemClickListener() {
                    @Override
                    public void OnItemClick(View view, int position) {
                        selected = position/2;  //用於降雨機率,因API提供的降雨機率最低為每6小時更新,所以每兩格更新一次
                        binding.weaStatus.setText(hourWeather.getRecords().getLocations().get(0).getLocation().get(0).get_Hour_Wx(position));
                        binding.weaTemp.setText(hourWeather.getRecords().getLocations().get(0).getLocation().get(0).get_Hour_T(position));
                        binding.weaPop.setText(hourWeather.getRecords().getLocations().get(0).getLocation().get(0).get_Hour_PoP6h(selected));
                        binding.weaWdirection.setText(hourWeather.getRecords().getLocations().get(0).getLocation().get(0).get_Hour_WD(position));
                        binding.weaHumi.setText(hourWeather.getRecords().getLocations().get(0).getLocation().get(0).get_Hour_RH(position));
                        binding.weaWspeed.setText(hourWeather.getRecords().getLocations().get(0).getLocation().get(0).get_Hour_WS(position));
                        String hour_WDesc =hourWeather.getRecords().getLocations().get(0).getLocation().get(0).get_Hour_WDesc(position);
                        String hour_Wx = hourWeather.getRecords().getLocations().get(0).getLocation().get(0).get_Hour_Wx(position);
                        binding.weaImg.setImageResource(ImgUtil.convertImg(hour_Wx));
                        String[] token = hour_WDesc.split("。");
                        for(int i =0;i< token.length;i++){
                            marquees.get(i).setImg(ImgUtil.convertImg(hour_Wx));
                            marquees.get(i).setTitle(token[i]);
                        }
                        binding.marqueeView.startWithList(marquees);

                    }
                });
        });

    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }


}