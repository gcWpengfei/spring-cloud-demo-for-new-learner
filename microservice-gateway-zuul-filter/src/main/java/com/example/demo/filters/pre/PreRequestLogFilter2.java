package com.example.demo.filters.pre;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;

public class PreRequestLogFilter2 extends ZuulFilter {
  private static final Logger LOGGER = LoggerFactory.getLogger(PreRequestLogFilter2.class);

  @Override
  public String filterType() {
    return "pre";
  }

  @Override
  public int filterOrder() {
    return 5;
  }

  @Override
  public boolean shouldFilter() {
    return true;
  }

  @Override
  public Object run() {
    RequestContext ctx = RequestContext.getCurrentContext();
    HttpServletRequest request = ctx.getRequest();
    
    PreRequestLogFilter2.LOGGER.info("+++++++++++++++++++++++++++22222++++++++++++++++++++++++++++++");
    PreRequestLogFilter2.LOGGER.info(String.format("send %s request to %s", request.getMethod(), request.getRequestURL().toString()));
    PreRequestLogFilter2.LOGGER.info("++++++++++++++++++++++++++++22222+++++++++++++++++++++++++++++");
    
    
    return null;
  }
}
