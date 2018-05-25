package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.feign.FeignClientsConfiguration;
import org.springframework.context.annotation.Import;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.feign.UserFeign;

import feign.Client;
import feign.Contract;
import feign.Feign;
import feign.auth.BasicAuthRequestInterceptor;
import feign.codec.Decoder;
import feign.codec.Encoder;

@Import(FeignClientsConfiguration.class)
@RestController
public class MovieController {
	
	private UserFeign userUserFeignClient;
	
	private UserFeign adminUserFeignClient;
	
	
	
	 @Autowired
	  public MovieController(Decoder decoder, Encoder encoder, Client client, Contract contract) {
	    // 这边的decoder、encoder、client、contract，可以debug看看是什么实例。
	    this.userUserFeignClient = Feign.builder().client(client).encoder(encoder).decoder(decoder).contract(contract)
	        .requestInterceptor(new BasicAuthRequestInterceptor("user", "password1")).target(UserFeign.class, "http://wpf-service/");
	    this.adminUserFeignClient = Feign.builder().client(client).encoder(encoder).decoder(decoder).contract(contract)
	        .requestInterceptor(new BasicAuthRequestInterceptor("admin", "password2"))
	        .target(UserFeign.class, "http://wpf-service/");
	  }
	
	 @GetMapping("/user-user/{id}")
	  public String findByUser(@PathVariable Long id) {
	    return this.userUserFeignClient.findById(id);
	  }
	 
	 @GetMapping("/admin-user/{id}")
	  public String findByAdmin(@PathVariable Long id) {
	    return this.adminUserFeignClient.findById(id);
	  }
	 
	 
	
}
