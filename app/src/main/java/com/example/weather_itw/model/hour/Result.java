package com.example.weather_itw.model.hour;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Result {
    @SerializedName("resource_id")
    private String resourceId;
    @SerializedName("fields")
    private List<Fields> fields;

    public String getResourceId() {
        return resourceId;
    }

    public void setResourceId(String resourceId) {
        this.resourceId = resourceId;
    }

    public List<Fields> getFields() {
        return fields;
    }

    public void setFields(List<Fields> fields) {
        this.fields = fields;
    }
}
