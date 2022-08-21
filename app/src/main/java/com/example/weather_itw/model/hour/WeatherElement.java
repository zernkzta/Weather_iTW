package com.example.weather_itw.model.hour;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class WeatherElement {
    @SerializedName("elementName")
    private String elementName;
    @SerializedName("description")
    private String description;
    @SerializedName("time")
    private List<Time> time;

    public String getElementName() {
        return elementName;
    }

    public void setElementName(String elementName) {
        this.elementName = elementName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Time> getTime() {
        return time;
    }

    public void setTime(List<Time> time) {
        this.time = time;
    }
}
