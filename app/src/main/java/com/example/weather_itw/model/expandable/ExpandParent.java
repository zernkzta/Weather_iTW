package com.example.weather_itw.model.expandable;

import com.example.weather_itw.model.week.Location;

import java.util.ArrayList;

public class ExpandParent {
    private String wea_date;
    private String max_temp;
    private String min_temp;
    private int wea_img;
    private ArrayList<ExpandChild> expandChildArrayList = new ArrayList<>();

    public ExpandParent(String wea_date, String max_temp, String min_temp, int wea_img) {
        this.wea_date = wea_date;
        this.max_temp = max_temp;
        this.min_temp = min_temp;
        this.wea_img = wea_img;
    }

    public String getWea_date() {
        return wea_date;
    }

    public void setWea_date(String wea_date) {
        this.wea_date = wea_date;
    }

    public String getMax_temp() {
        return max_temp;
    }

    public void setMax_temp(String max_temp) {
        this.max_temp = max_temp;
    }

    public String getMin_temp() {
        return min_temp;
    }

    public void setMin_temp(String min_temp) {
        this.min_temp = min_temp;
    }

    public int getWea_img() {
        return wea_img;
    }

    public void setWea_img(int wea_img) {
        this.wea_img = wea_img;
    }

    public ArrayList<ExpandChild> getExpandChildArrayList() {
        return expandChildArrayList;
    }

    public void setExpandChildArrayList(ArrayList<ExpandChild> expandChildArrayList) {
        this.expandChildArrayList = expandChildArrayList;
    }


}
