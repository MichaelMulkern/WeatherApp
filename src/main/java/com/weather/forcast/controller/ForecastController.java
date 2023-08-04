package com.weather.forcast.controller;

import com.weather.forcast.model.Forecast;
import com.weather.forcast.model.Hourly;
import com.weather.forcast.model.Location;
import com.weather.forcast.service.NoaaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

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
    private String officeId;

    //private String lat = "40.360278";
    //private String lon = "-81.057778";

    public List<Forecast> createForecast(String gridX, String gridY, String officeId){

        List<Forecast> forecastList = new ArrayList<>();

        Map<String, Map<String, List<Map<String, String>>>> words = new HashMap<>();
        Map<String, Map<String, List<Map<String, Integer>>>> nums = new HashMap<>();
        Map<String, Map<String, List<Map<String, Map<String, Double>>>>> celciusDouble = new HashMap<>();
        Map<String, Map<String, List<Map<String, Map<String, Integer>>>>> intExtraMap = new HashMap<>();

        words = (Map<String, Map<String, List<Map<String, String>>>>) service.forcast(gridX, gridY, officeId);
        nums = (Map<String, Map<String, List<Map<String, Integer>>>>) service.forcast(gridX, gridY, officeId);
        celciusDouble = (Map<String, Map<String, List<Map<String, Map<String, Double>>>>>) service.forcast(gridX, gridY, officeId);
        intExtraMap = (Map<String, Map<String, List<Map<String, Map<String, Integer>>>>>) service.forcast(gridX, gridY, officeId);

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
        Map<String, Map<String,String>> idString = new HashMap<>();
        Map<String, Map<String,Map<String, Map<String, String>>>> words = new HashMap<>();

        nums = (Map<String, Map<String,Integer>>) service.locationData(lat, lon);
        idString = (Map<String, Map<String,String>>) service.locationData(lat, lon);
        words = (Map<String, Map<String,Map<String, Map<String, String>>>>) service.locationData(lat, lon);

        location.setGridX(nums.get("properties").get("gridX"));
        location.setGridY(nums.get("properties").get("gridY"));
        location.setStationId(idString.get("properties").get("gridId"));
        location.setCity(words.get("properties").get("relativeLocation").get("properties").get("city"));
        location.setState(words.get("properties").get("relativeLocation").get("properties").get("state"));
        return location;
    }

    public List<Hourly> createHourly(String gridX, String gridY, String officeId){
        List<Hourly> hourlyList = new ArrayList<>();

        Map<String, Map<String, List<Map<String, String>>>> words = new HashMap<>();
        Map<String, Map<String, List<Map<String, Integer>>>> nums = new HashMap<>();
        Map<String, Map<String, List<Map<String, Boolean>>>> bools = new HashMap<>();

        words = (Map<String, Map<String, List<Map<String, String>>>>) service.hourly(gridX, gridY, officeId);
        nums = (Map<String, Map<String, List<Map<String, Integer>>>>) service.hourly(gridX, gridY, officeId);
        bools = (Map<String, Map<String, List<Map<String, Boolean>>>>) service.hourly(gridX, gridY, officeId);

        for (int i = 0; i < 24; i++) {
            //String timeString = "";
            Hourly hourlyObject = new Hourly();
            hourlyObject.setDaytime(bools.get("properties").get("periods").get(i).get("isDaytime"));
            hourlyObject.setShortForecast(words.get("properties").get("periods").get(i).get("shortForecast"));
            String timeString = words.get("properties").get("periods").get(i).get("startTime");
            hourlyObject.setStartTime(timeString.substring(11,16));
            hourlyObject.setTemperature(nums.get("properties").get("periods").get(i).get("temperature"));
            hourlyObject.setImageKey(hourlyImageKey(hourlyObject));
            hourlyList.add(hourlyObject);
        }

        return hourlyList;
    }

    @GetMapping("/forecast")
    @ResponseStatus(HttpStatus.OK)
    public List<Forecast> getForecast(){
        List<Forecast> forecast = createForecast(gridX, gridY, officeId);
        return forecast;
    }

    @GetMapping("/location/{lat}/{lon}")
    @ResponseStatus(HttpStatus.OK)
    public Location getLocation(@PathVariable("lat") String lat, @PathVariable("lon") String lon) {
        Location locationData = getLocationData(lat,lon);
        gridX = String.valueOf(locationData.getGridX());
        gridY = String.valueOf(locationData.getGridY());
        officeId = locationData.getStationId();
        return locationData;
    }

    @GetMapping("/hourly")
    @ResponseStatus(HttpStatus.OK)
    public List<Hourly> getHourly(){
      List<Hourly> hourly = createHourly(gridX, gridY, officeId);
      return hourly;
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

    public String hourlyImageKey(Hourly hourly){
        String imageKey = "";
        String shortForecast = hourly.getShortForecast().toLowerCase();
        if(shortForecast.contains("thunder") || shortForecast.contains("loghtning")){
            imageKey = "lightning";
        }else if (shortForecast.contains("shower") || shortForecast.contains("rain")){
            imageKey = "rain";
        }else if (shortForecast.contains("sun") || shortForecast.contains("clear")){
            imageKey = "sunny";
        }else if (shortForecast.contains("cloudy") || shortForecast.contains("clouds")){
            imageKey = "cloudy";
        }else{
            imageKey = "default";
        }
        return imageKey;
    }

}
