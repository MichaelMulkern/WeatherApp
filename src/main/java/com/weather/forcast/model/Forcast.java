package com.weather.forcast.model;

public class Forcast {

    private String name;
    private int temperature;

    public Forcast(String name, int temperature) {
        this.name = name;
        this.temperature = temperature;
    }

    public Forcast() {
    }

    public String getName() {
        return name;
    }

    public int getTemperature() {
        return temperature;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setTemperature(int temperature) {
        this.temperature = temperature;
    }
}
