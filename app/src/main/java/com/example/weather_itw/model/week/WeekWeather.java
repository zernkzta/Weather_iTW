package com.example.weather_itw.model.week;

import com.google.gson.annotations.SerializedName;

public class WeekWeather {

    @SerializedName("success")
    private String success;
    @SerializedName("result")
    private Result result;
    @SerializedName("records")
    private Records records;

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

    public Result getResult() {
        return result;
    }

    public void setResult(Result result) {
        this.result = result;
    }

    public Records getRecords() {
        return records;
    }

    public void setRecords(Records records) {
        this.records = records;
    }
}
