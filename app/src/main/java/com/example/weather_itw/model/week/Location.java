package com.example.weather_itw.model.week;

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

    public String get_Week_Wx(int time){
        return this.weatherElement.get(3).getTime().get(time).getElementValue().get(0).getValue();
    }
    public String get_Week_PoP12h(int time){
        return this.weatherElement.get(0).getTime().get(time).getElementValue().get(0).getValue()+"%";
    }
    public String get_Week_MinT(int time){
        return this.weatherElement.get(4).getTime().get(time).getElementValue().get(0).getValue();
    }
    public String get_Week_MaxT(int time){
        return this.weatherElement.get(5).getTime().get(time).getElementValue().get(0).getValue();
    }
    public String get_Week_WS(int time){
        return this.weatherElement.get(2).getTime().get(time).getElementValue().get(0).getValue()+"公尺/秒";
    }
    public String get_Week_WD(int time){
        return this.weatherElement.get(6).getTime().get(time).getElementValue().get(0).getValue();
    }
    public String get_Week_RH(int time){
        return this.weatherElement.get(1).getTime().get(time).getElementValue().get(0).getValue()+"%";
    }
    public String get_Week_date(int time) {
        StringBuilder sb = new StringBuilder();
        String date = this.weatherElement.get(0)
                .getTime().get(time).getStartTime();
        //sb.append(date);
        for(int i =0;i<date.length();i++){
            if(date.charAt(i)==' '){
                break;
            }
            sb.append(date.charAt(i));
        }
        return sb.toString();
    }



}
