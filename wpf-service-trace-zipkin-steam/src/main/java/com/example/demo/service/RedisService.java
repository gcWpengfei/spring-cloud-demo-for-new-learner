package com.example.demo.service;

/**
 * @author li gang
 */
public interface RedisService {

    String getInformationByKey(String key);

    void saveInformation(String key, String value);
}
