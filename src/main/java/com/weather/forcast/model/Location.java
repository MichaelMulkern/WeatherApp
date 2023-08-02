package com.weather.forcast.model;

public class Location {

    private Integer gridX;
    private Integer gridY;
    private String city;
    private String state;
    private String stationId;

    public Location(Integer gridX, Integer gridY, String city, String state, String stationId) {
        this.gridX = gridX;
        this.gridY = gridY;
        this.city = city;
        this.state = state;
        this.stationId = stationId;
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

    public String getStationId() {
        return stationId;
    }

    public void setStationId(String stationId) {
        this.stationId = stationId;
    }
}
