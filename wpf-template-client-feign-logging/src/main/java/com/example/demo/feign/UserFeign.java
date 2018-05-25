package com.example.demo.feign;

import java.util.Map;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.config.FeignLogConfiguration;
import com.example.demo.pojo.User;

@FeignClient(name="wpf-service", configuration = FeignLogConfiguration.class)
public interface UserFeign {

	@RequestMapping(value = "/get_info", method = RequestMethod.GET)
	public String findById(@RequestParam("key") String key);
	
	@RequestMapping(value = "/post", method = RequestMethod.POST)
	public User post(@RequestBody User User);
	
	@RequestMapping(value = "/get", method = RequestMethod.GET)
	public User get(@RequestParam Map<String, Object> map);
}
