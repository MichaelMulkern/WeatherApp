package com.weather.forcast.controller;

import com.weather.forcast.model.Forcast;
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

    public Forcast createForcast(){
        Forcast forcastObject = new Forcast();
        Map<String, Map<String, List<Map<String, String>>>> words = new HashMap<>();
        Map<String, Map<String, List<Map<String, Integer>>>> nums = new HashMap<>();
        Map<String, Map<String, List<Map<String, Map<String, Double>>>>> celciusDouble = new HashMap<>();
        Map<String, Map<String, List<Map<String, Map<String, Integer>>>>> intExtraMap = new HashMap<>();
        words = (Map<String, Map<String, List<Map<String, String>>>>) service.forcast();
        nums = (Map<String, Map<String, List<Map<String, Integer>>>>) service.forcast();
        celciusDouble = (Map<String, Map<String, List<Map<String, Map<String, Double>>>>>) service.forcast();
        intExtraMap = (Map<String, Map<String, List<Map<String, Map<String, Integer>>>>>) service.forcast();

        forcastObject.setFcNumber(nums.get("properties").get("periods").get(0).get("number"));
        forcastObject.setName(words.get("properties").get("periods").get(0).get("name"));
        forcastObject.setTemperature(nums.get("properties").get("periods").get(0).get("temperature"));
        forcastObject.setTemperatureUnit(words.get("properties").get("periods").get(0).get("temperatureUnit"));
        forcastObject.setProbabilityOfPrecipitation(intExtraMap.get("properties").get("periods").get(0).get("probabilityOfPrecipitation").get("value"));
        double DewPointC = (celciusDouble.get("properties").get("periods").get(0).get("dewpoint").get("value"));
        int DewPointF = (int) ((DewPointC * 1.8) + 32); //Maybe find a better way to round this
        forcastObject.setDewPoint(DewPointF);
        forcastObject.setReletiveHumidity(intExtraMap.get("properties").get("periods").get(0).get("relativeHumidity").get("value"));
        forcastObject.setWindSpeed(words.get("properties").get("periods").get(0).get("windSpeed"));
        forcastObject.setWindDirection(words.get("properties").get("periods").get(0).get("windDirection"));
        forcastObject.setShortForcast(words.get("properties").get("periods").get(0).get("shortForecast"));
        forcastObject.setLongForcast(words.get("properties").get("periods").get(0).get("longForecast"));
        return forcastObject;
    }

    @GetMapping("/poop")
    public Forcast poopTest(){
        Forcast ccc = createForcast();
        return ccc;
    }
    @GetMapping("/forcast")
    public String[] getForcast(){
        Forcast formattedWeather = new Forcast();
        Map<String, Map<String, List<Map<String, String>>>> properties = new HashMap<>();
        properties = (Map<String, Map<String, List<Map<String, String>>>>) service.forcast();
        String[] dayz = new String[14];
        dayz[0] = properties.get("properties").get("periods").get(0).get("name");
        dayz[1] = properties.get("properties").get("periods").get(1).get("name");
        dayz[2] = properties.get("properties").get("periods").get(2).get("name");
        dayz[3] = properties.get("properties").get("periods").get(3).get("name");
        dayz[4] = properties.get("properties").get("periods").get(4).get("name");
        dayz[5] = properties.get("properties").get("periods").get(5).get("name");
        dayz[6] = properties.get("properties").get("periods").get(6).get("name");
        dayz[7] = properties.get("properties").get("periods").get(7).get("name");
        dayz[8] = properties.get("properties").get("periods").get(8).get("name");
        dayz[9] = properties.get("properties").get("periods").get(9).get("name");
        dayz[10] = properties.get("properties").get("periods").get(10).get("name");
        dayz[11] = properties.get("properties").get("periods").get(11).get("name");
        dayz[12] = properties.get("properties").get("periods").get(12).get("name");
        dayz[13] = properties.get("properties").get("periods").get(13).get("name");


        //formattedWeather.setName(properties.get("properties").get("periods").get(0).get("name"));
        //formattedWeather.setTemperature(Integer.parseInt(properties.get("properties").get("periods").get(0).get("temperature")));
        //String nam = formattedWeather.getName();
        return dayz;
    }
}
