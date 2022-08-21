package com.example.weather_itw.model.hour;

import com.google.gson.annotations.SerializedName;

public class Fields {
    @SerializedName("id")
    private String id;
    @SerializedName("type")
    private String type;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
