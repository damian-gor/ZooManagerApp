package com.ZooManagerApp.controller;

import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AdminController {

    @GetMapping("/admin")
    @ApiOperation(value= "Admin's panel.",
            notes = "Returning confidential information.",
            response = String.class)
    public String getAdminData(){
        return "***** Confidential information *****";
    }

}

