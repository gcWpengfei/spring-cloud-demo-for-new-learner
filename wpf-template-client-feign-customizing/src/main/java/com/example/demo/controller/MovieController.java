package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.feign.UserFeign;


@RestController
public class MovieController {
  
	@Autowired
	private UserFeign userFeign;
	
	@GetMapping("/user/{key}")
	public String findById(@PathVariable("key") String key) {
		return this.userFeign.getInfo(key);
	}
}
