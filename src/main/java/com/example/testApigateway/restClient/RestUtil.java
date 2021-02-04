package com.example.testApigateway.restClient;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
/*
import io.betgeek.botcore.dto.BetHouseConfigurationDTO;
import io.betgeek.botcore.dto.BetProviderDTO;*/

@Component
public class RestUtil {

	@Autowired
	private ObjectMapper mapper;
	
	public JsonNode responseBodyToJson(String response) {
		JsonNode responseBodyJson = null;
		try {
		    responseBodyJson = mapper.readTree(response);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return responseBodyJson;
	}

	/*public List<BetProviderDTO> responseBodyToList(String response) {
	    List<BetProviderDTO> bodyList = new ArrayList<>();
		try {
		    JsonNode responseBodyJson = responseBodyToJson(response);
		    if (responseBodyJson.has("results")) {
		    	String results = responseBodyJson.get("results").toString();
				bodyList = mapper.readValue(results, new TypeReference<List<BetProviderDTO>>(){});
		    }
		} catch (IOException e) {
			e.printStackTrace();
		}
	    return bodyList;
	}
	
	public List<BetHouseConfigurationDTO> responseBodyToListConfiguration(String response) {
		List<BetHouseConfigurationDTO> bodyList = new ArrayList<BetHouseConfigurationDTO>();
		try {
		    JsonNode responseBodyJson = responseBodyToJson(response);
		    if (responseBodyJson.has("data")) {
		    	String results = responseBodyJson.get("data").toString();
				bodyList = mapper.readValue(results, new TypeReference<List<BetHouseConfigurationDTO>>(){});
		    }
		} catch (IOException e) {
			e.printStackTrace();
		}
		return bodyList;
	}*/
}