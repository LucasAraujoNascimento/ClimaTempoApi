package com.nascimento.api_clima.services;

import com.nascimento.api_clima.models.WeatherResponseModel;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Service
public class WeatherService {
    @Value("${weather.api.key}")
    private String apiKey;

    private final String apiUrl = "http://api.weatherapi.com/v1/current.json";

    private final RestTemplate restTemplate;

    public WeatherService(RestTemplate restTemplate){
        this.restTemplate = restTemplate;
    }

    public WeatherResponseModel getWeather(String cidade) {
        String url = UriComponentsBuilder.fromUriString(apiUrl)
                .queryParam("key", apiKey)
                .queryParam("q", cidade)
                .toUriString();

        return restTemplate.getForObject(url, WeatherResponseModel.class);
    }


}
