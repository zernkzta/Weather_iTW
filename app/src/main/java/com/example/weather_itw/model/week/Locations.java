package com.example.weather_itw.model.week;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Locations {
    @SerializedName("datasetDescription")
    private String datasetDescription;
    @SerializedName("locationsName")
    private String locationsName;
    @SerializedName("dataid")
    private String dataid;
    @SerializedName("location")
    private List<Location> location;

    public String getDatasetDescription() {
        return datasetDescription;
    }

    public void setDatasetDescription(String datasetDescription) {
        this.datasetDescription = datasetDescription;
    }

    public String getLocationsName() {
        return locationsName;
    }

    public void setLocationsName(String locationsName) {
        this.locationsName = locationsName;
    }

    public String getDataid() {
        return dataid;
    }

    public void setDataid(String dataid) {
        this.dataid = dataid;
    }

    public List<Location> getLocation() {
        return location;
    }

    public void setLocation(List<Location> location) {
        this.location = location;
    }
}
