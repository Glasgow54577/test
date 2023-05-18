package org.example.KursachP;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class KursachPApplication {

	public static void main(String[] args) {
		SpringApplication.run(KursachPApplication.class, args);
//		RestTemplate restTemplate = new RestTemplate();
//
////		String url = "https://reqres.in/api/users/2";
//		String url = "http://localhost:8090/view/products?";
//		String response = restTemplate.getForObject(url, String.class);
//
//		System.out.println(response);
	}

}
