package com.example.testApigateway;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.testApigateway.restClient.RestClient;



@RestController
public class TestController {
	
	@Autowired
	private RestClient restClient;
	
	public List<Integer> lista = Arrays.asList(1, 2, 3, 4, 5);

	@RequestMapping("/")
	public String index() {
		
		Logger logger = LoggerFactory.getLogger(TestController.class);
		restClient.clear();
		//restClient.setProxyHost("193.56.255.181"); // Singapore
		//restClient.setProxyPort(3128);
		restClient.setProxyHost("51.158.165.18");//Francia
		restClient.setProxyPort(8811);
		restClient.setRestTemplate(restClient.getTemplate());
		logger.info(testUrl());
		
		//------------
		restClient.clear();
		//restClient.setProxyHost("193.239.86.248"); // Hong Kong
		//restClient.setProxyPort(3128);
		restClient.setProxyHost("209.126.8.58"); // USA
		restClient.setProxyPort(3128);
		restClient.setRestTemplate(restClient.getTemplate());
		logger.info(testUrl());
		return "Greetings from Spring Boot!";

	}
	
	@RequestMapping("/asinc")
	public String indexAsinc() {
		
		lista.parallelStream().forEach(x->{
			Logger logger = LoggerFactory.getLogger(TestController.class);
			restClient.clear();
			//restClient.setProxyHost("193.56.255.181"); // Singapore
			//restClient.setProxyPort(3128);
			restClient.setProxyHost("51.158.165.18");//Francia
			restClient.setProxyPort(8811);
			restClient.setRestTemplate(restClient.getTemplate());
			logger.info(testUrl() + " -- thread ->"+x);
			
			//------------
			restClient.clear();
			//restClient.setProxyHost("193.239.86.248"); // Hong Kong
			//restClient.setProxyPort(3128);
			restClient.setProxyHost("209.126.8.58"); // USA
			restClient.setProxyPort(3128);
			restClient.setRestTemplate(restClient.getTemplate());
			logger.info(testUrl()+" -- thread ->"+x);
		});

		return "Greetings from Spring Boot!";

	}
	
	@RequestMapping("/sinc")
	public String indexSinc() {
		
		lista.stream().forEach(x->{
			Logger logger = LoggerFactory.getLogger(TestController.class);
			restClient.clear();
			//restClient.setProxyHost("193.56.255.181"); // Singapore
			//restClient.setProxyPort(3128);
			restClient.setProxyHost("51.158.165.18");//Francia
			restClient.setProxyPort(8811);
			restClient.setRestTemplate(restClient.getTemplate());
			logger.info(testUrl());
			
			//------------
			restClient.clear();
			//restClient.setProxyHost("193.239.86.248"); // Hong Kong
			//restClient.setProxyPort(3128);
			restClient.setProxyHost("209.126.8.58");
			restClient.setProxyPort(3128);
			restClient.setRestTemplate(restClient.getTemplate());
			logger.info(testUrl());
		});

		return "Greetings from Spring Boot!";

	}
	
	
	
	
	
	public String testUrl() {
		String url = "https://api.myip.com";
		
		try {
			ResponseEntity<?> isActiveResponse = restClient.executeGet(url);
			return isActiveResponse.getBody().toString();
		} catch (Exception e) {
			return e.getMessage();
		}
	}

}