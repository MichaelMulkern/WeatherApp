package com.weather.forcast.model;

public class Forecast {

    private String name;
    private String temperatureUnit;
    private String windSpeed;
    private String windDirection;
    private Integer fcNumber;
    private Integer temperature;
    private Integer probabilityOfPrecipitation;
    private Integer reletiveHumidity;
    private Integer dewPoint; //In degrees C, maybe do a conversion or something and instantiate it as int
    private String shortForcast;
    private String longForcast;

    //Constructors


    public Forecast(String name, String temperatureUnit, String windSpeed, String windDirection, Integer fcNumber, Integer temperature, Integer probabilityOfPrecipitation, Integer reletiveHumidity, Integer dewPoint, String shortForcast, String longForcast) {
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

    public Integer getReletiveHumidity() {
        return reletiveHumidity;
    }

    public void setReletiveHumidity(Integer reletiveHumidity) {
        this.reletiveHumidity = reletiveHumidity;
    }

    public Integer getDewPoint() {
        return dewPoint;
    }

    public void setDewPoint(Integer dewPoint) {
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