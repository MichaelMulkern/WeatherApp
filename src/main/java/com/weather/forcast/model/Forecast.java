package com.weather.forcast.model;

public class Forecast {

    private String name;
    private String temperatureUnit;
    private String windSpeed;
    private String windDirection;
    private Integer fcNumber;
    private Integer temperature;
    private Integer probabilityOfPrecipitation;
    private Integer relativeHumidity;
    private Integer dewPoint; //In degrees C, maybe do a conversion or something and instantiate it as int
    private String shortForecast;
    private String longForecast;

    //Constructors


    public Forecast(String name, String temperatureUnit, String windSpeed, String windDirection, Integer fcNumber, Integer temperature, Integer probabilityOfPrecipitation, Integer relativeHumidity, Integer dewPoint, String shortForecast, String longForecast) {
        this.name = name;
        this.temperatureUnit = temperatureUnit;
        this.windSpeed = windSpeed;
        this.windDirection = windDirection;
        this.fcNumber = fcNumber;
        this.temperature = temperature;
        this.probabilityOfPrecipitation = probabilityOfPrecipitation;
        this.relativeHumidity = relativeHumidity;
        this.dewPoint = dewPoint;
        this.shortForecast = shortForecast;
        this.longForecast = longForecast;
    }

    public Forecast() {
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

    public Integer getFcNumber() {
        return fcNumber;
    }

    public void setFcNumber(Integer fcNumber) {
        this.fcNumber = fcNumber;
    }

    public Integer getTemperature() {
        return temperature;
    }

    public void setTemperature(Integer temperature) {
        this.temperature = temperature;
    }

    public Integer getProbabilityOfPrecipitation() {
        return probabilityOfPrecipitation;
    }

    public void setProbabilityOfPrecipitation(Integer probabilityOfPrecipitation) {
        this.probabilityOfPrecipitation = probabilityOfPrecipitation;
    }

    public Integer getRelativeHumidity() {
        return relativeHumidity;
    }

    public void setRelativeHumidity(Integer relativeHumidity) {
        this.relativeHumidity = relativeHumidity;
    }

    public Integer getDewPoint() {
        return dewPoint;
    }

    public void setDewPoint(Integer dewPoint) {
        this.dewPoint = dewPoint;
    }

    public String getShortForecast() {
        return shortForecast;
    }

    public void setShortForecast(String shortForecast) {
        this.shortForecast = shortForecast;
    }

    public String getLongForecast() {
        return longForecast;
    }

    public void setLongForecast(String longForecast) {
        this.longForecast = longForecast;
    }
}