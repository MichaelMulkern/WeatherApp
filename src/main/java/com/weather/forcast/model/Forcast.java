package com.weather.forcast.model;

public class Forcast {

    private String name;
    private String temperatureUnit;
    private String windSpeed;
    private String windDirection;
    private int fcNumber;
    private int temperature;
    private Integer probabilityOfPrecipitation;
    private int reletiveHumidity;
    private int dewPoint; //In degrees C, maybe do a conversion or something and instantiate it as int
    private String shortForcast;
    private String longForcast;

    //Constructors


    public Forcast(String name, String temperatureUnit, String windSpeed, String windDirection, int fcNumber, int temperature, Integer probabilityOfPrecipitation, int reletiveHumidity, int dewPoint, String shortForcast, String longForcast) {
        this.name = name;
        this.temperatureUnit = temperatureUnit;
        this.windSpeed = windSpeed;
        this.windDirection = windDirection;
        this.fcNumber = fcNumber;
        this.temperature = temperature;
        this.probabilityOfPrecipitation = probabilityOfPrecipitation;
        this.reletiveHumidity = reletiveHumidity;
        this.dewPoint = dewPoint;
        this.shortForcast = shortForcast;
        this.longForcast = longForcast;
    }

    public Forcast() {
    }

    //------G&S-----------------

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTemperatureUnit() {
        return temperatureUnit;
    }

    public void setTemperatureUnit(String temperatureUnit) {
        this.temperatureUnit = temperatureUnit;
    }

    public String getWindSpeed() {
        return windSpeed;
    }

    public void setWindSpeed(String windSpeed) {
        this.windSpeed = windSpeed;
    }

    public String getWindDirection() {
        return windDirection;
    }

    public void setWindDirection(String windDirection) {
        this.windDirection = windDirection;
    }

    public int getFcNumber() {
        return fcNumber;
    }

    public void setFcNumber(int fcNumber) {
        this.fcNumber = fcNumber;
    }

    public int getTemperature() {
        return temperature;
    }

    public void setTemperature(int temperature) {
        this.temperature = temperature;
    }

    public Integer getProbabilityOfPrecipitation() {
        return probabilityOfPrecipitation;
    }

    public void setProbabilityOfPrecipitation(Integer probabilityOfPrecipitation) {
        this.probabilityOfPrecipitation = probabilityOfPrecipitation;
    }

    public int getReletiveHumidity() {
        return reletiveHumidity;
    }

    public void setReletiveHumidity(int reletiveHumidity) {
        this.reletiveHumidity = reletiveHumidity;
    }

    public int getDewPoint() {
        return dewPoint;
    }

    public void setDewPoint(int dewPoint) {
        this.dewPoint = dewPoint;
    }

    public String getShortForcast() {
        return shortForcast;
    }

    public void setShortForcast(String shortForcast) {
        this.shortForcast = shortForcast;
    }

    public String getLongForcast() {
        return longForcast;
    }

    public void setLongForcast(String longForcast) {
        this.longForcast = longForcast;
    }
}