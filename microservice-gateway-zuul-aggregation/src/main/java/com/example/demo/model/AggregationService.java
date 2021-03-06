package com.example.demo.model;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import rx.Observable;

@Service
public class AggregationService {
  @Autowired
  private RestTemplate restTemplate;

  @HystrixCommand(fallbackMethod = "fallback")
  public Observable<User> getUserById(Long id) {
    // 创建一个被观察者
    return Observable.create(observer -> {
      // 请求用户微服务的/{id}端点
      User user = new User();
      String name =  this.restTemplate.getForObject("http://wpf-service/get_info?key=" + id, String.class);
      user.setId(id);
      user.setName(name);
      observer.onNext(user);
      observer.onCompleted();
    });
  }

  @HystrixCommand(fallbackMethod = "fallback")
  public Observable<User> getMovieUserByUserId(Long id) {
    return Observable.create(observer -> {
      // 请求电影微服务的/user/{id}端点
    	 User user = new User();
         String name =  this.restTemplate.getForObject("http://wpf-consumer/user/" + id, String.class);
         user.setId(id);
         user.setName(name);
         observer.onNext(user);
         observer.onCompleted();
    });
  }

  public User fallback(Long id) {
    User user = new User();
    user.setId(-1L);
    return user;
  }
}
