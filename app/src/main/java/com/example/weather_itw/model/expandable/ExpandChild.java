package com.example.weather_itw.model.expandable;

public class ExpandChild {
    private String humi;
    private String pop;
    private String wdirection;
    private String wspeed;

    public ExpandChild(String humi, String pop, String wdirection, String wspeed) {
        this.humi = humi;
        this.pop = pop;
        this.wdirection = wdirection;
        this.wspeed = wspeed;
    }

    public String getHumi() {
        return humi;
    }

    public void setHumi(String humi) {
        this.humi = humi;
    }

    public String getPop() {
        return pop;
    }

    public void setPop(String pop) {
        this.pop = pop;
    }

    public String getWdirection() {
        return wdirection;
    }

    public void setWdirection(String wdirection) {
        this.wdirection = wdirection;
    }

    public String getWspeed() {
        return wspeed;
    }

    public void setWspeed(String wspeed) {
        this.wspeed = wspeed;
    }
}
