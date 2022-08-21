package com.example.weather_itw.model.hour;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Records {
    @SerializedName("locations")
    private List<Locations> locations;

    public List<Locations> getLocations() {
        return locations;
    }

    public void setLocations(List<Locations> locations) {
        this.locations = locations;
    }
}
