package com.weather.forcast.model;

public class Location {

    private Integer gridX;
    private Integer gridY;
    private String city;
    private String state;

    public Location(Integer gridX, Integer gridY, String city, String state) {
        this.gridX = gridX;
        this.gridY = gridY;
        this.city = city;
        this.state = state;
    }

    public Location() {
    }

    public Integer getGridX() {
        return gridX;
    }

    public void setGridX(Integer gridX) {
        this.gridX = gridX;
    }

    public Integer getGridY() {
        return gridY;
    }

    public void setGridY(Integer gridY) {
        this.gridY = gridY;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
