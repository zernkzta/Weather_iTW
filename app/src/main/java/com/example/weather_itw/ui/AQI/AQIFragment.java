package com.example.weather_itw.ui.AQI;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.weather_itw.Dist.Dist;
import com.example.weather_itw.R;
import com.example.weather_itw.Utils.SecondaryMemuUtils;
import com.example.weather_itw.databinding.FragmentAqiInfoBinding;

import java.util.ArrayList;
import java.util.List;

public class AQIFragment extends Fragment {
    private AQIViewModel aqiViewModel;
    private FragmentAqiInfoBinding binding;
    private List<String> listGroup = new ArrayList<>();
    private List<List<String>> listChild = new ArrayList<>();
    private ExpandableListView expandableListView;
    private SecondaryMemuUtils secondaryMemuUtils;
    private String[] arr = new String[2];
    private String location;
    private int selected;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        aqiViewModel =
                new ViewModelProvider(this).get(AQIViewModel.class);

        binding = FragmentAqiInfoBinding.inflate(inflater, container, false);
        binding.txtCounter.setVisibility(View.GONE);
        secondaryMemuUtils = new SecondaryMemuUtils(getContext(), expandableListView, listGroup, listChild, Dist.AQI_City, Dist.AQI_town);
        View root = binding.getRoot();

        aqiViewModel.getTitle().observe(getViewLifecycleOwner(), title -> {
            binding.pbLoading.setVisibility(View.VISIBLE);
            arr = title.split(",");
            location = arr[1];
            binding.txtCity.setText(title);         //設定地區
            selected = Dist.getAQI_Site(location);  //將選取到的地區轉換為JSON內第n筆資料
            binding.setSelected(selected);          //data binding selected
            aqiViewModel.setAQILiveData();          //檢查api資料是否為空
            callViewModel();                        //從api中獲取資料並更新ui
        });
        binding.txtCity.setOnClickListener(myListener);     //txtCity加上監聽事件(按下)
        binding.btnSelect.setOnClickListener(myListener);   //txtCity加上監聽事件(按下)
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    public void callViewModel() {
        aqiViewModel.getAQILiveData().observe(getViewLifecycleOwner(), aqi -> {
            if(aqi==null){
                Toast.makeText(getActivity(), "資料獲取失敗,請60秒後重新選取", Toast.LENGTH_SHORT).show();
                binding.txtCounter.setVisibility(View.VISIBLE);
                new CountDownTimer(60000,1000){
                    @Override
                    public void onTick(long millisUntilFinished) {
                        binding.txtCounter.setText(String.valueOf(millisUntilFinished/1000));
                        binding.txtCity.setClickable(false);
                        binding.btnSelect.setClickable(false);
                    }

                    @Override
                    public void onFinish() {
                        binding.txtCity.setClickable(true);
                        binding.btnSelect.setClickable(true);
                        binding.txtCounter.setVisibility(View.GONE);
                        Toast.makeText(getActivity(), "請再重新選取觀測站", Toast.LENGTH_SHORT).show();
                    }
                }.start();
            }
            else{
                Log.d("AQITAG","aqi : "+aqi.getRecords().get(selected).get_Pm25());
                binding.setAqiData(aqi);//data binding(各項數值)
                binding.vpPm25.setPM25_Progress(aqi.getRecords().get(selected).get_Pm25()); //pm2.5進度條(獲取數值再轉換)
                binding.vpPm10.setPM10_Progress(aqi.getRecords().get(selected).getPm10());  //pm10進度條(獲取數值再轉換)
                binding.vpSo2.setSO2_Progress(aqi.getRecords().get(selected).getSo2());     //So2進度條(獲取數值再轉換)
                binding.vpNo2.setNO2_Progress(aqi.getRecords().get(selected).getNo2());     //No2.5進度條(獲取數值再轉換)
                binding.vpO3.setO3_Progress(aqi.getRecords().get(selected).getO3());        //03進度條(獲取數值再轉換)
                binding.vpCo.setCO_Progress(aqi.getRecords().get(selected).getCo());        //Co進度條(獲取數值再轉換)
                binding.pbLoading.setVisibility(View.GONE);
            }

        });
    }

    private final View.OnClickListener myListener = new View.OnClickListener() {        //二層目錄,for AQI觀測站區域
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.txtCity:
                case R.id.btnSelect:
                    listChild.clear();
                    listGroup.clear();
                    secondaryMemuUtils.addDate(Dist.City);
                    secondaryMemuUtils.createExpandableListViewDialog(aqiViewModel);
            }
        }
    };
}
