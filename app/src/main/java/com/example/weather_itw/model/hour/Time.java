package com.example.weather_itw.model.hour;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Time {
    @SerializedName("startTime")
    private String startTime;
    @SerializedName("endTime")
    private String endTime;
    @SerializedName("elementValue")
    private List<ElementValue> elementValue;

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public List<ElementValue> getElementValue() {
        return elementValue;
    }

    public void setElementValue(List<ElementValue> elementValue) {
        this.elementValue = elementValue;
    }
}
