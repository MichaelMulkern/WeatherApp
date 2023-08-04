package com.weather.forcast.model;

public class Hourly{

    private Integer temperature;
    private String shortForecast;
    private Boolean isDaytime;
    private String startTime;
    private String imageKey;

    //-------Constructors------------
    public Hourly(Integer temperature, String shortForecast, Boolean isDaytime, String startTime, String imageKey) {
        this.temperature = temperature;
        this.shortForecast = shortForecast;
        this.isDaytime = isDaytime;
        this.startTime = startTime;
        this.imageKey = imageKey;
    }

    public Hourly() {
    }

    //------------G&S----------------

    public Integer getTemperature() {
        return temperature;
    }

    public void setTemperature(Integer temperature) {
        this.temperature = temperature;
    }

    public String getShortForecast() {
        return shortForecast;
    }

    public void setShortForecast(String shortForecast) {
        this.shortForecast = shortForecast;
    }

    public Boolean getDaytime() {
        return isDaytime;
    }

    public void setDaytime(Boolean daytime) {
        isDaytime = daytime;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getImageKey() {
        return imageKey;
    }
    public void setImageKey(String imageKey) {
        this.imageKey = imageKey;
    }
}
