package com.example.weather_itw.ui.weekWeather;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.weather_itw.Adapter.WeekExpandableListAdapter;
import com.example.weather_itw.MainViewModel;

import com.example.weather_itw.Utils.ImgUtil;
import com.example.weather_itw.databinding.FragmentWeekweatherBinding;
import com.example.weather_itw.model.expandable.ExpandChild;
import com.example.weather_itw.model.expandable.ExpandParent;
import com.example.weather_itw.model.week.Location;
import com.example.weather_itw.ui.hourWeather.HourWeatherViewModel;

import java.util.ArrayList;

public class WeekWeatherFragment extends Fragment {

    private FragmentWeekweatherBinding binding;
    private MainViewModel mainViewModel;
    private WeekWeatherViewModel weekWeatherViewModel;
    private final ArrayList<ExpandParent> expandarrayParent = new ArrayList<>();
    private WeekExpandableListAdapter mAdapter = null;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        weekWeatherViewModel = new ViewModelProvider(this).get(WeekWeatherViewModel.class);
        HourWeatherViewModel hourWeatherViewModel = new ViewModelProvider(requireActivity()).get(HourWeatherViewModel.class);
        mainViewModel = new ViewModelProvider(requireActivity()).get(MainViewModel.class);
        binding = FragmentWeekweatherBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        mainViewModel.getTitle().observe(getViewLifecycleOwner(), title -> {
            Log.d("DEBUG","msg : "+title);
            weekWeatherViewModel.setWeekWeather(title);     //設定地區
            callViewModel();    //從api中獲取資料並更新ui
        });
        //點擊child expand list時會顯示＂降雨機率＂,＂濕度＂,＂風向＂,＂風速＂等資訊
        binding.expendlist.setOnChildClickListener(((parent, v, groupPosition, childPosition, id) -> {
            Toast.makeText(getActivity(),"\t\t"+
                            expandarrayParent.get(groupPosition).getWea_date()+"\n降雨機率："+
                            expandarrayParent.get(groupPosition).getExpandChildArrayList().get(childPosition).getPop()+"\n相對溼度："+
                            expandarrayParent.get(groupPosition).getExpandChildArrayList().get(childPosition).getHumi()+"\n風向："+
                            expandarrayParent.get(groupPosition).getExpandChildArrayList().get(childPosition).getWdirection()+"\n風速："+
                            expandarrayParent.get(groupPosition).getExpandChildArrayList().get(childPosition).getWspeed(),
                    Toast.LENGTH_SHORT).show();
            return false;
        }));


        return root;
    }

    public void callViewModel(){
        weekWeatherViewModel.getWeekWeather().observe(getViewLifecycleOwner(), weekWeather -> {     //取得資料
            Location location = weekWeather.getRecords().getLocations().get(0).getLocation().get(0);
            expandarrayParent.clear();
             for(int i =0;i<7;i++){ //一個禮拜,i=0~6
                 String week_Desc = location.get_Week_Wx(i*2);  //time = i*2, time => api資料中每隔2單位為一天時間，所以time=(0,2,4,6,8,10,12)
                 ExpandParent expandParent = new ExpandParent(      //第一層填入日期、最低溫、最高溫、天氣圖示(Expand Parent)
                         location.get_Week_date(i*2),location.get_Week_MaxT(i*2),
                         location.get_Week_MinT(i*2),ImgUtil.convertImg(week_Desc));
                 expandarrayParent.add(i,expandParent);      //將資料存入expandarrayParent
                 ExpandChild expandChild = new ExpandChild(         //第二層填入降雨、風向、濕度、風速(Expand Child)
                         location.get_Week_RH(i*2),location.get_Week_PoP12h(i*2),
                         location.get_Week_WD(i*2),location.get_Week_WS(i*2));
                 expandarrayParent.get(i).getExpandChildArrayList().add(0,expandChild);
             }
            mAdapter = new WeekExpandableListAdapter(getActivity(),expandarrayParent);
            binding.expendlist.setAdapter(mAdapter);
            binding.expendlist.expandGroup(0);          //顯示當天氣象資訊(預設顯示當天與明天)
            binding.expendlist.expandGroup(1);          //顯示隔天氣象資訊(預設顯示當天與明天)
            mAdapter.notifyDataSetChanged();

        });

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}