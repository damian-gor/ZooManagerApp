package com.ZooManagerApp.controller;

import com.ZooManagerApp.service.HomePageService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URISyntaxException;

@RestController
public class HomePageController {

    @Autowired
    private HomePageService homePageService;

    @GetMapping("/home")
    @ApiOperation(value= "Homepage",
            notes = "Starting page, available to everyone.",
            response = String.class)
    public String getHomePage () throws URISyntaxException {
        return "Welcome in ZooManagerApp! Please, log in to the service. \n" + homePageService.constructLoginAddress();
    }
}
