package com.weather.forcast.controller;

import com.weather.forcast.model.Forecast;
import com.weather.forcast.service.NoaaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class ForcastController {

    @Autowired
    NoaaService service;

    public List<Forecast> createForcast(){

        List<Forecast> forecastList = new ArrayList<>();

        Map<String, Map<String, List<Map<String, String>>>> words = new HashMap<>();
        Map<String, Map<String, List<Map<String, Integer>>>> nums = new HashMap<>();
        Map<String, Map<String, List<Map<String, Map<String, Double>>>>> celciusDouble = new HashMap<>();
        Map<String, Map<String, List<Map<String, Map<String, Integer>>>>> intExtraMap = new HashMap<>();
        words = (Map<String, Map<String, List<Map<String, String>>>>) service.forcast();
        nums = (Map<String, Map<String, List<Map<String, Integer>>>>) service.forcast();
        celciusDouble = (Map<String, Map<String, List<Map<String, Map<String, Double>>>>>) service.forcast();
        intExtraMap = (Map<String, Map<String, List<Map<String, Map<String, Integer>>>>>) service.forcast();
        for (int i = 0; i < 14; i++) {
            Forecast forecastObject = new Forecast();
            forecastObject.setFcNumber(nums.get("properties").get("periods").get(i).get("number"));
            forecastObject.setName(words.get("properties").get("periods").get(i).get("name"));
            forecastObject.setTemperature(nums.get("properties").get("periods").get(i).get("temperature"));
            forecastObject.setTemperatureUnit(words.get("properties").get("periods").get(i).get("temperatureUnit"));
            forecastObject.setProbabilityOfPrecipitation(intExtraMap.get("properties").get("periods").get(i).get("probabilityOfPrecipitation").get("value"));
            double DewPointC = (celciusDouble.get("properties").get("periods").get(i).get("dewpoint").get("value"));
            int DewPointF = (int) ((DewPointC * 1.8) + 32); //Maybe find a better way to round this
            forecastObject.setDewPoint(DewPointF);
            forecastObject.setReletiveHumidity(intExtraMap.get("properties").get("periods").get(i).get("relativeHumidity").get("value"));
            forecastObject.setWindSpeed(words.get("properties").get("periods").get(i).get("windSpeed"));
            forecastObject.setWindDirection(words.get("properties").get("periods").get(i).get("windDirection"));
            forecastObject.setShortForcast(words.get("properties").get("periods").get(i).get("shortForecast"));
            forecastObject.setLongForcast(words.get("properties").get("periods").get(i).get("detailedForecast"));
            forecastList.add(forecastObject);
        }
        return forecastList;
    }

    @GetMapping("/forecast")
    public List<Forecast> getForecast(){
        List<Forecast> forecast = createForcast();
        return forecast;
    }

}
