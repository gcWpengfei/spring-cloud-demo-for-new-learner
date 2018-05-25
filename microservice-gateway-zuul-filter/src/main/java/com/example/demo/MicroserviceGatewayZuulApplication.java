package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;

import com.example.demo.filters.pre.PreRequestLogFilter;
import com.example.demo.filters.pre.PreRequestLogFilter2;

@SpringBootApplication
@EnableZuulProxy
public class MicroserviceGatewayZuulApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicroserviceGatewayZuulApplication.class, args);
	}
	
	

	  @Bean
	  public PreRequestLogFilter preRequestLogFilter() {
	    return new PreRequestLogFilter();
	  }
	  
	  @Bean
	  public PreRequestLogFilter2 preRequestLogFilter2() {
	    return new PreRequestLogFilter2();
	  }
}
