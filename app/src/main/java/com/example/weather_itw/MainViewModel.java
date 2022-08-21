package com.example.weather_itw;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class MainViewModel extends ViewModel {
    private MutableLiveData<String> title;

    public MainViewModel(){
        title = new MutableLiveData<>();
        title.setValue("宜蘭縣,蘇澳鎮");      //default toolbar
    }

    public MutableLiveData<String> getTitle() {
        return title;
    }
    public void setTitle(String value) {
        this.title.setValue(value);
    }


}
