package com.ZooManagerApp.controller;

import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URISyntaxException;

@RestController
public class HomePageController {

    @Value("${server.port:8080}")
    private String port;

    @Value("${server.address:localhost}")
    private String host;

    public String constructLoginAddress() {
        String loginAddress = UriComponentsBuilder.newInstance()
                .scheme("http").host(host).port(port).path("/login").build().toString();
        return loginAddress;
    }

    @GetMapping("/home")
    @ApiOperation(value= "Homepage",
            notes = "Starting page, available to everyone.",
            response = String.class)
    public String getHomePage () throws URISyntaxException {
        return "Welcome in ZooManagerApp! Please, log in to the service. \n" + constructLoginAddress();
    }
}
