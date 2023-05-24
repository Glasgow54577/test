package com.example.RestApp2Client;

import jakarta.validation.OverridesAttribute;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@SpringBootApplication
public class RestApp2ClientApplication {

    public static void main(String[] args) {

        final String sensorName = "SensorTest10";

        registerSensor(sensorName); // добавляет сенсор

        Random random = new Random(); //добавляет 10 измерений
        for (int i = 0; i < 10; i++){
            System.out.println(i);
            sendMeasurement((random.nextInt(100) - random.nextInt(100) ),
            random.nextBoolean(), sensorName);
        }

        getMeasurement(); //выводит измерения
	}
    private static void registerSensor(String sensorName) {
        final String url = "http://localhost:8091/sensor/registration";
        Map<String, Object> jsonData = new HashMap<>();
        jsonData.put("name", sensorName);
        makePostRequestWithJSONData (url, jsonData);
    }

    private static void sendMeasurement(int value, boolean raining, String sensorName) {
        final String url = "http://localhost:8091/measurements/add";
        Map<String, Object> jsonData = new HashMap<>();
        jsonData.put("value", value);
        jsonData.put("raining", raining);
        jsonData.put("sensor", Map.of("name", sensorName ));

        makePostRequestWithJSONData (url, jsonData);
    }

    private static void getMeasurement(){
        final RestTemplate restTemplate = new RestTemplate();
        final String url = "http://localhost:8091/measurements";
        String response = restTemplate.getForObject(url, String.class);
System.out.println(response);
    }

    private static void makePostRequestWithJSONData(String url, Map<String, Object> jsonData) {
       final RestTemplate restTemplate = new RestTemplate();

       final HttpHeaders headers = new HttpHeaders();
       headers.setContentType(MediaType.APPLICATION_JSON);

       HttpEntity<Object> request = new HttpEntity<>(jsonData, headers);

       try {
           restTemplate.postForObject(url, request, String.class);

           System.out.println("Измерение успешно отправлено");
       } catch (HttpClientErrorException e){
           System.out.println("ошибка");
           System.out.println(e.getMessage());
       }
    }




}
