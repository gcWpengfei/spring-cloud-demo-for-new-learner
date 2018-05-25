package com.example.demo.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class MovieController {

	private static final Logger LOGGER = LoggerFactory.getLogger(MovieController.class);
	@Autowired
	private LoadBalancerClient loadBalancerClient;

	@Autowired
	private RestTemplate restTemplate;

	@GetMapping("/user/{id}")
	public String findById(@PathVariable Long id) {
		return this.restTemplate.getForObject("http://wpf-service/get_info?key=" + id, String.class);
	}

	@GetMapping("/log-user-instance")
	public String logUserInstance() {
		ServiceInstance serviceInstance = this.loadBalancerClient.choose("wpf-service");
		// 打印当前选择的是哪个节点
		MovieController.LOGGER.info("{}:{}:{}", serviceInstance.getServiceId(), serviceInstance.getHost(),
				serviceInstance.getPort());
		
		return serviceInstance.getServiceId() + "　：　"+ serviceInstance.getHost() + "　：　"+ 
				serviceInstance.getPort();
	}

}
