package com.ZooManagerApp.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

@Component
public class HomePageService {

    @Value("${server.port:8080}")
    public String port;
    @Value("${server.address:localhost}")
    public String host;

    public String constructLoginAddress() {
        String loginAddress = UriComponentsBuilder.newInstance()
                .scheme("http").host(host).port(port).path("/login").build().toString();
        return loginAddress;
    }
}