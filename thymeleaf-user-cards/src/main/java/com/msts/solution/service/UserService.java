package com.msts.solution.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.msts.solution.model.ApiResponse;
import com.msts.solution.model.User;

@Service
public class UserService {

	@Autowired
	RestTemplate restTemplate;

	public List<User> getAllUsers() throws Exception{
		HttpHeaders headers = new HttpHeaders();
		headers.add("user-agent", "Application");
		HttpEntity<String> entity = new HttpEntity<>(headers);
		ApiResponse apiResponse = restTemplate
				.exchange("https://reqres.in/api/users", HttpMethod.GET, entity, ApiResponse.class).getBody();
		return apiResponse != null ? apiResponse.getData() : new ArrayList<>();
	}

}
