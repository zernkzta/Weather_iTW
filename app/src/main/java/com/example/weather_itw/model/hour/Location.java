package com.example.weather_itw.model.hour;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Location {
    @SerializedName("locationName")
    private String locationName;
    @SerializedName("geocode")
    private String geocode;
    @SerializedName("lat")
    private String lat;
    @SerializedName("lon")
    private String lon;
    @SerializedName("weatherElement")
    private List<WeatherElement> weatherElement;

    public String getLocationName() {
        return locationName;
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }

    public String getGeocode() {
        return geocode;
    }

    public void setGeocode(String geocode) {
        this.geocode = geocode;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLon() {
        return lon;
    }

    public void setLon(String lon) {
        this.lon = lon;
    }

    public List<WeatherElement> getWeatherElement() {
        return weatherElement;
    }

    public void setWeatherElement(List<WeatherElement> weatherElement) {
        this.weatherElement = weatherElement;
    }
    public String get_Hour_T(){
        return this.weatherElement.get(1).getTime().get(0).getElementValue().get(0).getValue()+"℃";
    }
    public String get_Hour_T(int selected){
        return this.weatherElement.get(1).getTime().get(selected).getElementValue().get(0).getValue()+"℃";
    }
    public String get_Hour_Wx(){
        return this.weatherElement.get(0).getTime().get(0).getElementValue().get(0).getValue();
    }
    public String get_Hour_Wx(int selected){
        return this.weatherElement.get(0).getTime().get(selected).getElementValue().get(0).getValue();
    }
    public String get_Hour_PoP6h(){
        return this.weatherElement.get(4).getTime().get(0).getElementValue().get(0).getValue()+"%";
    }
    public String get_Hour_PoP6h(int selected){
        return this.weatherElement.get(4).getTime().get(selected).getElementValue().get(0).getValue()+"%";
    }
    public String get_Hour_WD(){
        return this.weatherElement.get(6).getTime().get(0).getElementValue().get(0).getValue();
    }
    public String get_Hour_WD(int selected){
        return this.weatherElement.get(6).getTime().get(selected).getElementValue().get(0).getValue();
    }
    public String get_Hour_WS(){
        return this.weatherElement.get(5).getTime().get(0).getElementValue().get(0).getValue()+"公尺/秒";
    }
    public String get_Hour_WS(int selected){
        return this.weatherElement.get(5).getTime().get(selected).getElementValue().get(0).getValue()+"公尺/秒";
    }
    public String get_Hour_WDesc(){
        return this.weatherElement.get(3).getTime().get(0).getElementValue().get(0).getValue();
    }
    public String get_Hour_WDesc(int selected){
        return this.weatherElement.get(3).getTime().get(selected).getElementValue().get(0).getValue();
    }
    public String get_Hour_RH(){
        return this.weatherElement.get(2).getTime().get(0).getElementValue().get(0).getValue()+"%";
    }
    public String get_Hour_RH(int selected){
        return this.weatherElement.get(2).getTime().get(selected).getElementValue().get(0).getValue()+"%";
    }
}
