package com.weather.forcast.controller;

import com.weather.forcast.model.Forecast;
import com.weather.forcast.model.Location;
import com.weather.forcast.service.NoaaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins= "http://localhost:8080")
@RestController
public class ForecastController {

    @Autowired
    NoaaService service;

    private String gridX;
    private String gridY;

    private String lat = "40.360278";
    private String lon = "-81.057778";

    public List<Forecast> createForecast(String gridX, String gridY){

        List<Forecast> forecastList = new ArrayList<>();

        Map<String, Map<String, List<Map<String, String>>>> words = new HashMap<>();
        Map<String, Map<String, List<Map<String, Integer>>>> nums = new HashMap<>();
        Map<String, Map<String, List<Map<String, Map<String, Double>>>>> celciusDouble = new HashMap<>();
        Map<String, Map<String, List<Map<String, Map<String, Integer>>>>> intExtraMap = new HashMap<>();

        words = (Map<String, Map<String, List<Map<String, String>>>>) service.forcast(gridX, gridY);
        nums = (Map<String, Map<String, List<Map<String, Integer>>>>) service.forcast(gridX, gridY);
        celciusDouble = (Map<String, Map<String, List<Map<String, Map<String, Double>>>>>) service.forcast(gridX, gridY);
        intExtraMap = (Map<String, Map<String, List<Map<String, Map<String, Integer>>>>>) service.forcast(gridX, gridY);

        for (int i = 0; i < 14; i++) {
            Forecast forecastObject = new Forecast();
            forecastObject.setFcNumber(nums.get("properties").get("periods").get(i).get("number"));
            forecastObject.setName(words.get("properties").get("periods").get(i).get("name"));
            forecastObject.setTemperature(nums.get("properties").get("periods").get(i).get("temperature"));
            forecastObject.setTemperatureUnit(words.get("properties").get("periods").get(i).get("temperatureUnit"));
            forecastObject.setProbabilityOfPrecipitation(intExtraMap.get("properties").get("periods").get(i).get("probabilityOfPrecipitation").get("value"));
            double DewPointC = Double.parseDouble(String.valueOf(celciusDouble.get("properties").get("periods").get(i).get("dewpoint").get("value")));
            int DewPointF = (int) ((DewPointC * 1.8) + 32); //Maybe find a better way to round this
            forecastObject.setDewPoint(DewPointF);
            forecastObject.setRelativeHumidity(intExtraMap.get("properties").get("periods").get(i).get("relativeHumidity").get("value"));
            forecastObject.setWindSpeed(words.get("properties").get("periods").get(i).get("windSpeed"));
            forecastObject.setWindDirection(words.get("properties").get("periods").get(i).get("windDirection"));
            forecastObject.setShortForecast(words.get("properties").get("periods").get(i).get("shortForecast"));
            forecastObject.setLongForecast(words.get("properties").get("periods").get(i).get("detailedForecast"));
            forecastObject.setImageKey(imageKeyFinder(forecastObject));
            forecastList.add(forecastObject);
        }
        return forecastList;
    }

    public Location getLocationData(String lat, String lon) {
        Location location = new Location();
        Map<String, Map<String,Integer>> nums = new HashMap<>();
        Map<String, Map<String,Map<String, Map<String, String>>>> words = new HashMap<>();

        nums = (Map<String, Map<String,Integer>>) service.locationData(lat, lon);
        words = (Map<String, Map<String,Map<String, Map<String, String>>>>) service.locationData(lat, lon);

        location.setGridX(nums.get("properties").get("gridX"));
        location.setGridY(nums.get("properties").get("gridY"));
        location.setCity(words.get("properties").get("relativeLocation").get("properties").get("city"));
        location.setState(words.get("properties").get("relativeLocation").get("properties").get("state"));
        return location;
    }

    @GetMapping("/forecast")
    public List<Forecast> getForecast(){
        List<Forecast> forecast = createForecast(gridX, gridY);
        return forecast;
    }

    @GetMapping("/location")
    public Location getLocation() {
        Location locationData = getLocationData(lat,lon);
        gridX = String.valueOf(locationData.getGridX());
        gridY = String.valueOf(locationData.getGridY());
        return locationData;
    }

    public String imageKeyFinder(Forecast forecast){
        String imageKey = "";
        int precipitation = 0;
        //String shortForecast = forecast.getShortForecast().toLowerCase();
        String longForecast = forecast.getLongForecast().toLowerCase();
        if(forecast.getProbabilityOfPrecipitation() != null) {
            precipitation = forecast.getProbabilityOfPrecipitation();
        }else {
            precipitation = 0;
        }
        if(precipitation > 50 && (longForecast.matches("(.*)thunder(.*)") || longForecast.matches("(.*)lightning(.*)"))){
            imageKey = "lightning";
        } else if(precipitation > 50 && (longForecast.matches("(.*)shower(.*)") || longForecast.matches("(.*)rain(.*)"))){
            imageKey = "rain";
        } else if (longForecast.matches("(.*)sun(.*)") || longForecast.matches("(.*)clear(.*)")) {
            imageKey = "sunny";
        } else if (longForecast.matches("(.*)cloudy(.*)") || longForecast.matches("(.*)clouds(.*)")) {
            imageKey = "cloudy";
        } else{
          imageKey = "default";
        }
        return imageKey;
    }

}
