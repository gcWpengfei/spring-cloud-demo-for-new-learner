package com.example.demo.feign;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name="wpf-service")
public interface UserFeign {

	@RequestMapping(value = "/get_info", method = RequestMethod.GET)
	public String findById(@RequestParam("key") String key);
}
