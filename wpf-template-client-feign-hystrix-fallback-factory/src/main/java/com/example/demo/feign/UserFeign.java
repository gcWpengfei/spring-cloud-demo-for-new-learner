package com.example.demo.feign;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import feign.hystrix.FallbackFactory;

@FeignClient(name="wpf-service", fallbackFactory=FeignClientFallbackFactory.class)
public interface UserFeign {

	@RequestMapping(value = "/get_info", method = RequestMethod.GET)
	public String findById(@RequestParam("key") String key);
}

/**
 * UserFeignClient的fallbackFactory类，该类需实现FallbackFactory接口，并覆写create方法
 * The fallback factory must produce instances of fallback classes that
 * implement the interface annotated by {@link FeignClient}.
 * @author 周立
 */
@Component
class FeignClientFallbackFactory implements FallbackFactory<UserFeign>{
	 private static final Logger LOGGER = LoggerFactory.getLogger(FeignClientFallbackFactory.class);
	@Override
	public UserFeign create(Throwable cause) {
		// TODO Auto-generated method stub
		return new UserFeign() {
			
			@Override
			public String findById(String key) {
				FeignClientFallbackFactory.LOGGER.info("fallback; reason was:", cause);
				// TODO Auto-generated method stub
				return "error";
			}
		};
	}
}

