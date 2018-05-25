package com.example.demo.feign;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

public interface UserFeign {
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	  public String findById(@PathVariable("id") Long id);
}
