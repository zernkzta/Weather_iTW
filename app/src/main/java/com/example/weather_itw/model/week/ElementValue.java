package com.example.weather_itw.model.week;

import com.google.gson.annotations.SerializedName;

public class ElementValue {
    @SerializedName("value")
    private String value;
    @SerializedName("measures")
    private String measures;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getMeasures() {
        return measures;
    }

    public void setMeasures(String measures) {
        this.measures = measures;
    }
}
