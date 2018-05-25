package com.example.demo.controller;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.feign.UserFeign;
import com.example.demo.pojo.User;


@RestController
public class MovieController {
  /*@Autowired
  private RestTemplate restTemplate;

  @GetMapping("/user/{id}")
  public String findById(@PathVariable Long id) {
    return this.restTemplate.getForObject("http://localhost:8090/get_info?key=" + id, String.class);
  }*/
	@Autowired
	private UserFeign userFeign;
	@Autowired
	private LoadBalancerClient loadBalancerClient;
	private static final Logger LOGGER = LoggerFactory.getLogger(MovieController.class);
	@GetMapping("/{id}")
	public String findById(@PathVariable("id") String id) {
		return this.userFeign.findById(id);
	}
	
	 @GetMapping("/user/{id}")
	  public String findById1(@PathVariable Long id) {
	    return this.userFeign.findById(String.valueOf(id));
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
	
	 @RequestMapping(value = "/post", method = RequestMethod.POST)
	    public User post(User user) {
	        return this.userFeign.post(user);
	    }
	 
	 @RequestMapping(value = "/get", method = RequestMethod.GET)
	    public User get(@RequestParam  Map<String, Object> map) {
	        return this.userFeign.get(map);
	    }
}
