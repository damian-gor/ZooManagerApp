package com.ZooManagerApp.controller;

import com.ZooManagerApp.model.Division;
import com.ZooManagerApp.service.DivisionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class DivisionController {

    @Autowired
    private DivisionService divisionService;

    @GetMapping("/divisions")
    public ResponseEntity<List<Division>> getAllDivisions(){
        return ResponseEntity.ok().body(divisionService.getAllDivisions());
    }

    @GetMapping("/divisions/{id}")
    public ResponseEntity<Division> getDivision(@PathVariable long id){
        return ResponseEntity.ok().body(divisionService.getDivision(id));
    }

    @PostMapping("/divisions")
    public ResponseEntity<Division> addDivision(@RequestBody Division division){
        return ResponseEntity.ok().body(divisionService.addDivision(division));
    }

    @PutMapping("/divisions/{id}")
    public ResponseEntity<Division> updateDivision(@PathVariable long id, @RequestBody Division division){
    division.setId(id);
        return ResponseEntity.ok().body(divisionService.updateDivision(division));
    }

    @DeleteMapping("/divisions/{id}")
    public HttpStatus removeDivision(@PathVariable long id){
        divisionService.removeDivision(id);
        return HttpStatus.OK;
    }

}











