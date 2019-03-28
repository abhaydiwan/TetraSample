package com.rest.tetra.RestTemplate.client;

import org.springframework.web.client.RestTemplate;

import com.rest.tetra.TetraRestDemo.model.Topic;

public class RestTemplateClient {

	RestTemplate restTemplate;
	String url = "http://localhost:8080/topic/1";
	
			
	  public RestTemplateClient() {
		this.restTemplate = new RestTemplate();
		
	}
	  
	public void getTopic() {
		
		Topic topic = this.restTemplate.getForObject(url, Topic.class);
		System.out.println("topic is "+ topic);
		
	}
	
}
