package com.example.demo.service.imp;

import org.springframework.stereotype.Service;

import com.example.demo.service.DemoService;

/**
 * @author Li gang
 * @create 2017-03-29 10:43
 **/
@Service
public class DemoServiceImpl implements DemoService {

    @Override
    public String getInformation() {
        return "hello spring cloud A...";
    }
}
