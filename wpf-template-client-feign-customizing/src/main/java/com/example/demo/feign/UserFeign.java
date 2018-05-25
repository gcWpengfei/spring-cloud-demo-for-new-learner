package com.example.demo.feign;

import org.springframework.cloud.netflix.feign.FeignClient;

import com.example.config.FeignConfiguration;

import feign.Param;
import feign.RequestLine;

@FeignClient(name="wpf-service", configuration = FeignConfiguration.class)
public interface UserFeign {

	@RequestLine("GET /get_info")
	public String getInfo(@Param("key") String key);
}
