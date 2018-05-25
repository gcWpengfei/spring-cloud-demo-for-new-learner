package com.example.demo.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.pojo.User;
import com.example.demo.service.DemoService;
import com.example.demo.service.RedisService;


/**
 * @author li gang
 */
@RestController
public class Controller {

    @Autowired
    private RedisService redisService;

    @Autowired
    private DemoService demoService;

    @RequestMapping(value = "/")
    public String healthCheck() {
        return "server is ok";
    }

    @RequestMapping(value = "/get_info")
    public String getInfo(@RequestParam(value = "key") String key) {
        return this.redisService.getInformationByKey(key);
    }

    @RequestMapping(value = "/save_info", method = RequestMethod.GET)
    public void saveInfo(@RequestParam(value = "key") String key, @RequestParam(value = "value") String value) {
        this.redisService.saveInformation(key, value);
    }

    @RequestMapping(value = "/hello_world")
    public String helloWorld(HttpServletResponse response, HttpServletRequest request) {
        String aaa = response.getHeader("aaa");
        System.out.println(aaa);
        return this.demoService.getInformation();
    }
    
    @RequestMapping(value = "/post", method = RequestMethod.POST)
    public User post(@RequestBody User user) {
       
        return user;
    }
    
    @RequestMapping(value = "/get", method = RequestMethod.GET)
    public User get( User user) {
       
        return user;
    }

}
