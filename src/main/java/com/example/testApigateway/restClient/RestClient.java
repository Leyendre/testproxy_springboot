package com.example.testApigateway.restClient;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

@Component
public class RestClient extends RestClientBase {


	public RestTemplate restTemplate = getTemplate();
	
	public RestClient() {
	}
	
	public void setRestTemplate(RestTemplate resTemplate) {
		this.restTemplate = (resTemplate!= null)?resTemplate:getTemplate();
	}

	@SuppressWarnings("rawtypes")
	private ResponseEntity<?> execute(String url, HttpMethod method, HttpEntity requestEntity) throws RestClientException {
		return restTemplate.exchange(url, method, requestEntity, String.class);
	}
	
	@SuppressWarnings("rawtypes")
	private HttpEntity getRequestEntity(Map<String, String> headers, Map<String, String> body) {
		HttpHeaders validHeaders = getHttpHeaders(headers);
		MultiValueMap<String, String> validBody = new LinkedMultiValueMap<String, String>();
		
		if (body != null) {
			for (Map.Entry<String, String> bd : body.entrySet()) {
				validBody.add(bd.getKey(), bd.getValue());
			}
		}
		return new HttpEntity<>(validBody, validHeaders);
	}
	
	@SuppressWarnings("rawtypes")
	private HttpEntity getRequestEntity(Map<String, String> headers, String body) {
		HttpHeaders validHeaders = getHttpHeaders(headers);
		return new HttpEntity<>(body, validHeaders);
	}
	
	@SuppressWarnings("rawtypes")
	private HttpEntity getRequestEntity(Map<String, String> headers) {
		HttpHeaders validHeaders = getHttpHeaders(headers);
		return new HttpEntity<>(validHeaders);
	}
	
	private HttpHeaders getHttpHeaders(Map<String, String> headers) {
		HttpHeaders validHeaders = new HttpHeaders();
		if (headers != null) {
			for (Map.Entry<String, String> header : headers.entrySet()) {
				validHeaders.add(header.getKey(), header.getValue());
			}
		}
		return validHeaders;
	}
	
	public ResponseEntity<?> executeGet(String url) {
		return execute(url, HttpMethod.GET, null);
	}
	
	public ResponseEntity<?> executeGet(String url, Map<String, String> headers) {
		return execute(url, HttpMethod.GET, getRequestEntity(headers));
	}
	
	public ResponseEntity<?> executePost(String url, Map<String, String> formBody) {
		return executePost(url, new HashMap<String, String>(), formBody);
	}
	
	public ResponseEntity<?> executePost(String url, String jsonBody) {
		return executePost(url, new HashMap<String, String>(), jsonBody);
	}
	
	public ResponseEntity<?> executePost(String url, Map<String, String> headers, String jsonBody) {
		headers.put("Content-Type", "application/json");
		return execute(url, HttpMethod.POST, getRequestEntity(headers, jsonBody));
	}
	
	public ResponseEntity<?> executePost(String url, Map<String, String> headers, Map<String, String> formBody) {
		headers.put("Content-Type", "application/x-www-form-urlencoded");
		return execute(url, HttpMethod.POST, getRequestEntity(headers, formBody));
	}
	
	/*
	@Autowired
	private Client apiClient;
	private Invocation.Builder getRequestBuilder(String url, MultivaluedMap<String, Object> headers, Map<String, NewCookie> cookies) {
		Invocation.Builder requestBuilder = apiClient.target(url).request();
		if (headers != null) {
			requestBuilder.headers(headers);
		}
		if (cookies != null) {
			for (Map.Entry<String, NewCookie> cookie : cookies.entrySet()) {
				requestBuilder.cookie(cookie.getValue());
			}
		}
		return requestBuilder;
	}
	public Response executeGet(String url) {
		return getRequestBuilder(url, null, null).get();
	}
	public Response executeGet(String url, MultivaluedMap<String, Object> headers) {
		return getRequestBuilder(url, headers, null).get();
	}
	
	public Response executeGet(String url, MultivaluedMap<String, Object> headers, Map<String, NewCookie> cookies) {
		return getRequestBuilder(url, headers, cookies).get();
	}
	public Response executePost(String url, Entity<?> body) {
		return getRequestBuilder(url, null, null).post(body);
	}
	public Response executePost(String url, Entity<?> body, MultivaluedMap<String, Object> headers) {
		return getRequestBuilder(url, headers, null).post(body);
	}
	public Response executePost(String url, Entity<?> body, Map<String, NewCookie> cookies) {
		return getRequestBuilder(url, null, cookies).post(body);
	}
	public Response executePost(String url, Entity<?> body, MultivaluedMap<String, Object> headers, Map<String, NewCookie> cookies) {
		return getRequestBuilder(url, headers, cookies).post(body);
	}
	*/
	
	
	
}