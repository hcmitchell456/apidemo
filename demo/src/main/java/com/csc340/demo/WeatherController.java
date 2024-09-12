package com.csc340.demo;

import com.csc340.demo.WeatherResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/api")
public class WeatherController {

	@Value("${weatherapi.key}")
	private String apiKey;

	private final RestTemplate restTemplate;

	// Constructor-based injection of RestTemplate
	public WeatherController(RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
	}

	@GetMapping("/weather/{city}")
	public WeatherResponse getWeather(@PathVariable String city) {
		String url = "http://api.weatherapi.com/v1/current.json?key=" + apiKey + "&q=" + city;
		return restTemplate.getForObject(url, WeatherResponse.class);
	}
}
