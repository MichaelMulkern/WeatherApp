package com.weather.forcast.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestClientResponseException;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;


@Service
public class NoaaService {

    //private String gridCoords = "gridpoints/TOP/71,90/forecast";
    //private String latLon= "points/42.360278,-71.057778";

    @Value("${API_KEY}")
    private String apiKey;

    //@Value("${API_URL}")
    //private String url;


    //private String getGrid = "https://api.weather.gov/points/42.360278,-71.057778";

    private RestTemplate template = new RestTemplate();

    public Map forcast(String gridX, String gridY, String officeId){
        String url = "https://api.weather.gov/gridpoints/" + officeId + "/" + gridX + "," + gridY + "/forecast";
        HttpHeaders headers = new HttpHeaders();

        Map weatherForcast = new HashMap<>();

        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(apiKey);
        HttpEntity<Void> entity = new HttpEntity<>(headers); //Maybe change void to input a DTO model later
        try {
            ResponseEntity<Map> response = template.exchange(url, HttpMethod.GET, entity, Map.class);
            weatherForcast = response.getBody();
        } catch (RestClientResponseException e) {
            System.out.println("bad connection" + e.getMessage());
        } catch (ResourceAccessException e) {
            System.out.println("RAE EXCEPTION");
        }
        return weatherForcast;

    }

    public Map locationData(String lat, String lon){
        String url = "https://api.weather.gov/points/" + lat + "," + lon;
        HttpHeaders headers = new HttpHeaders();

        Map locationInfo = new HashMap<>();

        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(apiKey);
        HttpEntity<Void> entity = new HttpEntity<>(headers);

        try {
            ResponseEntity <Map> response = template.exchange(url, HttpMethod.GET, entity, Map.class);
            locationInfo = response.getBody();
        } catch (RestClientResponseException e) {
            System.out.println("bad connection" + e.getMessage());
        } catch (ResourceAccessException e) {
            System.out.println("RAE EXCEPTION");
        }

        return locationInfo;
    }

    public Map hourly(String gridX, String gridY, String officeId){
        String url = "https://api.weather.gov/gridpoints/" + officeId + "/" + gridX + "," + gridY + "/forecast/hourly";
        HttpHeaders headers = new HttpHeaders();

        Map hourlyForecast = new HashMap<>();

        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(apiKey);
        HttpEntity<Void> entity = new HttpEntity<>(headers);
        try {
            ResponseEntity<Map> response = template.exchange(url, HttpMethod.GET, entity, Map.class);
            hourlyForecast = response.getBody();
        } catch (RestClientResponseException e) {
            System.out.println("bad connection" + e.getMessage());
        } catch (ResourceAccessException e) {
            System.out.println("RAE EXCEPTION");
        }
        return hourlyForecast;
    }
}
