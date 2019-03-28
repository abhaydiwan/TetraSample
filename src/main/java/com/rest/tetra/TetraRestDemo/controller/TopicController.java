package com.rest.tetra.TetraRestDemo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.rest.tetra.TetraRestDemo.model.Topic;
import com.rest.tetra.TetraRestDemo.service.TopicService;
import com.rest.tetra.exception.TopicNotFoundException;

@RestController
@RequestMapping("/topic")
public class TopicController {

	@Autowired
	TopicService topicService;
	//http://localhost:8080/topic?id=1
	//http://localhost:8080/topic/1
	//http://localhost:8080/topic/batch/1/topic/1
	//@GetMapping("/batch/{batchid}/topic/{topicid}")
	@GetMapping( value = "/{topicid:[\\d]+}")
	public Topic getTopic(@PathVariable("topicid") String id)
	{
		try {
		//System.out.println("Batch Id  "+batchid);
		return this.topicService.getTopic(id);
		}catch(TopicNotFoundException ex) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Topic Not Found", ex);
		}
	}
	@GetMapping(produces="application/xml",path="/{id}")
	public Topic getTopicInXml(@PathVariable String id)
	{
		try {
			return this.topicService.getTopic(id);
		  }catch(TopicNotFoundException ex) {
				throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Topic Not Found", ex);
			}
	}
	//@RequestMapping(path = "/topic",method=RequestMethod.POST)
	@PostMapping()
	public String addTopic(@RequestBody Topic topic)
	{
		this.topicService.addTopic(topic);
		return "Topic added "+topic;
	}
	//@RequestMapping(path = "/topic",method=RequestMethod.PUT)
	@PutMapping()
	public String updateTopic(@RequestBody Topic topic)
	{
		this.topicService.updateTopic(topic);
		return "Topic updated!!";
	}
	//@RequestMapping(path = "/topic",method=RequestMethod.DELETE)
	@DeleteMapping("/{id}")
	public String deleteTopic(@PathVariable String id)
	{
		this.topicService.deleteTopic(id);
		return "Topic deleted!!";
	}
}
